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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mavlink.messages.IMAVLinkMessageID;
import org.mavlink.messages.MAVLinkMessage;
import org.mavlink.messages.MAV_CMD;
import org.mavlink.messages.MAV_COMPONENT;
import org.mavlink.messages.MAV_SEVERITY;
import org.mavlink.messages.MAV_STATE;
import org.mavlink.messages.MAV_TYPE;
import org.mavlink.messages.SERIAL_CONTROL_DEV;
import org.mavlink.messages.SERIAL_CONTROL_FLAG;
import org.mavlink.messages.lquac.msg_command_long;
import org.mavlink.messages.lquac.msg_event;
import org.mavlink.messages.lquac.msg_heartbeat;
import org.mavlink.messages.lquac.msg_serial_control;
import org.mavlink.messages.lquac.msg_statustext;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.comm.IMAVProxy;
import com.comino.mavcom.comm.proxy.MAVUdpProxyNIO2;
import com.comino.mavcom.comm.serial.MAVSerialComm;
import com.comino.mavcom.comm.udp.MAVUdpCommNIO2;
import com.comino.mavcom.config.MSPConfig;
import com.comino.mavcom.config.MSPParams;
import com.comino.mavcom.control.IMAVCmdAcknowledge;
import com.comino.mavcom.control.IMAVController;
import com.comino.mavcom.control.IMAVMSPController;
import com.comino.mavcom.log.IMAVMessageListener;
import com.comino.mavcom.log.MSPLogger;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.mavlink.MAVAcknowledge;
import com.comino.mavcom.mavlink.MAVLinkBlockingReader;
import com.comino.mavcom.mavlink.MAVTimeSync;
import com.comino.mavcom.messaging.MessageBus;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.LogMessage;
import com.comino.mavcom.model.segment.Status;
import com.comino.mavcom.status.StatusManager;
import com.comino.mavcom.status.listener.IMSPStatusChangedListener;
import com.comino.mavutils.hw.HardwareAbstraction;
import com.comino.mavutils.workqueue.WorkQueue;
import com.fazecast.jSerialComm.SerialPort;

public class MAVProxyController implements IMAVMSPController, Runnable {

	protected String peerAddress = null;

	protected static IMAVMSPController controller = null;

	protected IMAVComm comm = null;

	protected DataModel model  = null;
	protected IMAVProxy proxy1 = null;
	protected IMAVProxy proxy2 = null;

	private static final String DEFAULT_BAUDRATE = "57600";


	private static final msg_heartbeat beat_gcs = new msg_heartbeat(2, MAV_COMPONENT.MAV_COMP_ID_ONBOARD_COMPUTER);
	private static final msg_heartbeat beat_px4 = new msg_heartbeat(1, MAV_COMPONENT.MAV_COMP_ID_ONBOARD_COMPUTER);
	private static final msg_heartbeat beat_obs = new msg_heartbeat(1, MAV_COMPONENT.MAV_COMP_ID_OBSTACLE_AVOIDANCE);

	private StatusManager status_manager = null;
	private List<IMAVMessageListener> mavlinkListener = null;
	private final MAVLinkBlockingReader reader;

	private final WorkQueue wq = WorkQueue.getInstance();

	private int mode;

	public static IMAVController getInstance() {
		return controller;
	}

