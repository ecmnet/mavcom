/****************************************************************************
 *
 *   Copyright (c) 2017,2019 Eike Mansfeld ecm@gmx.de. All rights reserved.
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.mavlink.messages.MAVLinkMessage;
import org.mavlink.messages.SERIAL_CONTROL_DEV;
import org.mavlink.messages.SERIAL_CONTROL_FLAG;
import org.mavlink.messages.lquac.msg_command_long;
import org.mavlink.messages.lquac.msg_msp_command;
import org.mavlink.messages.lquac.msg_serial_control;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.control.IMAVCmdAcknowledge;
import com.comino.mavcom.control.IMAVController;
import com.comino.mavcom.log.IMAVMessageListener;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.mavlink.MAVAcknowledge;
import com.comino.mavcom.mavlink.MAVLinkBlockingReader;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.LogMessage;
import com.comino.mavcom.model.segment.Status;
import com.comino.mavcom.status.StatusManager;
import com.comino.mavcom.status.listener.IMSPStatusChangedListener;
import com.comino.mavutils.workqueue.WorkQueue;

import us.ihmc.log.LogTools;

public class MAVController implements IMAVController, Runnable {

	public static final int MODE_NORMAL = 0;
	public static final int MODE_SITL = 1;
	public static final int MODE_USB = 2;
	public static final int MODE_SITL_PROXY = 3;
	public static final int MODE_ORIN       = 4;

	protected String peerAddress = null;
	protected int peerPort = 0;
	protected int bindPort = 0;

	protected static IMAVController controller;
	protected IMAVComm comm = null;

	protected boolean isSITL = false;
	protected final DataModel model;
	protected final MAVLinkBlockingReader reader;

	protected int commError = 0;

	private boolean file_log_enabled = false;

	protected StatusManager status_manager = null;

	private String filename;
	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private PrintStream ps_log;

	protected int mode = MODE_NORMAL;

	protected final WorkQueue wq = WorkQueue.getInstance();

	public static IMAVController getInstance() {
		return controller;
	}

	public MAVController() {
		this(0);
	}

	public MAVController(int i) {
		
		controller = this;
		model = new DataModel();
		reader = new MAVLinkBlockingReader(i, model);
		status_manager = new StatusManager(model, true);
		
		wq.addCyclicTask("LP", 500, this);
	}

	public String enableFileLogging(boolean enable, String directory_name) {
		this.file_log_enabled = enable;
		if (enable) {
			if (directory_name == null)
				directory_name = System.getProperty("user.home") + "/MSPLog";
			File file = new File(directory_name);
			if (!file.exists() || !file.isDirectory()) {
				boolean wasDirectoryMade = file.mkdirs();
				if (wasDirectoryMade)
					LogTools.info("Directory " + directory_name + " created");
				else {
					file_log_enabled = false;
					LogTools.info("No logging to file: Could not create directory " + directory_name);
					return null;
				}
			}
			// create file, if it does not exist
			SimpleDateFormat sdfFile = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
			this.filename = directory_name + "/msplog_" + sdfFile.format(new Date()) + ".log";
			LogTools.info("Logging to: " + filename);

			try {
				FileOutputStream fos_log = new FileOutputStream(filename);
				ps_log = new PrintStream(fos_log);
			} catch (FileNotFoundException e) {
				LogTools.error("No logging to file: Error creating log file.");
				file_log_enabled = false;
				return null;
			}

			addMAVMessageListener((msg) -> {
				writeLogToFile(msg.toString());
			});

			return this.filename;
		}
		return null;
	}

	@Override
	public int getMode() {
		return mode;
	}

	@Override
	public boolean sendMAVLinkMessage(MAVLinkMessage msg) {

		if(comm==null)
			return false;
		
		try {
			comm.write(msg);
			return true;
		} catch (Exception e1) {
			commError++;
			LogTools.error("MAVLinkMessage " + msg + " not sent. " + e1.getMessage());
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
		cmd.target_component = 0;
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

	public boolean sendMSPLinkCmd(int command, float... params) {

		if (!controller.getCurrentModel().sys.isStatus(Status.MSP_CONNECTED)) {
			LogTools.error("Command rejected. No connection.");
			return false;
		}

		msg_msp_command cmd = new msg_msp_command(255, 1);
		cmd.command = command;

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

			}
		}

		if (comm != null) {

			try {
				if (comm.isSerial())
					throw new IOException("MSP Commands only via UDP to proxy allowed");

				comm.write(cmd);
				return true;
			} catch (IOException e1) {
				commError++;
				LogTools.error("Command rejected: " + e1.getMessage());
				return false;
			}

		}
		return false;
	}

	@Override
	public boolean sendShellCommand(String s) {
		String command = s + "\n";
		msg_serial_control msg = new msg_serial_control(255, 1);
		try {
			msg.target_system    = 1;
			msg.target_component = 1;
			byte[] bytes = command.getBytes("US-ASCII");
			for (int i = 0; i < bytes.length && i < 70; i++)
				msg.data[i] = bytes[i];
			msg.count = bytes.length;
			msg.device = SERIAL_CONTROL_DEV.SERIAL_CONTROL_DEV_SHELL;
			msg.flags = SERIAL_CONTROL_FLAG.SERIAL_CONTROL_FLAG_RESPOND | SERIAL_CONTROL_FLAG.SERIAL_CONTROL_FLAG_EXCLUSIVE;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		sendMAVLinkMessage(msg);
		System.out.println("ShellCommand executed: " + s);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean connect() {
		return false;
	}

	@Override
	public DataModel getCurrentModel() {
		return reader.getModel();
	}

	@Override
	public boolean isSimulation() {
		return model.sys.isStatus(Status.MSP_SITL);
	}

	@Override
	public boolean isConnected() {
		return true;
	}

	@Override
	public boolean close() {
		comm.close();
		if (file_log_enabled && ps_log!=null)
			ps_log.close();
		return true;
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
		reader.getParser().addMAVMessageListener(listener);

	}

	@Override
	public void shutdown() {
		if (comm != null)
			comm.shutdown();
		if (file_log_enabled)
			ps_log.close();
	}

	@Override
	public void writeLogMessage(LogMessage m) {

		if (comm != null)
			comm.writeMessage(m);
		model.msg.set(m);
	}

	@Override
	public int getErrorCount() {
		return comm.getErrorCount();
	}

	@Override
	public String getConnectedAddress() {
		return peerAddress;
	}

	@Override
	public void run() {
		if (file_log_enabled && ps_log!=null) {
			ps_log.flush();
		}
	}

	private void writeLogToFile(String msg) {
		ps_log.println(sdf1.format(new Date()) + ": " + msg);
	}

	@Override
	public StatusManager getStatusManager() {
		return status_manager;
	}

	@Override
	public long getTransferRate() {
		if (comm != null)
			return comm.getTransferRate();
		return 0;
	}


}
