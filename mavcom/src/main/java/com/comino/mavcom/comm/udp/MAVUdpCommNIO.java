/****************************************************************************
 *
 *   Copyright (c) 2017,2018 Eike Mansfeld ecm@gmx.de. All rights reserved.
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

package com.comino.mavcom.comm.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

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

public class MAVUdpCommNIO implements IMAVComm, Runnable {

	private static final int BUFFER = 128;

	private DataModel model = null;

	private SocketAddress bindPort = null;
	private SocketAddress peerPort = null;
	private DatagramChannel channel = null;

	private boolean isConnected = false;
	private long transfer_speed = 0;

	private MAVLinkBlockingReader reader;

	private Selector selector;

	private static MAVUdpCommNIO com = null;
	private IMAVProxy byteListener = null;

	private final ByteBuffer rxBuffer = ByteBuffer.allocate(BUFFER * 1024);
	private final byte[] proxyBuffer = new byte[rxBuffer.capacity()];

	private String peerAddress;

	public static MAVUdpCommNIO getInstance(MAVLinkBlockingReader reader, String peerAddress, int peerPort,
			int bindPort) {
		if (com == null)
			com = new MAVUdpCommNIO(reader, peerAddress, peerPort, bindPort);
		return com;
	}

	private MAVUdpCommNIO(MAVLinkBlockingReader reader, String peerAddress, int pPort, int bPort) {
		this.model = reader.getModel();
		this.peerPort = new InetSocketAddress(peerAddress, pPort);
		this.bindPort = new InetSocketAddress(bPort);
		this.reader = reader;
		this.peerAddress   = peerAddress;

		System.out.println(
				"Vehicle (NIO3): BindPort=" + bPort + " PeerPort=" + pPort + " BufferSize: " + rxBuffer.capacity());

	}
	

	public boolean open() {

		if (isConnected)
			return true;

		reader.getParser().reset();
		Arrays.fill(proxyBuffer, (byte) 0);

		if (channel != null && channel.isOpen() && channel.isConnected()) { // && parser.isConnected()) {
			isConnected = true;
			return true;
		}

		while (!isConnected) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e2) {
			}

			try {
				channel = DatagramChannel.open();
				channel.socket().setReuseAddress(true);
				channel.bind(bindPort);
				// channel.socket().setTrafficClass(0x08);
				channel.socket().setReceiveBufferSize(BUFFER * 1024);
				channel.socket().setSendBufferSize(32 * 1024);
				channel.connect(peerPort);
				channel.configureBlocking(false);
				selector = Selector.open();
				channel.register(selector, SelectionKey.OP_READ);

				if (channel.isConnected())
					isConnected = true;

			} catch (Exception e) {
				try {
					if (channel.isOpen()) {
						channel.disconnect();
						channel.close();
					}
				} catch (IOException e1) {
				}
				model.sys.setStatus(Status.MSP_CONNECTED, false);
				isConnected = false;
				continue;
			}
		}

		((Buffer) rxBuffer).clear();

		Thread t = new Thread(this);
		t.setName("MAVLink parser");
		t.setDaemon(true);
		t.start();

		return true;
	}

	@Override
	public void run() {
		SelectionKey key = null;
		Iterator<?> selectedKeys = null;
		long bcount = 0;
		int msg_length;
		long start;
		byte b;

		bcount = 0;

		if (channel.isConnected()) {
			isConnected = true;

			msg_heartbeat hb = new msg_heartbeat(255, 1);
			hb.isValid = true;
			try {
				for (int i = 0; i < 10; i++)
					write(hb);
			} catch (Exception e) {
			}
		} else
			isConnected = false;

		start = System.currentTimeMillis();

		while (isConnected) {

			try {

				if (selector.select(3000) == 0) {
					isConnected = false;
					continue;
				}

				selectedKeys = selector.selectedKeys().iterator();

				while (selectedKeys.hasNext()) {

					key = (SelectionKey) selectedKeys.next();
					selectedKeys.remove();

					if (!key.isValid())
						continue;

					if (key.isReadable()) {
						if (channel.isConnected() && channel.receive(rxBuffer) != null) {
							((Buffer) rxBuffer).flip();
							msg_length = 0;
							while (rxBuffer.hasRemaining()) {
								proxyBuffer[msg_length++] = rxBuffer.get();
							}
							rxBuffer.compact();

							if (byteListener != null)
								byteListener.write(proxyBuffer, msg_length);
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
				((Buffer) rxBuffer).clear();
				reader.getParser().reset();
				model.sys.setStatus(Status.MSP_CONNECTED, false);
				try {
					channel.close();
				} catch (IOException e1) {
				}
				isConnected = false;
			}
		}
		close();
	}

	@Override
	public int getErrorCount() {
		return reader.getLostPackages();
	}

	public void write(MAVLinkMessage msg) throws IOException {
		if (!channel.isConnected())
			throw new IOException("Not yet connected");
		if (msg != null && channel != null && channel.isOpen() && channel.isConnected() && isConnected)
			channel.write(ByteBuffer.wrap(msg.encode()));
	}

	@Override
	public boolean isSerial() {
		return false;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public DataModel getModel() {
		return model;
	}

	public void close() {
		isConnected = false;
		try {
			if (selector != null) {
				selector.close();
			}
			if (channel != null) {
				channel.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MAVUdpCommNIO comm = new MAVUdpCommNIO(new MAVLinkBlockingReader(2, new DataModel()), "172.168.178.1", 14555,
				14550);
		// MAVUdpComm comm = new MAVUdpComm(new DataModel(), "192.168.4.1",
		// 14555,"0.0.0.0",14550);

		comm.open();

		long time = System.currentTimeMillis();

		try {

			System.out.println("Started");

			while (System.currentTimeMillis() < (time + 60000)) {

				// comm.model.state.print("NED:");
				// System.out.println("REM="+comm.model.battery.p+"
				// VOLT="+comm.model.battery.b0+" CURRENT="+comm.model.battery.c0);

				if (comm.isConnected)
					System.out.println("ANGLEX=" + comm.model.hud.aX + " ANGLEY=" + comm.model.hud.aY + " "
							+ comm.model.sys.toString());

				Thread.sleep(100);

			}

			comm.close();

			// for(int i=0;i<colService.getModelList().size();i++) {
			// DataModel m = colService.getModelList().get(i);
			// System.out.println(m.attitude.aX);
			// }

		} catch (Exception e) {
			comm.close();
			e.printStackTrace();

		}

	}

	@Override
	public void writeMessage(LogMessage m) {
		reader.getParser().writeMessage(m);

	}

	@Override
	public long getTransferRate() {
		return transfer_speed;
	}

	@Override
	public void setProxyListener(IMAVProxy listener) {
		this.byteListener = listener;

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public MAVLinkBlockingReader getReader() {
		return reader;
	}
	
	@Override
	public String getHost() {
		return peerAddress;
	}

	@Override
	public void foreward(byte[] b, int len) throws IOException {
		try {
			
				channel.write(ByteBuffer.wrap(b));
		} catch (IOException e) {
		}
		
	}


}
