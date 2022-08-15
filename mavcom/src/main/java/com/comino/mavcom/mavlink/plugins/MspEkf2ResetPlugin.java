package com.comino.mavcom.mavlink.plugins;



import org.mavlink.messages.lquac.msg_msp_ekf2_reset;


public class MspEkf2ResetPlugin extends MAVLinkPluginBase {

	public MspEkf2ResetPlugin() {
		super(msg_msp_ekf2_reset.class);
	}

	@Override
	public void received(Object o) {

		msg_msp_ekf2_reset reset = (msg_msp_ekf2_reset) o;
		
		model.est.l_x_reset = reset.offset_x;
		model.est.l_y_reset = reset.offset_y;
		model.est.l_z_reset = reset.offset_z;
	
	}

}
