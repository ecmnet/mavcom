/**
 * Generated class : MAV_PROTOCOL_CAPABILITY
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_PROTOCOL_CAPABILITY
 * Bitmask of (optional) autopilot capabilities (64 bit). If a bit is set, the autopilot supports this capability.
 **/
public interface MAV_PROTOCOL_CAPABILITY {
    /**
     * Autopilot supports the MISSION_ITEM float message type.
          Note that MISSION_ITEM is deprecated, and autopilots should use MISSION_INT instead.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_MISSION_FLOAT = 1;
    /**
     * Autopilot supports the new param float message type.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_PARAM_FLOAT = 2;
    /**
     * Autopilot supports MISSION_ITEM_INT scaled integer message type.
          Note that this flag must always be set if missions are supported, because missions must always use MISSION_ITEM_INT (rather than MISSION_ITEM, which is deprecated).
     */
    public final static int MAV_PROTOCOL_CAPABILITY_MISSION_INT = 4;
    /**
     * Autopilot supports COMMAND_INT scaled integer message type.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_COMMAND_INT = 8;
    /**
     * Parameter protocol uses byte-wise encoding of parameter values into param_value (float) fields: https://mavlink.io/en/services/parameter.html#parameter-encoding.
          Note that either this flag or MAV_PROTOCOL_CAPABILITY_PARAM_ENCODE_C_CAST should be set if the parameter protocol is supported.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_PARAM_ENCODE_BYTEWISE = 16;
    /**
     * Autopilot supports the File Transfer Protocol v1: https://mavlink.io/en/services/ftp.html.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_FTP = 32;
    /**
     * Autopilot supports commanding attitude offboard.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_SET_ATTITUDE_TARGET = 64;
    /**
     * Autopilot supports commanding position and velocity targets in local NED frame.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_SET_POSITION_TARGET_LOCAL_NED = 128;
    /**
     * Autopilot supports commanding position and velocity targets in global scaled integers.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_SET_POSITION_TARGET_GLOBAL_INT = 256;
    /**
     * Autopilot supports terrain protocol / data handling.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_TERRAIN = 512;
    /**
     * Reserved for future use.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_RESERVED3 = 1024;
    /**
     * Autopilot supports the MAV_CMD_DO_FLIGHTTERMINATION command (flight termination).
     */
    public final static int MAV_PROTOCOL_CAPABILITY_FLIGHT_TERMINATION = 2048;
    /**
     * Autopilot supports onboard compass calibration.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_COMPASS_CALIBRATION = 4096;
    /**
     * Autopilot supports MAVLink version 2.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_MAVLINK2 = 8192;
    /**
     * Autopilot supports mission fence protocol.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_MISSION_FENCE = 16384;
    /**
     * Autopilot supports mission rally point protocol.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_MISSION_RALLY = 32768;
    /**
     * Reserved for future use.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_RESERVED2 = 65536;
    /**
     * Parameter protocol uses C-cast of parameter values to set the param_value (float) fields: https://mavlink.io/en/services/parameter.html#parameter-encoding.
          Note that either this flag or MAV_PROTOCOL_CAPABILITY_PARAM_ENCODE_BYTEWISE should be set if the parameter protocol is supported.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_PARAM_ENCODE_C_CAST = 131072;
    /**
     * This component implements/is a gimbal manager. This means the GIMBAL_MANAGER_INFORMATION, and other messages can be requested.
     */
    public final static int MAV_PROTOCOL_CAPABILITY_COMPONENT_IMPLEMENTS_GIMBAL_MANAGER = 262144;
    /**
     * Component supports locking control to a particular GCS independent of its system (via MAV_CMD_REQUEST_OPERATOR_CONTROL).
     */
    public final static int MAV_PROTOCOL_CAPABILITY_COMPONENT_ACCEPTS_GCS_CONTROL = 524288;
}
