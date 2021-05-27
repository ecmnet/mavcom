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

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.comm.IMAVProxy;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.mavlink.MAVLinkReader;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.Status;
import com.comino.mavutils.legacy.ExecutorService;


public class MAVUdpProxyNIO implements IMAVLinkListener, Runnable, IMAVProxy {

	private static final int BUFFER = 64;

	private SocketAddress 			bindPort = null;
	private SocketAddress 			peerPort;
	private DatagramChannel 		channel = null;

	private HashMap<Class<?>,List<IMAVLinkListener>> listeners = null;

	private MAVLinkReader 			reader;
	private Selector 				selector;
	private IMAVComm 				comm;

	private boolean 				isConnected   = false;
	private boolean					proxy_enabled = false;

	private final ByteBuffer 		rxBuffer = ByteBuffer.allocate(BUFFER*1024);

	private List<IMAVLinkListener> listener_list = null;
	private long                   transfer_speed = 0;

	private DataModel model;


	public MAVUdpProxyNIO(DataModel model,String peerAddress, int pPort, String bindAddress, int bPort, IMAVComm comm) {

		peerPort = new InetSocketAddress(peerAddress, pPort);
		bindPort = new InetSocketAddress(bindAddress, bPort);
		reader = new MAVLinkReader(1);

		this.comm = comm;
		this.model = model;

		listeners = new HashMap<Class<?>,List<IMAVLinkListener>>();

		System.out.println("Proxy (NIO3): BindPort="+bPort+" PeerPort="+pPort+ " BufferSize: "+rxBuffer.capacity());

	}

	@Override
	public boolean open() {

		if(channel!=null && channel.isConnected()) {
			isConnected = true;
			return true;
		}

			((Buffer)rxBuffer).clear(); 

			while(!isConnected) {

				isConnected = false;
				try { Thread.sleep(100); } catch (InterruptedException e2) { }

				try {

					//						System.out.println("Connect to UDP channel");
					try {
						channel = DatagramChannel.open();
						channel.socket().bind(bindPort);
						channel.socket().setTrafficClass(0x08);
						channel.socket().setSendBufferSize(BUFFER*1024);
						channel.socket().setReceiveBufferSize(BUFFER*1024);
						channel.configureBlocking(false);
						channel.socket().setReuseAddress(true);


					} catch(java.net.BindException b) {
						System.err.println("Connection error: "+b.getLocalizedMessage());
						continue;
					} catch (Exception e) {
						continue;
					}
					channel.connect(peerPort);
					selector = Selector.open();

					if(channel.isConnected())
						isConnected = true;


				} catch(Exception e) {
					System.err.println("Open "+e.getLocalizedMessage());
					try {
						channel.disconnect();
						channel.close();
					} catch (IOException e1) { }
					continue;
				}
			}
			
			((Buffer)rxBuffer).clear();

			Thread t = new Thread(this);
			t.setName("Proxy worker");
			t.start();

		return true;
	}

	@Override
	public boolean isConnected() {
		return isConnected;
	}

	@Override
	public boolean isProxyEnabled() {
		return proxy_enabled;
	}

	@Override
	public void enableProxy(boolean enable) {
		this.proxy_enabled = enable;
	}


	@Override
	public void close() {
		isConnected = false;
		((Buffer)rxBuffer).clear();
		try {
			if(selector!=null) {
				selector.close();
			}
			if (channel != null) {
				channel.disconnect();
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

	@Override
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

			if(!comm.isConnected()) {
				isConnected = false;
				return;
			}

			start = System.currentTimeMillis();
			while(isConnected) {

				if(selector.select(1000)==0) {
					isConnected = false;
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
						//						try {
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

								if((System.currentTimeMillis() - start) > 500) {
									transfer_speed = bcount * 1000 / (System.currentTimeMillis() - start);
									bcount = 0; start = System.currentTimeMillis();
								}
							}
						}
					}
				}
			}
		} catch(Exception e) { }
		close();
		isConnected = false;
	}


	@Override
	public int getBadCRC() {
		return 0;
	}

	@Override
	public void write(MAVLinkMessage msg)  {

		if(msg!=null && channel!=null && channel.isOpen() && isConnected) {
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

	@Override
	public long getTransferRate() {
		return transfer_speed;
	}


	@Override
	public void write(byte[] buffer, int length) {

		// Do not foreward data if GCL is not connected
		if(!model.sys.isStatus(Status.MSP_GCL_CONNECTED))
			return;

		if(channel != null && channel.isOpen() && channel.isConnected() && isConnected) {
			try {
				if(length > 0)
					channel.write(ByteBuffer.wrap(buffer,0,length));
			} catch (Exception e) { }
		} 
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcast() {
		// TODO Auto-generated method stub
		
	}

}
