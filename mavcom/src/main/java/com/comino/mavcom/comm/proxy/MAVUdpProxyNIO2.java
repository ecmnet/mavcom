/****************************************************************************
 *
 * Copyright (c) 2017-2021 Eike Mansfeld ecm@gmx.de. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 ****************************************************************************/

package com.comino.mavcom.comm.proxy;

import java.io.IOException;
import java.net.BindException;
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
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

import org.mavlink.messages.MAVLinkMessage;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.comm.IMAVProxy;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.mavlink.MAVLinkReader;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.Status;

public class MAVUdpProxyNIO2 implements IMAVLinkListener, IMAVProxy {

	private static final int BROADCAST_PORT = 4445;

	private static final int BUFFER_SIZE = 16;

	private static final int WAITING = 0;
	private static final int RUNNING = 1;

	private InetSocketAddress bindPort = null;
	private InetSocketAddress peerPort;
	private DatagramChannel channel = null;

	private HashMap<Class<?>, List<IMAVLinkListener>> listeners = null;

	private int state = WAITING;

	private MAVLinkReader reader;
	private Selector selector;
	private IMAVComm comm;

	private boolean proxy_enabled = false;

	private final ByteBuffer rxBuffer = ByteBuffer.allocateDirect(BUFFER_SIZE * 1024);

	private List<IMAVLinkListener> listener_list = null;
	private long transfer_speed = 0;

	private DataModel model;
	

	public MAVUdpProxyNIO2(DataModel model, String peerAddress, int pPort, String bindAddress, int bPort,
			IMAVComm comm) {

		peerPort = new InetSocketAddress(peerAddress, pPort);

		if(bindAddress == null)
			bindPort = new InetSocketAddress(getLocalAddress(), bPort);
		else
			bindPort = new InetSocketAddress(bindAddress, bPort);


		reader = new MAVLinkReader(1);

		this.comm  = comm;
		this.model = model;

		listeners = new HashMap<Class<?>, List<IMAVLinkListener>>();

		System.out.println(
				"Proxy (NIO2): BindPort=" + bPort + " PeerPort=" + pPort + " BufferSize: " + rxBuffer.capacity());

		new Thread(new Worker()).start();

	}

	public boolean open() {
		state = WAITING;
		return true;
	}

	public boolean isConnected() {
		return comm.isConnected();
	}

	public boolean isProxyEnabled() {
		return proxy_enabled;
	}

	public void enableProxy(boolean enable) {
		this.proxy_enabled = enable;
	}

	public void close() {
		state = WAITING;
	}

