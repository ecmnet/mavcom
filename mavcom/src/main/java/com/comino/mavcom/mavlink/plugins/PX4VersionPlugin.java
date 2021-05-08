package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_autopilot_version;

public class PX4VersionPlugin extends MAVLinkPluginBase {
	
	String build = "";

	public PX4VersionPlugin() {
		super(msg_autopilot_version.class);
	}

	@Override
	public void received(Object o) {

		msg_autopilot_version version = (msg_autopilot_version) o;
		model.sys.version = String.format("%d.%d.%d", (version.flight_sw_version >> (8 * 3)) & 0xFF,
				(version.flight_sw_version >> (8 * 2)) & 0xFF, (version.flight_sw_version >> (8 * 1)) & 0xFF);
		//	System.out.println("Version: " + model.sys.version);
		
		for(int i=0;i<5;i++)
			build = build +	Integer.toHexString(version.flight_custom_version[7-i]);
		model.sys.fw_build = build;
	}
}
