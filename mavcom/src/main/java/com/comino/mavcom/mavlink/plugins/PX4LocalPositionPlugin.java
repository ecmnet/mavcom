package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_local_position_ned;

import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.State;
import com.comino.mavcom.model.segment.Status;

public class PX4LocalPositionPlugin extends MAVLinkPluginBase {

	private static State last = new State();

	public PX4LocalPositionPlugin() {
		super(msg_local_position_ned.class);
	}

	@Override
	public void received(Object o) {

		msg_local_position_ned ned = (msg_local_position_ned) o;

		model.state.l_x = ned.x;
		model.state.l_y = ned.y;
		model.state.l_z = ned.z;

		model.state.l_vx = ned.vx;
		model.state.l_vy = ned.vy;
		model.state.l_vz = ned.vz;
		model.state.tms = DataModel.getSynchronizedPX4Time_us();

		model.state.v = (float) Math.sqrt(ned.vx * ned.vx + ned.vy * ned.vy);

		if (last.tms > 0) {
			
			model.state.l_ax = (model.state.l_vx - last.l_vx) * 1_000_000f / (model.state.tms - last.tms);
			model.state.l_ay = (model.state.l_vy - last.l_vy) * 1_000_000f / (model.state.tms - last.tms);
			model.state.l_az = (model.state.l_vz - last.l_vz) * 1_000_000f / (model.state.tms - last.tms);
		}

		last.set(model.state);
		last.tms = model.state.tms;

		if ((ned.x != 0 || ned.y != 0) && Float.isFinite(ned.x) && Float.isFinite(ned.y)) {
			model.sys.setStatus(Status.MSP_LPOS_VALID, true);
		}

	}
}
