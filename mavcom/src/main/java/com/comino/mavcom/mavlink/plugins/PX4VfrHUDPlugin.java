package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_vfr_hud;

import com.comino.mavcom.model.segment.Status;
import com.comino.mavutils.MSPMathUtils;

public class PX4VfrHUDPlugin extends MAVLinkPluginBase {

	public PX4VfrHUDPlugin() {
		super(msg_vfr_hud.class);
	}

	@Override
	public void received(Object o) {

		msg_vfr_hud hud = (msg_vfr_hud) o;
		model.hud.s = hud.groundspeed;
		model.hud.vs = hud.climb;
		model.hud.as = hud.airspeed;

//		if (!model.sys.isStatus(Status.MSP_GPOS_VALID) && hud.heading < 360 && hud.heading >= 0)
//		model.hud.h = MSPMathUtils.toRad(hud.heading);

		if (hud.throttle <= 100)
			model.attitude.st = hud.throttle / 100.0f;

	}
}
