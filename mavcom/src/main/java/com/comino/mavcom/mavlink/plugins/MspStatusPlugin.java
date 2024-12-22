package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_msp_status;

import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.Status;

public class MspStatusPlugin extends MAVLinkPluginBase {

	public MspStatusPlugin() {
		super(msg_msp_status.class);
	}

	@Override
	public void received(Object o) {

		msg_msp_status status = (msg_msp_status) o;
		model.sys.status = (int) status.status;
		model.sys.load_m = status.load;
		model.sys.autopilot = (long) status.autopilot_mode;
		model.sys.wifi_quality = status.wifi_quality / 100f;
		model.sys.msp_temp = (byte) status.cpu_temp;
		model.sys.bat_type = (byte) status.bat_type;
		model.sys.mem_m = (short) status.memory;
		model.sys.t_takeoff_ms = status.takeoff_ms;
		model.sys.msp_tms = System.currentTimeMillis() * 1000;
		
		model.sys.sensors = (int)status.sensors;
	
		Status.build = status.getVersion();
		
		
	}
}
