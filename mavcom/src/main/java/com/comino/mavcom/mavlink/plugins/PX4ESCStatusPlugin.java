package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_esc_status;

public class PX4ESCStatusPlugin extends MAVLinkPluginBase {

	public PX4ESCStatusPlugin() {
		super(msg_esc_status.class);
	}

	@Override
	public void received(Object o) {

		msg_esc_status esc = (msg_esc_status) o;
		model.esc.tms = esc.time_usec;
		for(int i=0;i<4;i++) {
			model.esc.rpm[i] = (short)esc.rpm[i];
			model.esc.voltage[i] = (short)esc.voltage[i];
			model.esc.current[i] = (short)esc.current[i];
		}
	}
}
