package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_trajectory_representation_waypoints;

public class PX4TrajectoryRepresentationWaypointsPlugin extends MAVLinkPluginBase {

	public PX4TrajectoryRepresentationWaypointsPlugin() {
		super(msg_trajectory_representation_waypoints.class);
	}

	@Override
	public void received(Object o) {

		msg_trajectory_representation_waypoints t = (msg_trajectory_representation_waypoints) o;

		model.way.valid_points = 0;
		model.way.tms = t.time_usec / 1000L;

		for (int i = 0; i < t.valid_points; i++) {

			model.way.posx[i] = t.pos_x[i];
			model.way.posy[i] = t.pos_y[i];
			model.way.posz[i] = t.pos_z[i];

			model.way.velx[i] = t.vel_x[i];
			model.way.vely[i] = t.vel_y[i];
			model.way.velz[i] = t.vel_z[i];

			model.way.accx[i] = t.acc_x[i];
			model.way.accy[i] = t.acc_y[i];
			model.way.accz[i] = t.acc_z[i];

			model.way.pyaw[i] = t.pos_yaw[i];
			model.way.vyaw[i] = t.vel_yaw[i];

			model.way.cmd[i] = (short) t.command[i];

		}
	}
}
