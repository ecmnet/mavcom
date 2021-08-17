package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_optical_flow_rad;

import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.Status;

public class PX4OptFlowRadPlugin extends MAVLinkPluginBase {

	public PX4OptFlowRadPlugin() {
		super(msg_optical_flow_rad.class);
	}

	@Override
	public void received(Object o) {

		msg_optical_flow_rad flow = (msg_optical_flow_rad) o;
		model.flow.fX = flow.integrated_x;
		model.flow.fY = flow.integrated_y;
		model.flow.fq = flow.quality;
		
		if(flow.distance > 0)
		  model.flow.fd = flow.distance;
		
		model.flow.fgX = flow.integrated_xgyro;
		model.flow.fgY = flow.integrated_ygyro;
		model.flow.fgZ = flow.integrated_zgyro;
		model.flow.ius = flow.integration_time_us;

	//	if(flow.quality > 0) {
		  model.sys.setSensor(Status.MSP_PIX4FLOW_AVAILABILITY, true);
		  model.flow.tms = DataModel.getSynchronizedPX4Time_us();
	//	}
	}
}
