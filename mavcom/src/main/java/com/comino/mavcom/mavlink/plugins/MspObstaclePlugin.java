package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_msp_obstacle;

import com.comino.mavcom.model.DataModel;

public class MspObstaclePlugin extends MAVLinkPluginBase {

	public MspObstaclePlugin() {
		super(msg_msp_obstacle.class);
	}

	@Override
	public void received(Object o) {

		msg_msp_obstacle obstacle = (msg_msp_obstacle) o;
		model.slam.dm = obstacle.dm;
		model.obs.x   = obstacle.ox;
		model.obs.y   = obstacle.oy;
		model.obs.z   = obstacle.oz;	
		model.obs.sx  = obstacle.dx;
		model.obs.sy  = obstacle.dy;
		model.obs.sz  = obstacle.dz;	
		model.slam.tms = DataModel.getSynchronizedPX4Time_us();

	}
}
