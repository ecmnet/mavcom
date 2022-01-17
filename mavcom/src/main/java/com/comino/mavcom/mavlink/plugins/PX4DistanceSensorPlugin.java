package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_distance_sensor;

import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.Status;

public class PX4DistanceSensorPlugin extends MAVLinkPluginBase {

	public PX4DistanceSensorPlugin() {
		super(msg_distance_sensor.class);
	}

	@Override
	public void received(Object o) {

		msg_distance_sensor lidar = (msg_distance_sensor) o;

		// with HereFlow: Workaround to map lightware distance sensor to model
		if (lidar.max_distance > 200) {
			model.sys.setSensor(Status.MSP_LIDAR_AVAILABILITY, true);
			if (lidar.current_distance > 1)
				model.distance.di = lidar.current_distance / 100f;
			else
				model.distance.di = Float.NaN;
			model.distance.dicov = lidar.covariance / 100f;
			model.distance.min = lidar.min_distance / 100f;
			model.distance.max = lidar.max_distance / 100f;
			model.distance.tms = DataModel.getSynchronizedPX4Time_us();
		} else {
			// Map hereflow distance to flow distance
			model.flow.fd = lidar.current_distance / 100f;
		}

//		switch(lidar.id) {
//		case 0:
//			model.raw.fd = lidar.current_distance / 100f;
//			break;
//		case 1:
//			model.sys.setSensor(Status.MSP_LIDAR_AVAILABILITY, true);
//			model.raw.di = lidar.current_distance / 100f;
//			model.raw.dicov = lidar.covariance / 100f;
//			model.raw.tms = DataModel.getSynchronizedPX4Time_us();
//			break;
//		}

	}
}
