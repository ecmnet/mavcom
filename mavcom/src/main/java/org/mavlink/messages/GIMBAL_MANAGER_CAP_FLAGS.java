/**
 * Generated class : GIMBAL_MANAGER_CAP_FLAGS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface GIMBAL_MANAGER_CAP_FLAGS
 * Gimbal manager high level capability flags (bitmap). The first 16 bits are identical to the GIMBAL_DEVICE_CAP_FLAGS which are identical with GIMBAL_DEVICE_FLAGS. However, the gimbal manager does not need to copy the flags from the gimbal but can also enhance the capabilities and thus add flags.
 **/
public interface GIMBAL_MANAGER_CAP_FLAGS {
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_RETRACT.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_HAS_RETRACT = 1;
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_NEUTRAL.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_HAS_NEUTRAL = 2;
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_ROLL_AXIS.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_HAS_ROLL_AXIS = 4;
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_ROLL_FOLLOW.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_HAS_ROLL_FOLLOW = 8;
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_ROLL_LOCK.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_HAS_ROLL_LOCK = 16;
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_PITCH_AXIS.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_HAS_PITCH_AXIS = 32;
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_PITCH_FOLLOW.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_HAS_PITCH_FOLLOW = 64;
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_PITCH_LOCK.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_HAS_PITCH_LOCK = 128;
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_YAW_AXIS.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_HAS_YAW_AXIS = 256;
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_YAW_FOLLOW.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_HAS_YAW_FOLLOW = 512;
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_YAW_LOCK.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_HAS_YAW_LOCK = 1024;
    /**
     * Based on GIMBAL_DEVICE_CAP_FLAGS_SUPPORTS_INFINITE_YAW.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_SUPPORTS_INFINITE_YAW = 2048;
    /**
     * Gimbal manager supports to point to a local position.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_CAN_POINT_LOCATION_LOCAL = 65536;
    /**
     * Gimbal manager supports to point to a global latitude, longitude, altitude position.
     */
    public final static int GIMBAL_MANAGER_CAP_FLAGS_CAN_POINT_LOCATION_GLOBAL = 131072;
}
