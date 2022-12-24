//package com.comino.mavcom.mavlink.plugins;
//
//import org.mavlink.messages.lquac.msg_msp_micro_slam;
//
//import com.comino.mavcom.model.DataModel;
//import com.comino.mavcom.model.segment.Status;
//
//public class MspObstaclePlugin extends MAVLinkPluginBase {
//
//	public MspObstaclePlugin() {
//		super(msg_msp_micro_slam.class);
//	}
//
//	@Override
//	public void received(Object o) {
//
//		msg_msp_micro_slam slam = (msg_msp_micro_slam) o;
//		model.slam.ix = slam.ix;
//		model.slam.iy = slam.iy;
//		model.slam.iz = slam.iz;
//		model.slam.di = slam.md;
//		model.slam.dw = slam.mw;
//		model.slam.dm = slam.dm;
//		model.slam.ox = slam.ox;
//		model.slam.oy = slam.oy;
//		model.slam.oz = slam.oz;	
//		model.slam.quality = slam.quality;
//		model.slam.wpcount = (int) slam.wpcount;
//		model.slam.flags = (short) slam.flags;
//		model.slam.fps = slam.fps;
//		model.slam.tms = DataModel.getSynchronizedPX4Time_us();
//		if (slam.quality > 0)
//			model.sys.setSensor(Status.MSP_SLAM_AVAILABILITY, true);
//
//	}
//}
