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

package com.comino.mavcom.control.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mavlink.messages.MAV_COMPONENT;
import org.mavlink.messages.MAV_STATE;
import org.mavlink.messages.MAV_TYPE;
import org.mavlink.messages.lquac.msg_heartbeat;
import org.mavlink.messages.lquac.msg_statustext;

import com.comino.mavcom.comm.serial.MAVSerialComm;
import com.comino.mavcom.control.IMAVController;
import com.comino.mavcom.log.IMAVMessageListener;
import com.comino.mavcom.mavlink.MAVLinkBlockingReader;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.LogMessage;

/*
 * Direct serial controller up to 115200 baud for telem1 connections e.g. Radio
 */

public class MAVSerialController extends MAVController implements IMAVController {
	
	private static final msg_heartbeat beat_px4 = new msg_heartbeat(255,MAV_COMPONENT.MAV_COMP_ID_MISSIONPLANNER);
	private List<IMAVMessageListener> messageListener = null;
	
	public MAVSerialController() {
		super();
		System.out.println("Serial Controller loaded");
		comm = MAVSerialComm.getInstance(reader, 115200);
		beat_px4.system_status = MAV_STATE.MAV_STATE_ACTIVE;
		beat_px4.type = MAV_TYPE.MAV_TYPE_GCS;
		messageListener = new ArrayList<IMAVMessageListener>();
		

	}

	public MAVSerialController(int baud) {
		super();
		System.out.println("Direct serial Controller loaded");
		comm = MAVSerialComm.getInstance(new MAVLinkBlockingReader(3, model), baud);
		beat_px4.system_status = MAV_STATE.MAV_STATE_ACTIVE;
		beat_px4.type = MAV_TYPE.MAV_TYPE_GCS;
		messageListener = new ArrayList<IMAVMessageListener>();

	}

	@Override
	public boolean connect() {
		boolean ok = comm.open();
		return ok;
	}

	@Override
	public boolean isSimulation() {
		return false;
	}

	@Override
	public boolean isConnected() {
		return comm.isConnected();
	}

	@Override
	public boolean close() {
		comm.close();
		return true;
	}
	
	@Override
	public void run() {
		try {
			comm.write(beat_px4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!comm.isConnected()) {
			comm.close();
			comm.open();
		}
	}
	
	@Override
	public void addMAVMessageListener(IMAVMessageListener listener) {
		messageListener.add(listener);
	}
	
	@Override
	public void writeLogMessage(LogMessage m) {
		if (!m.isNew())
			return;

		this.model.msg = m;
		this.model.msg.tms = DataModel.getSynchronizedPX4Time_us();

		msg_statustext msg = new msg_statustext();
		msg.setText(m.text);
		msg.componentId = 1;
		msg.severity = m.severity;
		
		if (messageListener != null) {
			for (IMAVMessageListener msglistener : messageListener)
				msglistener.messageReceived(m);
		}
	}

}
