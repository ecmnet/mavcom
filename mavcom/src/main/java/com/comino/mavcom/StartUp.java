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

package com.comino.mavcom;

import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;

import org.mavlink.messages.MAV_SEVERITY;
import org.mavlink.messages.MSP_CMD;
import org.mavlink.messages.lquac.msg_msp_command;
import org.mavlink.messages.lquac.msg_msp_micro_grid;
import org.mavlink.messages.lquac.msg_msp_status;

import com.comino.mavcom.config.MSPConfig;
import com.comino.mavcom.control.IMAVMSPController;
import com.comino.mavcom.control.impl.MAVController;
import com.comino.mavcom.control.impl.MAVProxyController;
import com.comino.mavcom.log.MSPLogger;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.LogMessage;
import com.comino.mavcom.model.segment.Status;
import com.comino.mavcom.param.PX4Parameters;

import us.ihmc.log.LogTools;

public class StartUp implements Runnable {

	private IMAVMSPController control = null;
	private MSPConfig config = null;

	private OperatingSystemMXBean osBean = null;;
	private MemoryMXBean mxBean = null;
	private DataModel model = null;

	private boolean is_simulation = false;

	private PX4Parameters params = null;

	public StartUp(String[] args) {

		if (args.length != 0) {
			is_simulation = true;
		}

		if (is_simulation) {
			config = MSPConfig.getInstance(System.getProperty("user.home") + "/", "msp.properties");
			control = new MAVProxyController(MAVController.MODE_SITL,config);
		} else {
			config = MSPConfig.getInstance("/home/up", "msp.properties");
			control = new MAVProxyController(MAVController.MODE_NORMAL,config);
		}

		model = control.getCurrentModel();

		MSPLogger.getInstance(control);

		osBean = java.lang.management.ManagementFactory.getOperatingSystemMXBean();
		mxBean = java.lang.management.ManagementFactory.getMemoryMXBean();

		// Start services if required

		control.start();

		MSPLogger.getInstance().writeLocalMsg("MAVProxy " + config.getVersion() + " loaded");
		Thread worker = new Thread(this);
		worker.start();

		control.registerListener(msg_msp_command.class, new IMAVLinkListener() {
			@Override
			public void received(Object o) {
				msg_msp_command cmd = (msg_msp_command) o;
				switch (cmd.command) {
				case MSP_CMD.MSP_TRANSFER_MICROSLAM:
					control.writeLogMessage(
							new LogMessage("[sitl] map transfer request", MAV_SEVERITY.MAV_SEVERITY_NOTICE));
					break;
				}
			}
		});

		params = PX4Parameters.getInstance(control);

		control.getStatusManager().addListener(Status.MSP_CONNECTED, (n) -> {
			LogTools.info("Connected");
			if (n.isStatus(Status.MSP_CONNECTED))
				params.requestRefresh(false);
		});

	}

	public static void main(String[] args) {
		new StartUp(args);
	}

	boolean isAvoiding = true;

	@Override
	public void run() {
		long tms = System.currentTimeMillis();

		msg_msp_status msg = new msg_msp_status(2, 1);

		while (true) {
			try {

				if (!control.isConnected()) {
					Thread.sleep(200);
					control.connect();
					continue;
				}

				Thread.sleep(50);

				if ((System.currentTimeMillis() - tms) < 500)
					continue;

				tms = System.currentTimeMillis();

				msg.load = 0;
				msg.autopilot_mode = control.getCurrentModel().sys.autopilot;
				msg.memory = (int) (mxBean.getHeapMemoryUsage().getUsed() * 100 / mxBean.getHeapMemoryUsage().getMax());
				msg.com_error = control.getErrorCount();
				msg.uptime_ms = System.currentTimeMillis() - tms;
				msg.status = control.getCurrentModel().sys.getStatus();
				msg.setVersion(config.getVersion());
				msg.setArch(osBean.getArch());
				msg.cpu_temp = 27;
				msg.unix_time_us = DataModel.getSynchronizedPX4Time_us();
				msg.wifi_quality = 100;
				control.sendMAVLinkMessage(msg);

			} catch (Exception e) {
				e.printStackTrace();
				control.close();
			}
		}

	}

}
