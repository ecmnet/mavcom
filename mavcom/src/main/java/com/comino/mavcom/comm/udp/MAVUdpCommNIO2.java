package com.comino.mavcom.comm.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Map;

import org.mavlink.messages.MAVLinkMessage;
import org.mavlink.messages.lquac.msg_heartbeat;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.comm.proxy.MAVUdpProxyNIO2;
import com.comino.mavcom.control.IMAVCmdAcknowledge;
import com.comino.mavcom.log.IMAVMessageListener;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.mavlink.MAVLinkBlockingReader;
import com.comino.mavcom.mavlink.MAVLinkToModelParser;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.LogMessage;
import com.comino.mavcom.model.segment.Status;

public class MAVUdpCommNIO2 implements IMAVComm {

	private static final int BUFFER_SIZE = 128;

	private static final int WAITING     = 0;
	private static final int RUNNING     = 1;

	private static MAVUdpCommNIO2 com    = null;

	private int   state          = WAITING;
	private long  transfer_speed = 0;


	private final DataModel model;
	private final MAVLinkToModelParser parser;
	private final InetSocketAddress peerPort;
	private final InetSocketAddress bindPort;
	private final MAVLinkBlockingReader reader;

	private DatagramChannel channel      = null;
	private Selector        selector     = null;

	private MAVUdpProxyNIO2 byteListener = null;

	private final ByteBuffer rxBuffer    = ByteBuffer.allocate(BUFFER_SIZE*1024);
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
		this.bindPort = new InetSocketAddress(bPort);
		this.reader   = new MAVLinkBlockingReader(2, parser);

		hb.isValid = true;

		System.out.println("Vehicle (NIO4): BindPort="+bPort+" PeerPort="+pPort+ " BufferSize: "+rxBuffer.capacity());
		
		try {
			channel = DatagramChannel.open();
			channel.bind(bindPort);
			channel.socket().setReceiveBufferSize(BUFFER_SIZE*1024);
			channel.socket().setSendBufferSize(32*1024);
			channel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

		new Thread(new Worker()).start();

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
	}

	@Override
	public void write(MAVLinkMessage msg) throws IOException {
		if(state == RUNNING)
			channel.write(ByteBuffer.wrap(msg.encode()));
	}

	@Override
	public void setProxyListener(MAVUdpProxyNIO2 proxy) {
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
	public void setCmdAcknowledgeListener(IMAVCmdAcknowledge ack) {
		parser.setCmdAcknowledgeListener(ack);
	}

	@Override
	public boolean isConnected() {
		return state == RUNNING;
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

		SelectionKey key = null;
		Iterator<?> selectedKeys = null;
		long bcount = 0; 
		int msg_length; 
		long start; 

		@Override
		public void run() {

			while(true) {

				while(state == WAITING) {
					model.sys.setStatus(Status.MSP_CONNECTED,false);
					transfer_speed = 0;
					((Buffer)rxBuffer).clear();
					try {
						
						
						//	channel.socket().setTrafficClass(0x08);
						channel.disconnect();
						channel.connect(peerPort);
						selector = Selector.open();
						channel.register(selector, SelectionKey.OP_READ);

						if(channel.isConnected())
							state = RUNNING;

					} catch (SocketException e) {
						try { channel.close(); selector.close();  } catch (Exception e1) {  }
						state = WAITING;
						e.printStackTrace();
					} catch (ClosedChannelException e) {
						state = WAITING;
					//	e.printStackTrace();
					} catch (IOException e) {
						try { channel.close(); selector.close();  } catch (IOException e1) { }
						state = WAITING;
						e.printStackTrace();
					}
				}
				
				try {
					write(hb);
				} catch (IOException e1) {
					state = WAITING;
				}

				start = System.currentTimeMillis();
				while(state == RUNNING) {

					try {
						if(selector.select(3000)==0) {
							state = WAITING;
							continue;
						}

						selectedKeys = selector.selectedKeys().iterator();

						while (selectedKeys.hasNext()) {

							key = (SelectionKey) selectedKeys.next();
							selectedKeys.remove();

							if (!key.isValid()) {
								state = WAITING;
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
					} catch (IOException e) {
						state = WAITING;
					}	
				}				
			}	
		}

	}

	public static void main(String[] args) {
		MAVUdpCommNIO2 comm = new MAVUdpCommNIO2(new DataModel(), "172.168.178.1", 14555, 14550);
		comm.open();

		long time = System.currentTimeMillis();

		try {

			System.out.println("Started");

			while(System.currentTimeMillis()< (time+60000)) {
				if(comm.isConnected())
					System.out.println(comm.isConnected()+" => "+comm+" => ANGLEX="+comm.model.hud.aX+" ANGLEY="+comm.model.hud.aY);
				Thread.sleep(200);
				comm.write(hb);
			}
			comm.close();

		} catch (Exception e) {
			comm.close();
			e.printStackTrace();

		}
	}

}
