package com.comino.mavcom.comm.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.mavlink.messages.MAVLinkMessage;
import org.mavlink.messages.lquac.msg_heartbeat;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.comm.IMAVProxy;
import com.comino.mavcom.log.IMAVMessageListener;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.mavlink.MAVAcknowledge;
import com.comino.mavcom.mavlink.MAVLinkBlockingReader;
import com.comino.mavcom.mavlink.MAVLinkToModelParser;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.LogMessage;

public class MAVUdpCommNIO2 implements IMAVComm {

	private static final int BROADCAST_PORT = 4445;

	private static final int BUFFER_SIZE = 128;

	private static final int WAITING     = 0;
	private static final int RUNNING     = 1;

	private static MAVUdpCommNIO2 com    = null;

	private int   state          = WAITING;
	private long  transfer_speed = 0;


	private final DataModel model;
	private final MAVLinkToModelParser parser;
	private final InetSocketAddress peerPort;
	private final MAVLinkBlockingReader reader;

	private final int bindPort;

	private DatagramChannel channel      = null;
	private Selector        selector     = null;

	private IMAVProxy       byteListener = null;
	private Thread worker                = null;

	private final ByteBuffer rxBuffer    = ByteBuffer.allocateDirect(BUFFER_SIZE*1024);
	private final byte[]    proxyBuffer  = new byte[rxBuffer.capacity()];

	private final static msg_heartbeat hb = new msg_heartbeat(255,1);

	public static MAVUdpCommNIO2 getInstance(DataModel model, String peerAddress, int peerPort, int bindPort) {
		if(com==null)
			com = new MAVUdpCommNIO2(model, peerAddress, peerPort, bindPort);
		return com;
	}

	private MAVUdpCommNIO2(DataModel model, String peerAddress, int pPort, int bPort) {


		this.model    = model;
		this.parser   = new MAVLinkToModelParser(model,this);
		this.peerPort = new InetSocketAddress(peerAddress,pPort);
		this.reader   = new MAVLinkBlockingReader(2, parser);
		this.bindPort = bPort;

		hb.isValid = true;
		worker = new Thread(new Worker());
		worker.start();

		System.out.println("Vehicle (NIO4): BindPort="+bPort+" PeerPort="+pPort+ " BufferSize: "+rxBuffer.capacity());
	}


