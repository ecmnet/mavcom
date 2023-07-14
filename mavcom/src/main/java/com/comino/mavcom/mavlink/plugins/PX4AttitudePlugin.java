package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_attitude;

import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.Status;
import com.comino.mavutils.MSPMathUtils;

public class PX4AttitudePlugin extends MAVLinkPluginBase {

	
	public PX4AttitudePlugin() {
		super(msg_attitude.class);
	}

	@Override
	public void received(Object o) {

		msg_attitude att = (msg_attitude) o;

		model.attitude.r = att.roll;
		model.attitude.p = att.pitch;
		model.attitude.y = att.yaw;
		model.state.h    = att.yaw;

		model.attitude.rr = att.rollspeed;
		model.attitude.pr = att.pitchspeed;
		model.attitude.yr = att.yawspeed;
		
		model.getBodyToNedBuffer().add(att.time_boot_ms*1000L);

		model.attitude.tms = DataModel.getSynchronizedPX4Time_us();

		model.hud.aX = att.roll;
		model.hud.aY = att.pitch;

		model.sys.t_boot_ms = att.time_boot_ms;

		model.sys.setSensor(Status.MSP_IMU_AVAILABILITY, true);

	}
}
