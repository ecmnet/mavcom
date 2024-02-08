package com.comino.mavcom.config;

public final class MSPParams {
	
	// Connection
	
	public static final String BAUDRATE = "baudrate";
	

	// autopilot

	public static final String AUTOPILOT_CLASS = "autopilot_class";
	public static final String AUTOPILOT_FORGET_MAP_MS = "autopilot_forget_map_ms";
	public static final String AUTOPILOT_TAKEOFF_PROC = "autopilot_takeoff_procedure";
	public static final String AUTOPILOT_TAKEOFF_OFFBOARD = "autopilot_takeoff_offboard";
	public static final String AUTOPILOT_PRECISION_LOCK = "autopilot_precision_lock";
	public static final String AUTOPILOT_MAX_XYZ_VEL = "autopilot_max_xyz_vel";
	public static final String AUTOPILOT_RADIUS_ACCEPT = "autopilot_acceptance_radius";
	public static final String AUTOPILOT_HEALTH_CHECK = "autopilot_healt_check";
	
	// control publishing

	public static final String PUBLISH_DEBUG = "publish_debug";
	public static final String PUBLISH_ODOMETRY = "publish_odometry";
	public static final String PUBLISH_MICROSLAM = "publish_microslam";
	public static final String PUBLISH_MICROGRID = "publish_microgrid";

	// Vision general

	public static final String VISION_ENABLED = "vision_enabled";

	// T265 settings

	public static final String T265_PRECISION_LOCK = "t265_precision_lock";
	public static final String T265_FIDUCIAL_SIZE = "t265_fiducial_size";
	public static final String T265_FIDUCIAL_OFFSET_X = "t265_fiducial_offset_x";
	public static final String T265_FIDUCIAL_OFFSET_Y = "t265_fiducial_offset_y";
	public static final String T265_FIDUCIAL_OFFSET_Z = "t265_fiducial_offset_z";
	public static final String T265_OFFSET_X = "t265_offset_x";
	public static final String T265_OFFSET_Y = "t265_offset_y";
	public static final String T265_OFFSET_Z = "t265_offset_z";
	
	// T265 checks
	
	public static final String T265_CHECK_VELTESTRATIO = "t265_check_vel_test_ratio";
	public static final String T265_CHECK_MAX_ERROR    = "t265_check_max_errors";

	// D455 settings

	public static final String D455_OFFSET_X = "d455_offset_x";
	public static final String D455_OFFSET_Y = "d455_offset_y";
	public static final String D455_OFFSET_Z = "d455_offset_z";
	public static final String D455_WARN_OBS = "d455_warn_obs";
	public static final String D455_DEPTH_OVERLAY = "d455_depth_overlay";


	// OAK_D settings

	public static final String OAKD_OFFSET_X = "oakd_offset_x";
	public static final String OAKD_OFFSET_Y = "oakd_offset_y";
	public static final String OAKD_OFFSET_Z = "oakd_offset_z";
	
	public static final String OAKD_YOLO_ENABLED = "oakd_yolo_enabled";

}
