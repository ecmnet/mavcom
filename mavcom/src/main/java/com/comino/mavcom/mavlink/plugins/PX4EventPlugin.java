package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_event;

public class PX4EventPlugin extends MAVLinkPluginBase {

	public PX4EventPlugin() {
		super(msg_event.class);
	}

	@Override
	public void received(Object o) {
	
	}

}
