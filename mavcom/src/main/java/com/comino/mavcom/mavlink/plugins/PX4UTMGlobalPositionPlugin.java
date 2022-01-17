package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_odometry;

public class PX4UTMGlobalPositionPlugin extends MAVLinkPluginBase {

	public PX4UTMGlobalPositionPlugin() {
		super(msg_odometry.class);
	}

	@Override
	public void received(Object o) {

	}
}
