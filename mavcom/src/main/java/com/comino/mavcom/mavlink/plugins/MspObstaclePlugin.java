package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_msp_micro_slam;
import org.mavlink.messages.lquac.msg_msp_obstacle;

import com.comino.mavcom.model.DataModel;

public class MspObstaclePlugin extends MAVLinkPluginBase {

	public MspObstaclePlugin() {
		super(msg_msp_micro_slam.class);
	}

	@Override
	public void received(Object o) {

		msg_msp_obstacle slam = (msg_msp_obstacle) o;
		model.slam.dm = slam.dm;
		model.slam.ox = slam.ox;
		model.slam.oy = slam.oy;
		model.slam.oz = slam.oz;	
		model.slam.tms = DataModel.getSynchronizedPX4Time_us();

	}
}
