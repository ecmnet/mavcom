package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_msp_micro_slam;

import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.Status;

public class MspMicroSlamPlugin extends MAVLinkPluginBase {

	public MspMicroSlamPlugin() {
		super(msg_msp_micro_slam.class);
	}

	@Override
	public void received(Object o) {

		msg_msp_micro_slam slam = (msg_msp_micro_slam) o;
		model.slam.pd = slam.pd;
		model.slam.pp = slam.pp;
		model.slam.pv = slam.pv;
		model.slam.px = slam.px;
		model.slam.py = slam.py;
		model.slam.pz = slam.pz;
		model.slam.di = slam.md;
		model.slam.dw = slam.mw;
		model.slam.dm = slam.dm;
		model.slam.ox = slam.ox;
		model.slam.oy = slam.oy;
		model.slam.oz = slam.oz;
		model.slam.quality = slam.quality;
		model.slam.wpcount = (int)slam.wpcount;
		model.slam.flags = (short)slam.flags;
		model.slam.fps  = slam.fps;
		model.slam.tms = DataModel.getSynchronizedPX4Time_us();
		model.sys.setSensor(Status.MSP_SLAM_AVAILABILITY, true);

	}
}
