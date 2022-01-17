package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_ping;

import com.comino.mavcom.model.segment.Status;

public class PX4PingPlugin extends MAVLinkPluginBase {

	public PX4PingPlugin() {
		super(msg_ping.class);
	}

	@Override
	public void received(Object o) {

		model.sys.setSensor(Status.MSP_SYSM_AVAILABILITY, true);

	}
}
