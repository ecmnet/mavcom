package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_gps_raw_int;

import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.GPS;
import com.comino.mavcom.model.segment.Status;
import com.comino.mavutils.MSPMathUtils;

public class PX4RawGPSPlugin extends MAVLinkPluginBase {

	private final static float[] p     = new float[2];
	private final static float[] p_old = new float[2];
	private static long  tms_old = 0;

	public PX4RawGPSPlugin() {
		super(msg_gps_raw_int.class);
	}

	@Override
	public void received(Object o) {

		msg_gps_raw_int gps = (msg_gps_raw_int) o;

		if (gps.satellites_visible < 99) { // spike fix

			model.gps.numsat = (byte) gps.satellites_visible;

			model.gps.setFlag(GPS.GPS_SAT_FIX, (gps.fix_type & 0xF) > 0);
			model.gps.setFlag(GPS.GPS_SAT_RTK, (gps.fix_type & 0xF) > 4);

			model.gps.setFlag(GPS.GPS_SAT_RTKFIX, (gps.fix_type & 0xF) > 5);
			model.gps.setFlag(GPS.GPS_SAT_VALID, true);
			model.gps.heading = gps.yaw / 1000f;

			model.gps.eph = gps.h_acc < 90000 && gps.h_acc > 0 ? gps.h_acc / 1000f : Float.NaN;
			model.gps.epv = gps.v_acc < 90000 && gps.v_acc > 0 ? gps.v_acc / 1000f : Float.NaN;
			model.gps.hdop = gps.eph / 100f;

			model.gps.latitude  = gps.lat / 1e7;
			model.gps.longitude = gps.lon / 1e7;

			model.gps.altitude = gps.alt / 1000f;
			model.gps.fixtype = (byte) gps.fix_type;
			model.gps.tms = DataModel.getSynchronizedPX4Time_us();

			if (MSPMathUtils.is_projection_initialized() && gps.fix_type > 2) {
				if(MSPMathUtils.map_projection_project(model.gps.latitude, model.gps.longitude, p)) {
				  model.gps.lx = p[0];
				  model.gps.ly = p[1];
				  if (tms_old > 0) {
					  model.gps.lx_s = (p[0] - p_old[0]) * 1e6f / (gps.time_usec - tms_old);
					  model.gps.ly_s = (p[1] - p_old[1]) * 1e6f / (gps.time_usec - tms_old);  
				  }
				  p_old[0] = p[0];
				  p_old[1] = p[1];
				  tms_old = gps.time_usec;
				}
			}

			model.sys.setSensor(Status.MSP_GPS_AVAILABILITY, true);
			model.sys.setSensor(Status.MSP_RTK_AVAILABILITY, (gps.fix_type & 0xF) > 4);
		}

	}
}