	public MAVProxyController(int mode, MSPConfig config) {

		this.mode = mode;

		controller = this;
		model = new DataModel();
		reader = new MAVLinkBlockingReader(2, model);
		status_manager = new StatusManager(model,false);
		mavlinkListener = new ArrayList<IMAVMessageListener>();

		MessageBus.getInstance();

		String baudrate = config.getProperty(MSPParams.BAUDRATE, DEFAULT_BAUDRATE);

		model.sys.setSensor(Status.MSP_MSP_AVAILABILITY, true);
		model.sys.setStatus(Status.MSP_SITL, mode == MAVController.MODE_NORMAL);
		model.sys.setStatus(Status.MSP_PROXY, true);

		beat_gcs.type = MAV_TYPE.MAV_TYPE_ONBOARD_CONTROLLER;
		beat_gcs.system_status = MAV_STATE.MAV_STATE_ACTIVE;

		beat_px4.type = MAV_TYPE.MAV_TYPE_ONBOARD_CONTROLLER;
		beat_px4.system_status = MAV_STATE.MAV_STATE_ACTIVE;

		beat_obs.type = MAV_TYPE.MAV_TYPE_ONBOARD_CONTROLLER;
		beat_obs.system_status = MAV_STATE.MAV_STATE_ACTIVE;

		status_manager.addListener(StatusManager.TYPE_MSP_STATUS, Status.MSP_CONNECTED, StatusManager.EDGE_RISING,
				(a) -> {
					System.out.println("Connection to device established...");
					model.sys.setStatus(Status.MSP_SITL, !comm.isSerial());
				});

		status_manager.addListener(StatusManager.TYPE_MSP_STATUS, Status.MSP_CONNECTED, StatusManager.EDGE_FALLING,
				(a) -> {
					model.sys.setStatus(Status.MSP_ACTIVE, false);
					System.out.println("Connection to device lost...");
				});

		status_manager.addListener(StatusManager.TYPE_MSP_STATUS, Status.MSP_GCL_CONNECTED, StatusManager.EDGE_RISING,
				(a) -> {
					System.out.println("Connection to GCS established...");
					proxy1.enableProxy(true);
				});

		status_manager.addListener(StatusManager.TYPE_MSP_STATUS, Status.MSP_GCL_CONNECTED, StatusManager.EDGE_FALLING,
				(a) -> {
					proxy1.enableProxy(false);
					System.out.println("Connection to GCS lost...");
				});

		switch (mode) {
		case MAVController.MODE_NORMAL:

			comm = MAVSerialComm.getInstance(reader,baudrate,SerialPort.FLOW_CONTROL_CTS_ENABLED | SerialPort.FLOW_CONTROL_RTS_ENABLED);
			comm.open();
			sendMAVLinkMessage(beat_px4);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}

			if (HardwareAbstraction.instance().getArchId() == HardwareAbstraction.JETSON) {
				proxy1 = new MAVUdpProxyNIO2(model, "172.168.178.2", 14550, null, 14555, comm);
				peerAddress = "172.168.178.22";
			} else {
				proxy1 = new MAVUdpProxyNIO2(model, "172.168.178.2", 14550, null, 14555, comm);
				peerAddress = "172.168.178.1";
			}
			System.out.println("Proxy Controller loaded: " + peerAddress);
			model.sys.setStatus(Status.MSP_SITL, false);
			break;

		case MAVController.MODE_ORIN:
			// comm = MAVSerialComm.getInstance(model, BAUDRATE_15, false);
			// comm = MAVSerialComm.getInstance(model, BAUDRATE_20, false);

			comm = MAVSerialComm.getInstance(reader,"ttyTHS0@921600",SerialPort.FLOW_CONTROL_DISABLED );
			comm.open();
			sendMAVLinkMessage(beat_px4);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}

			//			comm = new MAVUdpCommNIO2(reader, "127.0.0.1", 14541, 14587,false);
			proxy1 = new MAVUdpProxyNIO2(model, "192.168.178.156", 14650, null, 14656, comm);
			peerAddress = "192.168.178.46";

			System.out.println("Proxy Controller loaded (ORIN): " + peerAddress);
			model.sys.setStatus(Status.MSP_SITL, false);
			break;

		case MAVController.MODE_SITL:
			model.sys.setStatus(Status.MSP_SITL, true);
			comm = new MAVUdpCommNIO2(reader, "127.0.0.1", 14580, 14540,false);
			proxy1 = new MAVUdpProxyNIO2(model, "127.0.0.1", 14650, "0.0.0.0", 14656, comm);
			peerAddress = "127.0.0.1";
			System.out.println("Proxy Controller (SITL mode) loaded");
			break;
		case MAVController.MODE_USB:
			// comm = MAVSerialComm.getInstancqe(model, BAUDRATE_15, false);
			comm = MAVSerialComm.getInstance(new MAVLinkBlockingReader(3, model), DEFAULT_BAUDRATE,SerialPort.FLOW_CONTROL_DISABLED);
			// comm = MAVSerialComm.getInstance(model, BAUDRATE_9, false);
			comm.open();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			proxy1 = new MAVUdpProxyNIO2(model, "127.0.0.1", 14650, "0.0.0.0", 14656, comm);
			peerAddress = "127.0.0.1";
			System.out.println("Proxy Controller (serial mode) loaded: " + peerAddress);
			model.sys.setStatus(Status.MSP_SITL, false);
			break;
		case MAVController.MODE_SITL_PROXY:

