package com.comino.mavcom.comm.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

import org.mavlink.messages.MAVLinkMessage;
import org.mavlink.messages.lquac.msg_heartbeat;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.comm.IMAVProxy;
import com.comino.mavcom.log.IMAVMessageListener;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.mavlink.MAVAcknowledge;
import com.comino.mavcom.mavlink.MAVLinkBlockingReader;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.LogMessage;
import com.comino.mavcom.model.segment.Status;

public class MAVUdpCommNIO2 implements IMAVComm {

	private static final int BROADCAST_PORT = 4445;

	private static final int BUFFER_SIZE = 128;

	private static final int WAITING = 0;
	private static final int RUNNING = 1;

	private static MAVUdpCommNIO2 com = null;

	private int state = WAITING;
	private long transfer_speed = 0;

	private final DataModel model;
	private final InetSocketAddress peerPort;
	private final MAVLinkBlockingReader reader;

	private final int bindPort;

	private DatagramChannel channel = null;
	private Selector selector = null;

	private List<IMAVProxy> byteListener = new ArrayList<IMAVProxy>();
	private Thread wt = null;

	private final ByteBuffer rxBuffer = ByteBuffer.allocateDirect(BUFFER_SIZE * 1024);
	private final byte[] proxyBuffer = new byte[rxBuffer.capacity()];

	private Worker worker;

	private String peerAddress;

	private boolean search_local_address = true;

	private final static msg_heartbeat hb = new msg_heartbeat(255, 1);

	public static MAVUdpCommNIO2 getInstance(MAVLinkBlockingReader reader, String peerAddress, int peerPort,
			int bindPort) {
		if (com == null)
			com = new MAVUdpCommNIO2(reader, peerAddress, peerPort, bindPort,true);
		return com;
	}

	public MAVUdpCommNIO2(MAVLinkBlockingReader reader, String peerAddress, int pPort, int bPort, boolean search_local_address) {
		this.search_local_address = search_local_address;
		this.model = reader.getModel();
		this.peerPort = new InetSocketAddress(peerAddress, pPort);
		this.reader = reader;
		this.bindPort = bPort;
		this.peerAddress = peerAddress;

		hb.isValid = true;
		this.worker = new Worker();
		wt = new Thread(worker);
		wt.start();
	}

	public String toString() {
		return "UDP " + peerPort.getHostString();
	}

