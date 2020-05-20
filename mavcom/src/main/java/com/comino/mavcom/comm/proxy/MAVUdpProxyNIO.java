/****************************************************************************
 *
 * Copyright (c) 2017-2019 Eike Mansfeld ecm@gmx.de. All rights reserved.
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
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.mavlink.messages.MAVLinkMessage;
import org.mavlink.messages.lquac.msg_heartbeat;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.mavlink.MAVLinkReader;
import com.comino.mavutils.legacy.ExecutorService;


public class MAVUdpProxyNIO implements IMAVLinkListener, Runnable {

	private SocketAddress 			bindPort = null;
	private SocketAddress 			peerPort;
	private DatagramChannel 			channel = null;

	private HashMap<Class<?>,List<IMAVLinkListener>> listeners = null;

	private MAVLinkReader 			reader;
	private Selector 				selector;
	private IMAVComm 				comm;

	private boolean 				isConnected   = false;
	private boolean					proxy_enabled = false;

	private final ByteBuffer 		rxBuffer = ByteBuffer.allocate(4096);

	private List<IMAVLinkListener> listener_list = null;
	private long                   transfer_speed = 0;


	public MAVUdpProxyNIO(String peerAddress, int pPort, String bindAddress, int bPort, IMAVComm comm) {

		peerPort = new InetSocketAddress(peerAddress, pPort);
		bindPort = new InetSocketAddress(bindAddress, bPort);
		reader = new MAVLinkReader(1);

		this.comm = comm;

		listeners = new HashMap<Class<?>,List<IMAVLinkListener>>();

		System.out.println("Proxy (NIO3): BindPort="+bPort+" PeerPort="+pPort+ " BufferSize: "+rxBuffer.capacity());

	}

	public boolean open() {

		if(channel!=null && channel.isConnected()) {
			isConnected = true;
			return true;
		}


		while(!isConnected) {
			try {

				isConnected = true;
				//						System.out.println("Connect to UDP channel");
				try {
					channel = DatagramChannel.open();
					channel.socket().bind(bindPort);
					//		channel.socket().setTrafficClass(0x10);
					//		channel.socket().setBroadcast(true);
					channel.socket().setSendBufferSize(32*1024);
					channel.socket().setReceiveBufferSize(32*1024);
					channel.configureBlocking(false);

					Thread.sleep(100);

				} catch(java.net.BindException b) {
					System.err.println("Connection error: "+b.getLocalizedMessage());
					System.exit(-1);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				channel.connect(peerPort);
				selector = Selector.open();
				((Buffer)rxBuffer).clear();


				Thread t = new Thread(this);
				t.setName("Proxy worker");
				t.start();

				//				ExecutorService.submit(this);


				return true;
			} catch(Exception e) {
				System.err.println(e.getLocalizedMessage());
				try {
					channel.disconnect();
					channel.close();
				} catch (IOException e1) { }
				isConnected = false;
			}
		}
		return false;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public boolean isProxyEnabled() {
		return proxy_enabled;
	}

	public void enableProxy(boolean enable) {
		this.proxy_enabled = enable;
	}


	public void close() {
		isConnected = false;
		((Buffer)rxBuffer).clear();
		try {
			if(selector!=null)
				selector.close();
			if (channel != null) {
				channel.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void registerListener(Class<?> clazz, IMAVLinkListener listener) {

		List<IMAVLinkListener> list = null;
		if(listeners.containsKey(clazz)) {
			list = listeners.get(clazz);
			if(!list.contains(listener)) {
				list.add(listener);
				System.out.println("Register MavLink listener: "+clazz.getSimpleName()+" : "+listener.getClass().getName());
			}
		} else {
			list  = new ArrayList<IMAVLinkListener>();
			list.add(listener);
			listeners.put(clazz, list);
			System.out.println("Register MavLink listener: "+clazz.getSimpleName()+" : "+listener.getClass().getName());
		}
	}

	public void unregisterListener(Class<?> clazz) {
		listeners.remove(clazz);
	}

	@Override
	public void run() {

		SelectionKey key = null;
		MAVLinkMessage msg = null;
		Iterator<?> selectedKeys = null; long bcount = 0; long start;

		try {
			channel.register(selector, SelectionKey.OP_READ );

			bcount = 0;

			if(comm.isConnected()) {
				msg_heartbeat hb = new msg_heartbeat(255,1);
				hb.isValid = true;
				comm.write(hb);
			} else {
				isConnected = false;
				return;
			}

            start = System.currentTimeMillis();
			while(isConnected) {

				if(selector.select(1000)==0)
					continue;

				selectedKeys = selector.selectedKeys().iterator();

				while (selectedKeys.hasNext()) {
					key = (SelectionKey) selectedKeys.next();
					selectedKeys.remove();

					if (!key.isValid()) {
						continue;
					}

					if (key.isReadable()) {
						try {
							if(channel.isConnected() && channel.receive(rxBuffer)!=null) {
								if(rxBuffer.position()>0) {
									((Buffer)rxBuffer).flip();
									while(rxBuffer.hasRemaining()) {
										bcount++;
										reader.put(rxBuffer.get());
									}
									rxBuffer.compact();
									while((msg=reader.getNextMessage())!=null) {
										listener_list = listeners.get(msg.getClass());
										if(listener_list!=null) {
											for(IMAVLinkListener listener : listener_list)
												listener.received(msg);
										}
										if(comm.isConnected())
											comm.write(msg);
									}

									transfer_speed = bcount * 1000 / (System.currentTimeMillis() - start);
								}
							}
						} catch(Exception io) { }
					}
				}
			}
			close();
		} catch(Exception e) {
			close();
			isConnected = false;
		}
	}


	public int getBadCRC() {
		return 0;
	}

	public  void write(MAVLinkMessage msg)  {
		if(msg!=null && channel!=null && channel.isConnected() && isConnected) {
			try {
				channel.write(ByteBuffer.wrap(msg.encode()));

			} catch (IOException e) {}
		}

	}

	@Override
	public void received(Object o) {
		if(proxy_enabled) {
			write((MAVLinkMessage) o);
		}
	}

	public long getTransferRate() {
		return transfer_speed;
	}

}
