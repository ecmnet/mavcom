/**
 * Generated class : MAV_FRAME
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_FRAME
 * 
 **/
public interface MAV_FRAME {
    /**
     * Global (WGS84) coordinate frame + MSL altitude. First value / x: latitude, second value / y: longitude, third value / z: positive altitude over mean sea level (MSL).
     */
    public final static int MAV_FRAME_GLOBAL = 0;
    /**
     * Local coordinate frame, Z-down (x: North, y: East, z: Down).
     */
    public final static int MAV_FRAME_LOCAL_NED = 1;
    /**
     * NOT a coordinate frame, indicates a mission command.
     */
    public final static int MAV_FRAME_MISSION = 2;
    /**
     * Global (WGS84) coordinate frame + altitude relative to the home position. First value / x: latitude, second value / y: longitude, third value / z: positive altitude with 0 being at the altitude of the home location.
     */
    public final static int MAV_FRAME_GLOBAL_RELATIVE_ALT = 3;
    /**
     * Local coordinate frame, Z-up (x: East, y: North, z: Up).
     */
    public final static int MAV_FRAME_LOCAL_ENU = 4;
    /**
     * Global (WGS84) coordinate frame (scaled) + MSL altitude. First value / x: latitude in degrees*1.0e-7, second value / y: longitude in degrees*1.0e-7, third value / z: positive altitude over mean sea level (MSL).
     */
    public final static int MAV_FRAME_GLOBAL_INT = 5;
    /**
     * Global (WGS84) coordinate frame (scaled) + altitude relative to the home position. First value / x: latitude in degrees*10e-7, second value / y: longitude in degrees*10e-7, third value / z: positive altitude with 0 being at the altitude of the home location.
     */
    public final static int MAV_FRAME_GLOBAL_RELATIVE_ALT_INT = 6;
    /**
     * Offset to the current local frame. Anything expressed in this frame should be added to the current local frame position.
     */
    public final static int MAV_FRAME_LOCAL_OFFSET_NED = 7;
    /**
     * Setpoint in body NED frame. This makes sense if all position control is externalized - e.g. useful to command 2 m/s^2 acceleration to the right.
     */
    public final static int MAV_FRAME_BODY_NED = 8;
    /**
     * Offset in body NED frame. This makes sense if adding setpoints to the current flight path, to avoid an obstacle - e.g. useful to command 2 m/s^2 acceleration to the east.
     */
    public final static int MAV_FRAME_BODY_OFFSET_NED = 9;
    /**
     * Global (WGS84) coordinate frame with AGL altitude (at the waypoint coordinate). First value / x: latitude in degrees, second value / y: longitude in degrees, third value / z: positive altitude in meters with 0 being at ground level in terrain model.
     */
    public final static int MAV_FRAME_GLOBAL_TERRAIN_ALT = 10;
    /**
     * Global (WGS84) coordinate frame (scaled) with AGL altitude (at the waypoint coordinate). First value / x: latitude in degrees*10e-7, second value / y: longitude in degrees*10e-7, third value / z: positive altitude in meters with 0 being at ground level in terrain model.
     */
    public final static int MAV_FRAME_GLOBAL_TERRAIN_ALT_INT = 11;
    /**
     * Body fixed frame of reference, Z-down (x: Forward, y: Right, z: Down).
     */
    public final static int MAV_FRAME_BODY_FRD = 12;
    /**
     * MAV_FRAME_BODY_FLU - Body fixed frame of reference, Z-up (x: Forward, y: Left, z: Up).
     */
    public final static int MAV_FRAME_RESERVED_13 = 13;
    /**
     * MAV_FRAME_MOCAP_NED - Odometry local coordinate frame of data given by a motion capture system, Z-down (x: North, y: East, z: Down).
     */
    public final static int MAV_FRAME_RESERVED_14 = 14;
    /**
     * MAV_FRAME_MOCAP_ENU - Odometry local coordinate frame of data given by a motion capture system, Z-up (x: East, y: North, z: Up).
     */
    public final static int MAV_FRAME_RESERVED_15 = 15;
    /**
     * MAV_FRAME_VISION_NED - Odometry local coordinate frame of data given by a vision estimation system, Z-down (x: North, y: East, z: Down).
     */
    public final static int MAV_FRAME_RESERVED_16 = 16;
    /**
     * MAV_FRAME_VISION_ENU - Odometry local coordinate frame of data given by a vision estimation system, Z-up (x: East, y: North, z: Up).
     */
    public final static int MAV_FRAME_RESERVED_17 = 17;
    /**
     * MAV_FRAME_ESTIM_NED - Odometry local coordinate frame of data given by an estimator running onboard the vehicle, Z-down (x: North, y: East, z: Down).
     */
    public final static int MAV_FRAME_RESERVED_18 = 18;
    /**
     * MAV_FRAME_ESTIM_ENU - Odometry local coordinate frame of data given by an estimator running onboard the vehicle, Z-up (x: East, y: North, z: Up).
     */
    public final static int MAV_FRAME_RESERVED_19 = 19;
    /**
     * Forward, Right, Down coordinate frame. This is a local frame with Z-down and arbitrary F/R alignment (i.e. not aligned with NED/earth frame).
     */
    public final static int MAV_FRAME_LOCAL_FRD = 20;
    /**
     * Forward, Left, Up coordinate frame. This is a local frame with Z-up and arbitrary F/L alignment (i.e. not aligned with ENU/earth frame).
     */
    public final static int MAV_FRAME_LOCAL_FLU = 21;
}
