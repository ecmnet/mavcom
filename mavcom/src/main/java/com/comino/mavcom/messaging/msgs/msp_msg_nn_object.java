package com.comino.mavcom.messaging.msgs;

import georegression.struct.point.Point3D_F64;

public class msp_msg_nn_object extends msp_msg_base {
	
	public byte          object_id     = -1;
	public Point3D_F64   position      = new Point3D_F64();

}
