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
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class MAVAutoController extends MAVController implements IMAVController, Runnable {

	private boolean connected;

	private final msg_heartbeat beat = new msg_heartbeat(2, MAV_COMPONENT.MAV_COMP_ID_OSD);
	private final IMAVComm[] comms = new IMAVComm[4];
	private boolean isMSP = true;
	private int     last=0;

	public MAVAutoController(String peerAddress, int peerPort, int bindPort) {
		super(2);
		this.peerAddress = peerAddress;
		this.peerPort = peerPort;
		this.bindPort = bindPort;

		comms[0] = MAVSerialComm.getInstance(reader, 57600);
		comms[1] = new MAVUdpCommNIO2(reader, peerAddress, peerPort, bindPort);
		comms[2] = new MAVUdpCommNIO2(reader, "127.0.0.1", 14580, 14540);
		comms[3] = new MAVUdpCommNIO2(reader, "127.0.0.1", 14656, 14650);

		model.sys.setStatus(Status.MSP_PROXY, false);

		beat.type = MAV_TYPE.MAV_TYPE_GCS;
		beat.system_status = MAV_STATE.MAV_STATE_ACTIVE;
		System.out.println("Auto Controller loaded (" + peerAddress + ":" + peerPort + ")");
	}

	@Override
	public boolean connect() {

		if (comm != null && comm.isConnected())
			return true;

		if (comms[0].open()) {
			comm = comms[0];
			this.isSITL = false;
			this.isMSP  = false;
			this.mode = MODE_USB;
			model.sys.setStatus(Status.MSP_SITL, false);
			if(last != comm.hashCode())
			  System.out.println(comm);
			last = comm.hashCode();
			this.connected = true;
			return true;
		}

		if (comms[1].open()) {
			comm = comms[1];
			this.isSITL = false;
			this.mode = MODE_NORMAL;
			this.isMSP  = true;
//			comms[2].shutdown();
//			comms[3].shutdown();
			model.sys.setStatus(Status.MSP_SITL, false);
			if(last != comm.hashCode())
			  System.out.println(comm);
			last = comm.hashCode();
			this.connected = true;
			return true;
		}

		if (comms[2].open()) {
			comm = comms[2];
			comms[3].shutdown();
			this.isSITL = true;
			this.isMSP  = false;
			this.mode = MODE_SITL;
			model.sys.setStatus(Status.MSP_SITL, true);
			if(last != comm.hashCode())
			  System.out.println(comm);
			last = comm.hashCode();
			this.connected = true;
			return true;
		}
		
		if (comms[3].open()) {
			comm = comms[3];
			comms[2].shutdown();
			this.isSITL = true;
			this.isMSP  = true;
			this.mode = MODE_SITL_PROXY;
			model.sys.setStatus(Status.MSP_SITL, true);
			if(last != comm.hashCode())
			  System.out.println(comm+" PROXY");
			last = comm.hashCode();
			this.connected = true;
			return true;
		}
	

		return true;
	}
	
	private void sendDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm:ss YYYY");   
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String s = sdf.format(new Date());
		System.out.println("Sending date & time: "+s);
		this.sendShellCommand("date -s \""+s+"\"");
	}

	@Override
	public boolean close() {
		super.close();
		this.connected = false;
		model.sys.setStatus(Status.MSP_CONNECTED, false);
		return true;
	}

	@Override
	public boolean isConnected() {
		return model.sys.isStatus(Status.MSP_CONNECTED);
	}

	@Override
	public void run() {
		super.run();
		
		if (comm == null)
			return;
		
		try {
			if (!comm.isConnected() ||!model.sys.isStatus(Status.MSP_CONNECTED)) {
				close();
				connected = false;
				model.sys.setStatus(Status.MSP_CONNECTED,false);
				connect();
			}
			this.connected = true;
			model.sys.setStatus(Status.MSP_SITL, isSimulation());
			comm.write(beat);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
