package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_mission_current;


public class PX4MissionCurrentPlugin extends MAVLinkPluginBase {

	public PX4MissionCurrentPlugin() {
		super(msg_mission_current.class);
	}

	@Override
	public void received(Object o) {
		
		msg_mission_current mission = (msg_mission_current) o;
		model.slam.wpcount = mission.seq;

	}
}
