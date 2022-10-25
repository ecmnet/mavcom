package com.comino.mavcom.messaging.msgs;

import georegression.struct.point.Point3D_F64;

public class msp_msg_landing_pad extends msp_msg_base {
	
	public Point3D_F64   position    = new Point3D_F64();
	public float         orientation = Float.NaN;

}
