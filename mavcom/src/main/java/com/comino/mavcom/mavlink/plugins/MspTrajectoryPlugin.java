package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_msp_trajectory;

public class MspTrajectoryPlugin extends MAVLinkPluginBase {

	public MspTrajectoryPlugin() {
		super(msg_msp_trajectory.class);
	}

	@Override
	public void received(Object o) {

		msg_msp_trajectory traj = (msg_msp_trajectory) o;
		model.traj.ls = traj.ls;
		model.traj.fs = traj.fs;
		
		model.traj.ax = traj.ax;
		model.traj.ay = traj.ay;
		
		model.traj.bx = traj.bx;
		model.traj.by = traj.by;
		
		model.traj.gx = traj.gx;
		model.traj.gy = traj.gy;
		
		model.traj.sx = traj.sx;
		model.traj.sy = traj.sy;
		
		model.traj.svx = traj.svx;
		model.traj.svy = traj.svy;
	

		model.traj.tms = traj.tms;
		
	}
}