			comm = new MAVUdpCommNIO2(reader, "10.211.55.8", 14541, 14587,false);
			proxy1 = new MAVUdpProxyNIO2(model, "10.211.55.2", 14650, "0.0.0.0", 14656, comm);
			proxy2 = new MAVUdpProxyNIO2(model, "10.211.55.2", 14750, "0.0.0.0", 14657, comm);
			peerAddress = "10.211.55.2";
			System.out.println("SITL Proxy Controller 1 and 2 loaded: " + peerAddress);
			model.sys.setStatus(Status.MSP_SITL, true);
			break;
		}

		// Direct byte based proxy
		comm.setProxyListener(proxy1);
		if(proxy2!=null)
			comm.setProxyListener(proxy2);

	}

	@Override
	public boolean sendMAVLinkMessage(MAVLinkMessage msg) {

		try {
			if (msg.sysId == 2) {
				if (model.sys.isStatus(Status.MSP_GCL_CONNECTED))
					proxy1.write(msg);
			} else 
				comm.write(msg);
			return true;
		} catch (Exception e1) {
			return false;
		}

	}

	@Override
	public boolean sendMAVLinkCmd(int command, float... params) {
		return sendMAVLinkCmd(command, null, params);
	}

	@Override
	public boolean sendMAVLinkCmd(int command, IMAVCmdAcknowledge callback, float... params) {

		msg_command_long cmd = new msg_command_long(255, 1);
		cmd.target_system = 1;
		cmd.target_component = 1;
		cmd.command = command;
		cmd.confirmation = 1;

		for (int i = 0; i < params.length; i++) {
			switch (i) {
			case 0:
				cmd.param1 = params[0];
				break;
			case 1:
				cmd.param2 = params[1];
				break;
			case 2:
				cmd.param3 = params[2];
				break;
			case 3:
				cmd.param4 = params[3];
				break;
			case 4:
				cmd.param5 = params[4];
				break;
			case 5:
				cmd.param6 = params[5];
				break;
			case 6:
				cmd.param7 = params[6];
				break;
			}
		}
		if (callback != null)
			reader.getParser().setCmdAcknowledgeListener(command, new MAVAcknowledge(callback, cmd, comm, 1));
		return sendMAVLinkMessage(cmd);
	}

	@Override
	public int getMode() {
		return mode;
	}

	@Override
	public boolean sendShellCommand(String s) {
		String command = s + "\n";
		msg_serial_control msg = new msg_serial_control(1, 1);
		try {
			byte[] bytes = command.getBytes("US-ASCII");
			for (int i = 0; i < bytes.length && i < 70; i++)
				msg.data[i] = bytes[i];
			msg.count = bytes.length;
			msg.device = SERIAL_CONTROL_DEV.SERIAL_CONTROL_DEV_SHELL;
			msg.flags = SERIAL_CONTROL_FLAG.SERIAL_CONTROL_FLAG_RESPOND;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		sendMAVLinkMessage(msg);
		System.out.println("ShellCommand executed: " + s);
		return true;
	}

	@Override
	public boolean sendMSPLinkCmd(int command, float... params) {
		MSPLogger.getInstance().writeLocalMsg("Command rejected: Proxy cannot send command to itself...");
		return false;
	}

	public void registerListener(Class<?> clazz, IMAVLinkListener listener) {
		proxy1.registerListener(clazz, listener);
		if(proxy2!=null)
			proxy2.registerListener(clazz, listener);
	}

	public boolean isConnected() {
		// model.sys.setStatus(Status.MSP_ACTIVE, comm.isConnected());
		if (mode == MAVController.MODE_NORMAL) {
			if(proxy2!=null)
				return (proxy1.isConnected() || proxy2.isConnected() ) && comm.isConnected();
			return proxy1.isConnected() && comm.isConnected();
		}
		if(proxy2!=null)
			return proxy1.isConnected() || proxy2.isConnected();

		return proxy1.isConnected();
	}

	@Override
	public boolean connect() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		comm.open();
		proxy1.open();
		if(proxy2!=null)
			proxy2.open();

		if (comm.isConnected()) {
			sendMAVLinkCmd(MAV_CMD.MAV_CMD_REQUEST_AUTOPILOT_CAPABILITIES, 1);
		}
		model.sys.resetStatus();
		return true;
	}

	@Override
	public boolean close() {
		proxy1.close();
		if(proxy2!=null)
			proxy2.close();
		comm.close();
		return true;
	}

	@Override
	public void shutdown() {
		proxy1.shutdown();
		if(proxy2!=null)
			proxy2.shutdown();
	}

	@Override
	public DataModel getCurrentModel() {
		return model;
	}

	@Override
	public boolean isSimulation() {
		return !comm.isSerial() && mode != MAVController.MODE_ORIN;
	}

	@Override
	public void addStatusChangeListener(IMSPStatusChangedListener listener) {
		status_manager.addListener(listener);

	}

	@Override
	public void addMAVLinkListener(IMAVLinkListener listener) {
		reader.getParser().addMAVLinkListener(listener);
	}

	@Override
	public void addMAVLinkListener(Class<?> clazz, IMAVLinkListener listener) {
		reader.getParser().registerListener(clazz, listener);
	}

	@Override
	public void addMAVMessageListener(IMAVMessageListener listener) {
		mavlinkListener.add(listener);
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
		proxy1.write(msg);
		if(proxy2!=null)
			proxy2.write(msg);
		if (mavlinkListener != null) {
			for (IMAVMessageListener msglistener : mavlinkListener)
				msglistener.messageReceived(m);
		}
	}

	@Override
	public String getConnectedAddress() {
		return peerAddress;
	}

	@Override
	public int getErrorCount() {
		return comm.getErrorCount();
	}

	@Override
	public String enableFileLogging(boolean enable, String directory) {
		return null;
	}

	@Override
	public StatusManager getStatusManager() {
		return status_manager;
	}

	@Override
	public boolean start() {

		status_manager.start();	
		wq.addCyclicTask("NP", 250, this);	
		wq.addSingleTask("LP", 5000, () -> new MAVTimeSync(comm));


		// Register processing of PING sent by GCL
		proxy1.registerListener(msg_heartbeat.class, (o) -> {
			model.sys.gcl_tms = System.currentTimeMillis() * 1000L;
			model.sys.setStatus(Status.MSP_GCL_CONNECTED, true);
		});

		if(proxy2!=null) {
			proxy2.registerListener(msg_heartbeat.class, (o) -> {
				model.sys.gcl_tms = System.currentTimeMillis() * 1000L;
				model.sys.setStatus(Status.MSP_GCL_CONNECTED, true);
			});
		}

		// FWD PX4 heartbeat messages to GCL when not connected
		reader.getParser().addMAVLinkListener((o) -> {
			if (!model.sys.isStatus(Status.MSP_GCL_CONNECTED) && o instanceof msg_heartbeat) {
				proxy1.write((MAVLinkMessage) o);
				if(proxy2!=null)
					proxy2.write((MAVLinkMessage) o);
			}
		});



		if (isSimulation()) {
			System.out.println("Setup MAVLink streams for simulation mode");
			sendMAVLinkCmd(MAV_CMD.MAV_CMD_SET_MESSAGE_INTERVAL, IMAVLinkMessageID.MAVLINK_MSG_ID_HIGHRES_IMU, 50000);
			sendMAVLinkCmd(MAV_CMD.MAV_CMD_SET_MESSAGE_INTERVAL,
					IMAVLinkMessageID.MAVLINK_MSG_ID_VISION_POSITION_ESTIMATE, 50000);
			sendMAVLinkCmd(MAV_CMD.MAV_CMD_SET_MESSAGE_INTERVAL, IMAVLinkMessageID.MAVLINK_MSG_ID_ATTITUDE_TARGET,
					20000);
		}

		return true;
	}

	int count=0;

	@Override
	public void run() {

		//	count++;

		if (!model.sys.isStatus(Status.MSP_GCL_CONNECTED)) {
			count++;
			if((count % 10) == 0) {
				if (HardwareAbstraction.instance().getArchId() == HardwareAbstraction.JETSON && !this.isSimulation()) { 
					setupWifi(); 
					proxy1.open();
					return;
				} 
			}
		}

		if (!model.sys.isStatus(Status.MSP_GCL_CONNECTED)) {
			proxy1.broadcast();
		}

		sendMAVLinkMessage(beat_px4);
		//	sendMAVLinkMessage(beat_obs);

		if (!proxy1.isConnected()) {
			proxy1.close();
			proxy1.open();
		}


		if (proxy2!=null && !proxy2.isConnected()) {
			proxy2.close();
			proxy2.open();
		}

		if (!comm.isConnected()) {
			comm.open();
			model.sys.setStatus(Status.MSP_ACTIVE, true);
		}

	}

	@Override
	public long getTransferRate() {
		return proxy1.getTransferRate();
	}

	private void setupWifi() {

		// TODO: Does not work: Should only restart if 
		if(!comm.isConnected() || !comm.isSerial())
			return;

		System.out.println("Restart wlan0 interface..");
		executeConsoleCommand("ifdown wlan0");
		executeConsoleCommand("ifup wlan0");
	}

	private void executeConsoleCommand(String command) {
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			MSPLogger.getInstance().writeLocalMsg("LINUX command '"+command+"' failed: "+e.getMessage(),
					MAV_SEVERITY.MAV_SEVERITY_CRITICAL);
		}
	}

}
