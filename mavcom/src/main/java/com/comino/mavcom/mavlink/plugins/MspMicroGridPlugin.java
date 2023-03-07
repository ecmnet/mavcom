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
//		model.grid.fromArray(grid.data);
//		model.grid.count = (int) grid.count;
		model.grid.status = (byte) grid.status;
		model.grid.ox = grid.cx;
		model.grid.oy = grid.cy;
		model.grid.oz = grid.cz;
		model.grid.resolution = grid.resolution;
		model.grid.tms = DataModel.getSynchronizedPX4Time_us();
		if(grid.count>0)
		  model.sys.setSensor(Status.MSP_GRID_AVAILABILITY, true);

	}

}
