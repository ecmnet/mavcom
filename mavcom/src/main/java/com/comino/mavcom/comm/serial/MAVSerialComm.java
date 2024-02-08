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

package com.comino.mavcom.comm.serial;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

import org.mavlink.messages.MAVLinkMessage;
import org.mavlink.messages.lquac.msg_timesync;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.comm.IMAVProxy;
import com.comino.mavcom.log.IMAVMessageListener;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.mavlink.MAVAcknowledge;
import com.comino.mavcom.mavlink.MAVLinkBlockingReader;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.LogMessage;
import com.comino.mavcom.model.segment.Status;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

public class MAVSerialComm implements IMAVComm {

	private static final int TEST = 57600;

	private static final int BUFFER = 16;

	private final byte[] buf = new byte[BUFFER * 1024];

	private SerialPort serialPort;
	private String port;

	private DataModel model = null;

	private MAVLinkBlockingReader reader;

	private static IMAVComm com = null;
	private IMAVProxy byteListener = null;

	private int baudrate = 921600;
	private int flowcontrol = SerialPort.FLOW_CONTROL_DISABLED;

	private InputStream is;
	private OutputStream os;

	private boolean is_connected = false;

	public static IMAVComm getInstance(MAVLinkBlockingReader reader, String port_baudrate, int flowcontrol) {
		if (com == null)
			com = new MAVSerialComm(reader, port_baudrate, flowcontrol);
		return com;
	}

	private MAVSerialComm(MAVLinkBlockingReader reader, String port_baudrate, int flowcontrol) {


		String[] defs = port_baudrate.split("@");
		if(defs.length==2) {
			this.baudrate = Integer.parseInt(defs[1]);
			if(!searchPort(defs[0])) {
				System.out.println("Serial port " +defs[0] +" not found...");
				return;
			}

		} else {
			this.baudrate = Integer.parseInt(port_baudrate);
			if(!searchPort(null)) {
				System.out.println("No Serial port found...");
				return;
			}
		}

		this.model = reader.getModel();
		this.flowcontrol = flowcontrol;

		System.out.println("PX4 connection using "+port+" at "+baudrate+" baud");

		this.reader = reader;

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.comino.px4.control.serial.IPX4Comm#open()
	 */
	@Override
	public boolean open() {

		if(serialPort==null)
			return false;


		if (serialPort.isOpen()) {
			return true;
		}

		while (!open(port, baudrate, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY,flowcontrol)) {
			try {
				if (serialPort.isOpen()) {
					serialPort.removeDataListener();
					serialPort.closePort();
				}
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (!serialPort.isOpen()) {
			return false;
		}

		System.out.println("Serial port opened...");
		this.is = new BufferedInputStream(serialPort.getInputStream(), BUFFER * 1024 * 2);
		this.os = new BufferedOutputStream(serialPort.getOutputStream(), 2048);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.comino.px4.control.serial.IPX4Comm#getModel()
	 */
	@Override
	public DataModel getModel() {
		return model;
	}

	public int getUnread() {
		return reader.nbUnreadMessages();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.comino.px4.control.serial.IPX4Comm#close()
	 */
	@Override
	public void close() {
		model.sys.setStatus(Status.MSP_CONNECTED, false);
		is_connected = false;
		if (serialPort != null) {
			serialPort.removeDataListener();
			serialPort.closePort();
		}
		try {
			is.close();
			os.close();
		} catch (Exception e) {

		}
	}

	public boolean searchPort(String pattern) {
		int i; boolean found = false;

		SerialPort[] ports = SerialPort.getCommPorts();


		if (ports.length > 0) {
			for (i = 0; i < ports.length; i++) {

				if(serialPort!=null && ports[i].getSystemPortName().equals(serialPort.getSystemPortName()))
					return true;

				if (pattern != null && ports[i].getSystemPortName().contains(pattern)) {
					found = true;
					break;
				}

				if (pattern == null && (ports[i].getSystemPortName().contains("tty.SLAB")
						|| ports[i].getSystemPortName().contains("tty.usb")
						|| ports[i].getSystemPortName().contains("ttyTHS")
						|| ports[i].getSystemPortName().contains("ttyS1")
						|| ports[i].getSystemPortName().contains("ttyS4")
						|| ports[i].getSystemPortName().contains("ttyACM0")
						|| ports[i].getSystemPortName().contains("ttyAMA0")) 
						) {
					found = true;
					break;
				}
			}

			if (found)
				this.serialPort = ports[i];
		} else
			this.serialPort = SerialPort.getCommPort("/dev/tty.SLAB_USBtoUART");

		if (found) {
			this.port = serialPort.getSystemPortName();
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return "Serial " + serialPort.getDescriptivePortName() + " (" + baudrate + ")";
	}

	private boolean open(String portName, int baudRate, int dataBits, int stopBits, int parity, int flowControl) {


		if (serialPort == null)
			return false;

		if (serialPort.isOpen())
			return true;

		try {
			serialPort.setComPortParameters(baudRate, dataBits, stopBits, parity);
			serialPort.setFlowControl(flowControl);
			serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 500, 500);
			serialPort.addDataListener(new SerialPortDataListener() {
				int avail;


				@Override
				public int getListeningEvents() {
					return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
				}

				@Override
				public void serialEvent(SerialPortEvent event) {
					if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
						return;
					}

					try {
						avail = is.available();
						if (avail > 0) {
							avail = is.read(buf, 0, avail);
							if (avail < 5000) {
								if (byteListener != null)
									byteListener.write(buf, avail);
								reader.put(buf, avail);
								is_connected = true;
								model.sys.setStatus(Status.MSP_CONNECTED, true);
							}
						}
					} catch (Exception e) {
						//	e.printStackTrace();
					}
				}
			});


			serialPort.openPort();

			model.sys.tms = DataModel.getSynchronizedPX4Time_us();

		} catch (Exception e2) {
			e2.printStackTrace();
			close();
			return false;
		}
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.comino.px4.control.serial.IPX4Comm#write(org.mavlink.messages.
	 * MAVLinkMessage)
	 */
	@Override
	public void write(MAVLinkMessage msg) throws IOException {
		if (!serialPort.isOpen()) {
			return;
		}
		try {
			byte[] buffer = msg.encode();
			os.write(buffer, 0, buffer.length);
			os.flush();
		} catch (Exception e) {
			close();
			return;
		}
	}


	@Override
	public boolean isConnected() {
		return (serialPort != null && serialPort.isOpen() && is_connected);
	}

	@Override
	public void writeMessage(LogMessage m) {

	}

	@Override
	public boolean isSerial() {
		return true;
	}

	@Override
	public int getErrorCount() {
		return reader.getLostPackages();
	}

	@Override
	public long getTransferRate() {

		return 0;
	}

	@Override
	public void shutdown() {
		close();
	}

	@Override
	public void setProxyListener(IMAVProxy listener) {
		this.byteListener = listener;

	}

	@Override
	public MAVLinkBlockingReader getReader() {
		return reader;
	}

	@Override
	public String getHost() {
		return "Serial";
	}

	@Override
	public void foreward(byte[] b, int len) throws IOException {
		try {
			if(this.is_connected) {
				os.write(b, 0,len);
				os.flush();
			}
		} catch (IOException e) {
		}

	}

}