	@Override
	public boolean open() {
		try {
			state = WAITING;
			if(selector!=null)
				selector.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public DataModel getModel() {
		return model;
	}

	@Override
	public Map<Class<?>, MAVLinkMessage> getMavLinkMessageMap() {
		if(parser!=null)
			return parser.getMavLinkMessageMap();
		return null;
	}

	@Override
	public void close() {
		state = WAITING;
		try {
			if(channel!=null)
				channel.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
		try {
			System.out.println("[mgc] Closing channel...");
			state = WAITING;
			channel.disconnect();
			channel.close();
		} catch (IOException e) {
		}	
	}

	@Override
	public void write(MAVLinkMessage msg) {
		try {
			if(state == RUNNING && channel.isConnected())
				channel.write(ByteBuffer.wrap(msg.encode()));
		} catch (IOException e) { 	}
	}

	@Override
	public void setProxyListener(IMAVProxy proxy) {
		this.byteListener = proxy;
	}

	@Override
	public void addMAVLinkListener(IMAVLinkListener listener) {
		parser.addMAVLinkListener(listener);
	}

	@Override
	public void addMAVMessageListener(IMAVMessageListener listener) {
		parser.addMAVMessageListener(listener);
	}

	@Override
	public void setCmdAcknowledgeListener(int command,MAVAcknowledge ack) {
		parser.setCmdAcknowledgeListener(command,ack);
	}
	
	public void registerListener(Class<?> clazz, IMAVLinkListener listener) {
		parser.registerListener(clazz, listener);
	}

	@Override
	public boolean isConnected() {
		return channel.isConnected();
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
		parser.writeMessage(m);
	}

	public String toString() {
		return "State: "+state+" ("+transfer_speed +")";
	}

	private class Worker implements Runnable {

		private SelectionKey key = null;
		private Iterator<?> selectedKeys = null;
		private int bcount = 0; 
		private int msg_length; 
		private long start; 
		private String localAddress;


		@Override
		public void run() {

			try {
				channel = DatagramChannel.open();
				final Set<SocketOption<?>> options = channel.supportedOptions();
			    if (options.contains(StandardSocketOptions.TCP_NODELAY)) {
			        channel.setOption(StandardSocketOptions.TCP_NODELAY, true);
			    }
				channel.socket().setReuseAddress(true);
				channel.socket().setReceiveBufferSize(BUFFER_SIZE*1024);
				channel.socket().setSendBufferSize(BUFFER_SIZE*1024);
				channel.socket().setSoTimeout(1000);
				selector = Selector.open();
		//		channel.setOption(StandardSocketOptions.TCP_NODELAY, true);
				channel.configureBlocking(false);
			} catch (IOException e) {
				e.printStackTrace();
			}

			while(channel.isOpen()) {

				while(state == WAITING) {


					try { Thread.sleep(100); } catch (InterruptedException e) { }
					
					transfer_speed = 0;
					((Buffer)rxBuffer).clear();
					try {

						channel.disconnect();
						
						try { Thread.sleep(50); } catch (InterruptedException e) { }

						if(!channel.socket().isBound()) {
							if(peerPort.getAddress().isLoopbackAddress()) {
								channel.socket().bind(new InetSocketAddress(bindPort));
							}
							else {
								localAddress = getLocalAdress(BROADCAST_PORT);
								if(localAddress!=null)
									channel.socket().bind(new InetSocketAddress(localAddress,bindPort));
								else
									channel.socket().bind(new InetSocketAddress(bindPort));
									continue;
							}
						}
						channel.connect(peerPort);
						
						if(selector.isOpen())
							selector.close();
						selector = Selector.open();
						channel.register(selector, SelectionKey.OP_READ);

						if(channel.isConnected())
							state = RUNNING;

					} catch ( ClosedSelectorException  |  IOException e) {
						state = WAITING;
					}

				}

				write(hb);

				start = System.currentTimeMillis();
				while(state == RUNNING) {

					try {
						if(selector.select()==0) {
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
								if(channel.isConnected() && channel.receive(rxBuffer)!=null) {
									((Buffer)rxBuffer).flip();
									msg_length = 0;
									while(rxBuffer.hasRemaining()) {
										proxyBuffer[msg_length++] = rxBuffer.get();
									}
									rxBuffer.compact();

									if(byteListener !=null)
										byteListener.write(proxyBuffer, msg_length);
									reader.put(proxyBuffer, msg_length);
									bcount = bcount + msg_length;
								}
								if((System.currentTimeMillis() - start) > 750) {
									transfer_speed = bcount * 1000 / (System.currentTimeMillis() - start);
									bcount = 0; start = System.currentTimeMillis();
								}
							}	
						}
					} catch (Exception e) {
						try {
							selector.close();
						} catch (Exception e1) { 	}
						state = WAITING;
					}	
				}				
			}	
		}

		private String getLocalAdress(int port) {

			InetAddress localAddress = null;
			boolean      found = false;

			Enumeration<?> e;
			try {
				String peer = listenToBroadcast(port);
				e = NetworkInterface.getNetworkInterfaces();
				while(e.hasMoreElements() && !found)
				{
					NetworkInterface n = (NetworkInterface) e.nextElement();
					Enumeration<?> ee = n.getInetAddresses();
					while (ee.hasMoreElements()) {
						localAddress = (InetAddress) ee.nextElement();
						if(localAddress.getHostAddress().startsWith(peer.substring(0,3))) {
							found = true;
							break;
						}
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}

			if(found) {
				System.out.println("LocalAdress: "+localAddress.getHostAddress());
				return localAddress.getHostAddress();
			}
			System.out.println("No adapter found");
			return null;
		}

		private String listenToBroadcast(int port) throws IOException {

			DatagramSocket socket;
			byte[] buf = new byte[5];
			socket = new DatagramSocket(port);
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			System.out.println("Waiting for remote broadcast...");
			socket.receive(packet);
			System.out.println("Remote broadcast received. Binding..");
			InetAddress address = packet.getAddress();
			socket.close();
			String received =  new String(packet.getData(), 0, packet.getLength());
			if (received.equals("LQUAC")) {			
				System.out.println("Address: "+address.getHostAddress());
				return address.getHostAddress();
			}
			return null;	
		}
	}


	public static void main(String[] args) {
		MAVUdpCommNIO2 comm = new MAVUdpCommNIO2(new DataModel(), "172.168.178.22", 14555, 14550);





		try {
			while(true) {
				comm.open();
				System.out.println("Started");
				long time = System.currentTimeMillis();
				while(System.currentTimeMillis()< (time+3000)) {
					if(comm.isConnected())
						System.out.println(comm.isConnected()+" => "+comm+" => ANGLEX="+comm.model.hud.aX+" ANGLEY="+comm.model.hud.aY);
					Thread.sleep(200);
					comm.write(hb);
				}
				comm.close();
				Thread.sleep(5000);
			}

		} catch (Exception e) {
			comm.close();
			e.printStackTrace();

		}
	}

}
