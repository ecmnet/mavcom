/****************************************************************************
 *
 *   Copyright (c) 2017,2022 Eike Mansfeld ecm@gmx.de. All rights reserved.
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

package com.comino.mavcom.control.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.mavlink.messages.MAV_COMPONENT;
import org.mavlink.messages.MAV_STATE;
import org.mavlink.messages.MAV_TYPE;
import org.mavlink.messages.lquac.msg_heartbeat;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.comm.serial.MAVSerialComm;
import com.comino.mavcom.comm.udp.MAVUdpCommNIO2;
import com.comino.mavcom.control.IMAVController;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.Status;
import com.fazecast.jSerialComm.SerialPort;

public class MAVAutoController extends MAVController implements IMAVController, Runnable {
	
	private static final int BROADCAST_PORT = 4445;

	private boolean connected;

	private final msg_heartbeat beat = new msg_heartbeat(2, MAV_COMPONENT.MAV_COMP_ID_OSD);
	private final IMAVComm[] comms = new IMAVComm[7];
	private String host;
	private DatagramSocket socket;
	
	public MAVAutoController(String peerAddress, int peerPort, int bindPort) {
		super(2);
		this.peerAddress = peerAddress;
		this.peerPort = peerPort;
		this.bindPort = bindPort;

		comms[0] = MAVSerialComm.getInstance(reader, "57800",SerialPort.FLOW_CONTROL_DISABLED);
		comms[1] = new MAVUdpCommNIO2(reader, peerAddress, peerPort, bindPort,true);
		comms[2] = new MAVUdpCommNIO2(reader, "127.0.0.1", 14656, 14650,false);
		comms[3] = new MAVUdpCommNIO2(reader, "127.0.0.1", 14580, 14540,false);
	    comms[4] = new MAVUdpCommNIO2(reader, "10.211.55.8", 14656, 14650, true);
	    comms[5] = new MAVUdpCommNIO2(reader, "192.168.178.46", 14656, 14650, true);
	  
	   
	    try {
			socket = new DatagramSocket(BROADCAST_PORT);
		} catch (SocketException e) {
		}
	   
		model.sys.setStatus(Status.MSP_PROXY, false);

		beat.type = MAV_TYPE.MAV_TYPE_GCS;
		beat.system_status = MAV_STATE.MAV_STATE_ACTIVE;
		System.out.println("Auto Controller loaded (" + peerAddress + ":" + peerPort + ")");
	}

	@Override
	public boolean connect() {
		
		if(comms[0]==null)
			return false;
		

		if (comm != null && comm.isConnected())
			return true;
		

		if (comms[0].open()) {
			comm = comms[0];
			this.isSITL = false;
			this.mode = MODE_USB;
			model.sys.setStatus(Status.MSP_SITL, false);
			this.connected = true;
			return true;
		} 
		
		try {
			
			if(host==null)
				host = listenToBroadcast();
			
			 for(int i=1;i<comms.length;i++) {
		    	   if(comms[i] != null && comms[i].getHost().equals(host)) {
		    			if (comms[i].open()) {
		    				comm = comms[i];
		    				this.isSITL = host.startsWith("10") || host.startsWith("127");
		    				if(isSITL)
		    				   this.mode = MODE_SITL;
		    				else
		    				   this.mode = MODE_NORMAL;
		    				model.sys.setStatus(Status.MSP_SITL, isSITL);
		    				this.connected = true;
		    		 } 
		    	   } 
		       }
				
			return true;
		} catch (IOException e) {
		}

		return true;
	}
	
	private String listenToBroadcast() throws IOException {

		byte[] buf = new byte[5];
		
		socket.setSoTimeout(200);
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
	
	private void sendDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm:ss YYYY", Locale.ENGLISH);   
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String s = sdf.format(new Date());
		System.out.println("Sending date & time: "+s);
		this.sendShellCommand("date -s \""+s+"\"");
	}

	@Override
	public boolean close() {
		super.close();
		this.connected = false;
		return true;
	}

	@Override
	public boolean isConnected() {
		return model.sys.isStatus(Status.MSP_CONNECTED) && comm!=null;
	}
	
	@Override
	public boolean isSimulation() {
		return isSITL;
	}
	
	@Override
	public String getConnectedAddress() {
		return comm.getHost();
	}

	@Override
	public void run() {
		super.run();
		
		if (comm == null) {
			 connect();
			return;
		}
		
		try {
			if (!comm.isConnected() ) {
				close(); connect();
			}
			this.connected = true;
			comm.write(beat);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
