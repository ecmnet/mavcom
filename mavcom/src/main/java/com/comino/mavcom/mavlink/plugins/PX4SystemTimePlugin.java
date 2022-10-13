package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_system_time;

public class PX4SystemTimePlugin extends MAVLinkPluginBase {
	
	private static boolean is_time_set = false;

	public PX4SystemTimePlugin() {
		super(msg_system_time.class);
	}

	@Override
	public void received(Object o) {
		
		msg_system_time time = (msg_system_time)o;
		model.sys.t_unix_us = time.time_unix_usec;

		
	}
}
