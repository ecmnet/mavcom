package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_odometry;

import com.comino.mavcom.mavlink.MAV_COV;

public class PX4OdometryPlugin extends MAVLinkPluginBase {

	public PX4OdometryPlugin() {
		super(msg_odometry.class);
	}

	@Override
	public void received(Object o) {

		msg_odometry odom = (msg_odometry) o;

		model.vision.cov_px = odom.pose_covariance[MAV_COV.VIS_COV_X];
		model.vision.cov_py = odom.pose_covariance[MAV_COV.VIS_COV_Y];
		model.vision.cov_pz = odom.pose_covariance[MAV_COV.VIS_COV_Z];
		
		model.vision.ox = odom.x;
		model.vision.oy = odom.y;
		model.vision.oz = odom.z;

//		model.vision.cov_vx = odom.velocity_covariance[MAV_COV.VIS_COV_VX];
//		model.vision.cov_vy = odom.velocity_covariance[MAV_COV.VIS_COV_VY];
//		model.vision.cov_vz = odom.velocity_covariance[MAV_COV.VIS_COV_VZ];



	}
}
