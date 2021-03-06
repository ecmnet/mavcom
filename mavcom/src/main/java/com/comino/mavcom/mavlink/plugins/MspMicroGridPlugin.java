package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_msp_micro_grid;

import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.Status;

public class MspMicroGridPlugin extends MAVLinkPluginBase {

	public MspMicroGridPlugin() {
		super(msg_msp_micro_grid.class);
	}

	@Override
	public void received(Object o) {

		msg_msp_micro_grid grid = (msg_msp_micro_grid) o;
		model.grid.fromArray(grid.data);
		model.grid.count = (int) grid.count;
		model.grid.status = (byte)grid.status;
		model.grid.ix = grid.cx;
		model.grid.iy = grid.cy;
		model.grid.iz = grid.cz;
		model.grid.tms = DataModel.getSynchronizedPX4Time_us();

	}


}
