package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_msp_vision;

import com.comino.mavcom.mavlink.MAV_COV;
import com.comino.mavcom.model.segment.Status;

public class MSPVisionPlugin extends MAVLinkPluginBase {

	public MSPVisionPlugin() {
		super(msg_msp_vision.class);
	}

	@Override
	public void received(Object o) {

		msg_msp_vision vision = (msg_msp_vision) o;

		model.vision.vx = vision.vx;
		model.vision.vy = vision.vy;
		model.vision.vz = vision.vz;

		model.vision.gx = vision.gx;
		model.vision.gy = vision.gy;
		model.vision.gz = vision.gz;

		model.vision.cov_px = vision.covariance[MAV_COV.VIS_COV_X];
		model.vision.cov_py = vision.covariance[MAV_COV.VIS_COV_Y];
		model.vision.cov_pz = vision.covariance[MAV_COV.VIS_COV_Z];

		model.vision.cov_vx = vision.covariance[MAV_COV.VIS_COV_VX];
		model.vision.cov_vy = vision.covariance[MAV_COV.VIS_COV_VY];
		model.vision.cov_vz = vision.covariance[MAV_COV.VIS_COV_VZ];

		model.vision.x = vision.x;
		model.vision.y = vision.y;
		model.vision.z = vision.z;

		model.vision.h= vision.h;
		model.vision.p= vision.p;
		model.vision.r= vision.r;

		model.vision.qual = vision.quality;
		model.vision.errors = (int) vision.errors;

		model.vision.flags = (int) vision.flags;
		model.vision.fps = vision.fps;
		if (model.vision.errors < 5 && ( vision.vx !=0 || vision.vy!=0)) {
			model.vision.tms = model.sys.getSynchronizedPX4Time_us();
			model.sys.setSensor(Status.MSP_OPCV_AVAILABILITY, true);
		}
	}
}
