package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_global_position_int;

import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.Status;

public class PX4GlobalPositionPlugin extends MAVLinkPluginBase {
	
	private float hdg;

	public PX4GlobalPositionPlugin() {
		super(msg_global_position_int.class);
	}

	@Override
	public void received(Object o) {

		msg_global_position_int pos = (msg_global_position_int) o;
		model.state.g_lat = pos.lat / 1.0e7;
		model.state.g_lon = pos.lon / 1.0e7;
		model.state.g_alt = (pos.alt / 1000f);
		model.state.g_vx = pos.vx / 100f;
		model.state.g_vy = pos.vy / 100f;
		model.state.g_vz = pos.vz / 100f;


		if (pos.lat != 0 && pos.lon != 0) {
//			hdg = pos.hdg / 100f;
//			if (hdg < 360 && hdg >= 0)
//				model.hud.h = hdg;
			model.state.gpos_tms = DataModel.getSynchronizedPX4Time_us();
			model.sys.setStatus(Status.MSP_GPOS_VALID, true);
		}

	}
}
