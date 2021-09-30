/**
 * Generated class : MAV_FRAME
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_FRAME
 * Co-ordinate frames used by MAVLink. Not all frames are supported by all commands, messages, or vehicles.
      
      Global frames use the following naming conventions:
      - `GLOBAL`: Global co-ordinate frame with WGS84 latitude/longitude and altitude positive over mean sea level (MSL) by default. 
        The following modifiers may be used with `GLOBAL`:
        - `RELATIVE_ALT`: Altitude is relative to the vehicle home position rather than MSL
        - `TERRAIN_ALT`: Altitude is relative to ground level rather than MSL
        - `INT`: Latitude/longitude (in degrees) are scaled by multiplying by 1E7  

      Local frames use the following naming conventions:
      - `LOCAL`: Origin of local frame is fixed relative to earth. Unless otherwise specified this origin is the origin of the vehicle position-estimator ("EKF").
      - `BODY`: Origin of local frame travels with the vehicle. NOTE, `BODY` does NOT indicate alignment of frame axis with vehicle attitude.
      - `OFFSET`: Deprecated synonym for `BODY` (origin travels with the vehicle). Not to be used for new frames.

      Some deprecated frames do not follow these conventions (e.g. MAV_FRAME_BODY_NED and MAV_FRAME_BODY_OFFSET_NED).
 **/
public interface MAV_FRAME {
    /**
     * Global (WGS84) coordinate frame + MSL altitude. First value / x: latitude, second value / y: longitude, third value / z: positive altitude over mean sea level (MSL).
     */
    public final static int MAV_FRAME_GLOBAL = 0;
    /**
     * NED local tangent frame (x: North, y: East, z: Down) with origin fixed relative to earth.
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
     * ENU local tangent frame (x: East, y: North, z: Up) with origin fixed relative to earth.
     */
    public final static int MAV_FRAME_LOCAL_ENU = 4;
    /**
     * Global (WGS84) coordinate frame (scaled) + MSL altitude. First value / x: latitude in degrees*1E7, second value / y: longitude in degrees*1E7, third value / z: positive altitude over mean sea level (MSL).
     */
    public final static int MAV_FRAME_GLOBAL_INT = 5;
    /**
     * Global (WGS84) coordinate frame (scaled) + altitude relative to the home position. First value / x: latitude in degrees*1E7, second value / y: longitude in degrees*1E7, third value / z: positive altitude with 0 being at the altitude of the home location.
     */
    public final static int MAV_FRAME_GLOBAL_RELATIVE_ALT_INT = 6;
    /**
     * NED local tangent frame (x: North, y: East, z: Down) with origin that travels with the vehicle.
     */
    public final static int MAV_FRAME_LOCAL_OFFSET_NED = 7;
    /**
     * Same as MAV_FRAME_LOCAL_NED when used to represent position values. Same as MAV_FRAME_BODY_FRD when used with velocity/accelaration values.
     */
    public final static int MAV_FRAME_BODY_NED = 8;
    /**
     * This is the same as MAV_FRAME_BODY_FRD.
     */
    public final static int MAV_FRAME_BODY_OFFSET_NED = 9;
    /**
     * Global (WGS84) coordinate frame with AGL altitude (at the waypoint coordinate). First value / x: latitude in degrees, second value / y: longitude in degrees, third value / z: positive altitude in meters with 0 being at ground level in terrain model.
     */
    public final static int MAV_FRAME_GLOBAL_TERRAIN_ALT = 10;
    /**
     * Global (WGS84) coordinate frame (scaled) with AGL altitude (at the waypoint coordinate). First value / x: latitude in degrees*1E7, second value / y: longitude in degrees*1E7, third value / z: positive altitude in meters with 0 being at ground level in terrain model.
     */
    public final static int MAV_FRAME_GLOBAL_TERRAIN_ALT_INT = 11;
    /**
     * FRD local tangent frame (x: Forward, y: Right, z: Down) with origin that travels with vehicle. The forward axis is aligned to the front of the vehicle in the horizontal plane.
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
     * FRD local tangent frame (x: Forward, y: Right, z: Down) with origin fixed relative to earth. The forward axis is aligned to the front of the vehicle in the horizontal plane.
     */
    public final static int MAV_FRAME_LOCAL_FRD = 20;
    /**
     * FLU local tangent frame (x: Forward, y: Left, z: Up) with origin fixed relative to earth. The forward axis is aligned to the front of the vehicle in the horizontal plane.
     */
    public final static int MAV_FRAME_LOCAL_FLU = 21;
}
