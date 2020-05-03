package com.comino.mavcom.mavlink;

public class MAV_MASK {

	public static final int     MASK_POSITION_IGNORE      = 0b0000000000000111;
	public static final int     MASK_VELOCITY_IGNORE      = 0b0000000000111000;
	public static final int     MASK_ACCELERATION_IGNORE  = 0b0000000111000000;
	public static final int     MASK_FORCE_IGNORE         = 0b0000001000000000;
	public static final int     MASK_YAW_IGNORE           = 0b0000010000000000;
	public static final int     MASK_YAW_RATE_IGNORE      = 0b0000100000000000;

	public static final int     MASK_POS_SETPOINT_TYPE    = 0b0000000000000000;
	public static final int     MASK_TAKEOFF_SETPOINT_TYPE= 0b0001000000000000;
	public static final int     MASK_LAND_SETPOINT_TYPE   = 0b0010000000000000;
	public static final int     MASK_LOITER_SETPOINT_TYPE = 0b0100000000000000;
	public static final int     MASK_IDLE_SETPOINT_TYPE   = 0b1000000000000000;


}