	public void shutdown() {
		try {
			System.out.println("[mgc] Closing channel...");
			state = WAITING;
			try {
				channel.disconnect();
			} catch (SocketException s) { }
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void broadcast() {
		if (bindPort.getAddress().isLoopbackAddress()) {
			return;
		}
		
		DatagramSocket socket;
		try {
			
			socket = new DatagramSocket(BROADCAST_PORT, bindPort.getAddress());
			socket.setSoTimeout(100);
			socket.setBroadcast(true);
			try {
				DatagramPacket packet = new DatagramPacket("LQUAC".getBytes(), 5, InetAddress.getByName("255.255.255.255"),
						BROADCAST_PORT);
				socket.send(packet);
				socket.receive(packet);
				socket.close();
			} catch (IOException e) {
				socket.close();
			//	System.err.println(bindPort.getAddress().getHostAddress() + ": " + e.getMessage());
			}

		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void registerListener(Class<?> clazz, IMAVLinkListener listener) {

		List<IMAVLinkListener> list = null;
		if (listeners.containsKey(clazz)) {
			list = listeners.get(clazz);
			if (!list.contains(listener)) {
				list.add(listener);
				System.out.println(
						"Register MavLink listener: " + clazz.getSimpleName() + " : " + listener.getClass().getName());
			}
		} else {
			list = new ArrayList<IMAVLinkListener>();
			list.add(listener);
			listeners.put(clazz, list);
			System.out.println(
					"Register MavLink listener: " + clazz.getSimpleName() + " : " + listener.getClass().getName());
		}
	}

	public void unregisterListener(Class<?> clazz) {
		listeners.remove(clazz);
	}

	public int getBadCRC() {
		return 0;
	}

	public void write(MAVLinkMessage msg) {
		try {
			if (state == RUNNING && channel.isConnected())
				channel.write(ByteBuffer.wrap(msg.encode()));
		} catch (IOException e) {
		}
	}

	@Override
	public void received(Object o) {
		if (proxy_enabled) {
			write((MAVLinkMessage) o);
		}
	}

	public long getTransferRate() {
		return transfer_speed;
	}

	public void write(byte[] buffer, int length) {
		if (!model.sys.isStatus(Status.MSP_GCL_CONNECTED))
			return;

		try {
			if (length > 0 && state == RUNNING && channel != null && channel.isConnected())
				channel.write(ByteBuffer.wrap(buffer, 0, length));
		} catch (IOException e) {
		}
	}

	private String getLocalAddress() {

		InetAddress localAddress = null;
		boolean found = false;

		int count = 0;

		Enumeration<?> e;

		try {
			while(!found && ++count < 50) {
				e = NetworkInterface.getNetworkInterfaces();
				while (e.hasMoreElements() && !found) {
					NetworkInterface n = (NetworkInterface) e.nextElement();
					if(!n.getDisplayName().startsWith("w"))
						continue;
					Enumeration<?> ee = n.getInetAddresses();
					while (ee.hasMoreElements()) {
						localAddress = (InetAddress) ee.nextElement();
						if (localAddress.getHostAddress().startsWith("172") || 
							localAddress.getHostAddress().startsWith("192") || 
							localAddress.getHostAddress().startsWith("10")) {
							found = true;
							break;
						}
					}
				}
				if(!found) LockSupport.parkNanos(200_000_000);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}

		if (found) {
			System.out.println("LocalAdress identified: " + localAddress.getHostAddress());
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

	private class Worker implements Runnable {

		SelectionKey key = null;
		MAVLinkMessage msg = null;
		Iterator<?> selectedKeys = null;
		long bcount = 0;
		long start;
		
		byte[] forewardBuffer = new byte[300];
		int    forewardCount  = 0;
		byte c;

		@Override
		public void run() {

			try {
				channel = DatagramChannel.open();
				final Set<SocketOption<?>> options = channel.supportedOptions();
				if (options.contains(StandardSocketOptions.TCP_NODELAY)) {
					channel.setOption(StandardSocketOptions.TCP_NODELAY, true);
				}
				channel.bind(bindPort);
				channel.socket().setReceiveBufferSize(BUFFER_SIZE * 1024);
				channel.socket().setSendBufferSize(BUFFER_SIZE * 1024);
				channel.configureBlocking(false);
				selector = Selector.open();
			} catch (BindException b) {
				try {
					channel.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println("Already running: " + b.getMessage());
				System.err.println("Exiting now...");
				System.exit(-1);

			} catch (IOException e) {
				e.printStackTrace();
			}

			while (true) {

				while (state == WAITING) {

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}

					transfer_speed = 0;
					((Buffer) rxBuffer).clear();
					try {

						// Do not disconnect in SITL because of 12.4
						//						if(comm.isSerial())
						//							channel.disconnect();

						if (!channel.socket().isBound()) {
							channel.socket().bind(bindPort);
						}
						if (selector.isOpen())
							selector.close();
						selector = Selector.open();

						if(!channel.isConnected())
							channel.connect(peerPort);

						channel.register(selector, SelectionKey.OP_READ);

						if (channel.isConnected())
							state = RUNNING;

					} catch (SocketException e) {
						// try { channel.close(); selector.close(); } catch (Exception e1) { }
						state = WAITING;
						// e.printStackTrace();
					} catch (ClosedChannelException e) {
						state = WAITING;
						// e.printStackTrace();
					} catch (IOException e) {
						// try { channel.close(); selector.close(); } catch (IOException e1) { }
						state = WAITING;
						// e.printStackTrace();
					}
				}

				try {
					Thread.sleep(200);
				} catch (InterruptedException e2) { }

				start = System.currentTimeMillis();
				while (state == RUNNING) {

					if (!comm.isConnected()) {
						state = WAITING;
						continue;
					}

					try {
						if (selector.select(2000) == 0) {
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
								// try {
								if (channel.isConnected() && channel.receive(rxBuffer) != null) {
									if (rxBuffer.position() > 0) {
										((Buffer) rxBuffer).flip();
										while (rxBuffer.hasRemaining()) {
											c = rxBuffer.get();
											forewardBuffer[forewardCount++] = c;
											bcount++;
											reader.put(c);
										}
										rxBuffer.compact();
										while ((msg = reader.getNextMessage()) != null) {
											listener_list = listeners.get(msg.getClass());
											if (listener_list != null) {
												for (IMAVLinkListener listener : listener_list)
													listener.received(msg);
											}
											
											if (comm.isConnected()) {
												comm.foreward(forewardBuffer, forewardCount);
											}
											forewardCount = 0;
										}

										if ((System.currentTimeMillis() - start) > 500) {
											transfer_speed = bcount * 1000 / (System.currentTimeMillis() - start);
											bcount = 0;
											start = System.currentTimeMillis();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						try {
							selector.close();
						} catch (IOException e1) {
						}
						state = WAITING;
					}
				}
			}
		}

	}

}
