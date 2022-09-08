package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_sys_status;

import com.comino.mavcom.model.segment.Status;

public class PX4SystemStatusPlugin extends MAVLinkPluginBase {

	public PX4SystemStatusPlugin() {
		super(msg_sys_status.class);
	}

	@Override
	public void received(Object o) {

		msg_sys_status sys = (msg_sys_status) o;
		model.sys.error1 = sys.errors_count1;
		if (sys.load > 0)
			model.sys.load_p = sys.load / 10;
		
		model.sys.drops_p = sys.drop_rate_comm / 10000f;
		model.sys.health  = sys.onboard_control_sensors_health & 0x7FFFFFFF;
	
	}
}
