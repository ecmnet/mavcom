package com.comino.mavcom.mavlink;

public class MAV_MASK {

	public static final int     MASK_POSITION_IGNORE        = 0b000000000000111;
	public static final int     MASK_POSITION_IGNORE_ZLOCK  = 0b000000000000011;
	public static final int     MASK_POSITION_IGNORE_XYLOCK = 0b000000000000100;
	public static final int     MASK_VELOCITY_IGNORE        = 0b000000000111000;
	public static final int     MASK_ACCELERATION_IGNORE    = 0b000000111000000;
	public static final int     MASK_FORCE_IGNORE           = 0b000001000000000;
	public static final int     MASK_YAW_IGNORE             = 0b000010000000000;
	public static final int     MASK_YAW_RATE_IGNORE        = 0b000100000000000;

//	public static final int     MASK_POS_SETPOINT_TYPE    = 0x0000;
//	public static final int     MASK_TAKEOFF_SETPOINT_TYPE= 0x1000;
//	public static final int     MASK_LAND_SETPOINT_TYPE   = 0x2000;
	public static final int     MASK_LOITER_SETPOINT_TYPE = 0x3000;
	public static final int     MASK_IDLE_SETPOINT_TYPE   = 0x4000;


}