	public boolean open() {


		if(state == RUNNING)
			return true;

		try {
			state = WAITING;
			if (selector != null)
				selector.close();
			worker.waitFor();
			if (state == WAITING) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channel.isConnected();
	}

	@Override
	public DataModel getModel() {
		return model;
	}

	@Override
	public void close() {
		state = WAITING;
	}

	@Override
	public void shutdown() {
		try {

			try {
				channel.disconnect();
			} catch (SocketException s) { }
			channel.close();
			state = WAITING;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void write(MAVLinkMessage msg) {
		try {
			if (state == RUNNING && channel.isConnected())
				channel.write(ByteBuffer.wrap(msg.encode()));
		} catch (IOException e) {
		}
	}

	@Override
	public void setProxyListener(IMAVProxy proxy) {
		this.byteListener.add(proxy);
	}

	@Override
	public boolean isConnected() {
		return channel != null && channel.isConnected() && state == RUNNING;
	}

	@Override
	public boolean isSerial() {
		return false;
	}

	@Override
	public int getErrorCount() {
		return reader.getLostPackages();
	}

	@Override
	public long getTransferRate() {
		return transfer_speed;
	}

	@Override
	public void writeMessage(LogMessage m) {
		reader.getParser().writeMessage(m);
	}

	@Override
	public MAVLinkBlockingReader getReader() {
		return reader;
	}

	@Override
	public String getHost() {
		return peerAddress;
	}

	private class Worker implements Runnable {

		private SelectionKey key = null;
		private Iterator<?> selectedKeys = null;
		private int bcount = 0;
		private int msg_length;
		private long start;
		private String localAddress;

		public void waitFor() {
			synchronized (this) {
				try {
					wait(100);
				} catch (InterruptedException e) {
				}
			}
		}


		@Override
		public void run() {

			try {
				channel = DatagramChannel.open();
				final Set<SocketOption<?>> options = channel.supportedOptions();
				if (options.contains(StandardSocketOptions.TCP_NODELAY)) {
					channel.setOption(StandardSocketOptions.TCP_NODELAY, true);
				}
				channel.socket().setReuseAddress(true);
				channel.socket().setReceiveBufferSize(BUFFER_SIZE * 1024);
				channel.socket().setSendBufferSize(BUFFER_SIZE * 1024);
				channel.socket().setSoTimeout(1000);
				selector = Selector.open();
				// channel.setOption(StandardSocketOptions.TCP_NODELAY, true);
				channel.configureBlocking(false);
			} catch (IOException e) {
				e.printStackTrace();
			}

			while (true) {

				while (state == WAITING) {

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
					
					if(!channel.isOpen())
						return;

					transfer_speed = 0;
					((Buffer) rxBuffer).clear();
					try {

						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
						}

						if (!channel.socket().isBound()) {
							if (peerPort.getAddress().isLoopbackAddress() || !search_local_address) {
								channel.socket().bind(new InetSocketAddress(bindPort));
							} else {
								localAddress = getLocalAdress(BROADCAST_PORT);
								if (localAddress != null)
									channel.socket().bind(new InetSocketAddress(localAddress, bindPort));
								else
									channel.socket().bind(new InetSocketAddress(bindPort));
								continue;
							}
						}

						if(!channel.isConnected())
							channel.connect(peerPort);

						if (selector.isOpen())
							selector.close();
						selector = Selector.open();
						channel.register(selector, SelectionKey.OP_READ);

						if (channel.isConnected())
							state = RUNNING;

					} catch (ClosedSelectorException | IOException e) {
						state = WAITING;
					}

				}
				synchronized (this) {
					notify();
				}

				write(hb);

				start = System.currentTimeMillis();
				while (state == RUNNING) {

					try {
						if (selector.select() == 0) {
							state = WAITING;
							continue;
						}

						selectedKeys = selector.selectedKeys().iterator();
					

						while (selectedKeys.hasNext()) {

							key = (SelectionKey) selectedKeys.next();
							selectedKeys.remove();

							if (!key.isValid()) {
								continue;
							}

							if (key.isReadable()) {
								if (channel.isConnected() && channel.receive(rxBuffer) != null) {
									
									((Buffer) rxBuffer).flip();
									msg_length = 0;
									while (rxBuffer.hasRemaining()) {
										proxyBuffer[msg_length++] = rxBuffer.get();
									}
									rxBuffer.compact();

									byteListener.forEach( (proxy) -> proxy.write(proxyBuffer, msg_length));
										
									reader.put(proxyBuffer, msg_length);
									bcount = bcount + msg_length;
								}
								if ((System.currentTimeMillis() - start) > 750) {
									transfer_speed = bcount * 1000 / (System.currentTimeMillis() - start);
									bcount = 0;
									start = System.currentTimeMillis();
								}
							}
						}
					} catch (Exception e) {
						try {
							selector.close();
						} catch (Exception e1) {
						}

						state = WAITING;
					}
				}
			}
		}

		private String getLocalAdress(int port) {

			InetAddress localAddress = null;
			boolean found = false;

			int count = 0;

			Enumeration<?> e;

			try {
				while(!found && ++count < 50) {
					String peer = listenToBroadcast(port);
					System.err.println(peer);
					e = NetworkInterface.getNetworkInterfaces();
					while (e.hasMoreElements() && !found) {
						NetworkInterface n = (NetworkInterface) e.nextElement();
						Enumeration<?> ee = n.getInetAddresses();
						while (ee.hasMoreElements()) {
							localAddress = (InetAddress) ee.nextElement();
							if (localAddress.getHostAddress().startsWith(peer.substring(0, 7))) {
								found = true;
								break;
							}
						}
					}
					if(!found) LockSupport.parkNanos(200_000_000);
				}
			} catch (IOException e1) {
				return null;
			}

			if (found) {
				System.out.println("LocalAdress: " + localAddress.getHostAddress());
				return localAddress.getHostAddress();
			}
			System.out.println("No adapter found");
			return null;
		}

		private String listenToBroadcast(int port) throws IOException {

			DatagramSocket socket;
			byte[] buf = new byte[5];
			socket = new DatagramSocket(port);
			socket.setSoTimeout(500);
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			System.out.println("Remote broadcast received. Binding..");
			InetAddress address = packet.getAddress();
			socket.close();
			String received = new String(packet.getData(), 0, packet.getLength());
			if (received.equals("LQUAC")) {
				System.out.println("Address: " + address.getHostAddress());
				return address.getHostAddress();
			}
			return null;
		}
	}

	public static void main(String[] args) {
		//		MAVUdpCommNIO2 comm = new MAVUdpCommNIO2(new MAVLinkBlockingReader(2, new DataModel()), "172.168.178.22", 14555,
		//				14550);

		//	MAVUdpCommNIO2 comm = new MAVUdpCommNIO2(new MAVLinkBlockingReader(2, new DataModel()), "127.0.0.1", 14580,14540);

		MAVUdpCommNIO2 comm = new MAVUdpCommNIO2(new MAVLinkBlockingReader(2, new DataModel()), "127.0.0.1", 14656,14650,true);



		try {
			while (true) {
				comm.open();
				System.out.println("Started");
				long time = System.currentTimeMillis();
				while (System.currentTimeMillis() < (time + 3000)) {
					if (comm.isConnected())
						System.out.println(comm.isConnected() + " => " + comm + " => ANGLEX=" + comm.model.hud.aX
								+ " ANGLEY=" + comm.model.hud.aY);
					Thread.sleep(200);
					//comm.write(hb);
				}
				comm.close();
				Thread.sleep(5000);
			}

		} catch (Exception e) {
			comm.close();
			e.printStackTrace();

		}
	}
	
	

	@Override
	public void foreward(byte[] b, int len) throws IOException {
		try {
			if (state == RUNNING && channel.isConnected())
				channel.write(ByteBuffer.wrap(b,0,len));
		} catch (IOException e) {
		}
		
	}

}
