package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_msp_local_position_corrected;

public class MspLocalPositionCorrectedPlugin extends MAVLinkPluginBase {

	public MspLocalPositionCorrectedPlugin() {
		super(msg_msp_local_position_corrected.class);
	}

	@Override
	public void received(Object o) {

		msg_msp_local_position_corrected lpos = (msg_msp_local_position_corrected) o;
		
		model.state.l_rx    = lpos.cx;
		model.state.l_ry    = lpos.cy;
		model.state.l_rz    = lpos.cz;
		
		model.vision.gx     = lpos.gx;
		model.vision.gy     = lpos.gy;
		model.vision.gz     = lpos.gz;
	
	}

}
