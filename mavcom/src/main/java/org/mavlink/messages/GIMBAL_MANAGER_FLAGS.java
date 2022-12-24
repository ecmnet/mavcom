/**
 * Generated class : GIMBAL_MANAGER_FLAGS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface GIMBAL_MANAGER_FLAGS
 * Flags for high level gimbal manager operation The first 16 bits are identical to the GIMBAL_DEVICE_FLAGS.
 **/
public interface GIMBAL_MANAGER_FLAGS {
    /**
     * Based on GIMBAL_DEVICE_FLAGS_RETRACT.
     */
    public final static int GIMBAL_MANAGER_FLAGS_RETRACT = 1;
    /**
     * Based on GIMBAL_DEVICE_FLAGS_NEUTRAL.
     */
    public final static int GIMBAL_MANAGER_FLAGS_NEUTRAL = 2;
    /**
     * Based on GIMBAL_DEVICE_FLAGS_ROLL_LOCK.
     */
    public final static int GIMBAL_MANAGER_FLAGS_ROLL_LOCK = 4;
    /**
     * Based on GIMBAL_DEVICE_FLAGS_PITCH_LOCK.
     */
    public final static int GIMBAL_MANAGER_FLAGS_PITCH_LOCK = 8;
    /**
     * Based on GIMBAL_DEVICE_FLAGS_YAW_LOCK.
     */
    public final static int GIMBAL_MANAGER_FLAGS_YAW_LOCK = 16;
    /**
     * Based on GIMBAL_DEVICE_FLAGS_YAW_IN_VEHICLE_FRAME.
     */
    public final static int GIMBAL_MANAGER_FLAGS_YAW_IN_VEHICLE_FRAME = 32;
    /**
     * Based on GIMBAL_DEVICE_FLAGS_YAW_IN_EARTH_FRAME.
     */
    public final static int GIMBAL_MANAGER_FLAGS_YAW_IN_EARTH_FRAME = 64;
    /**
     * Based on GIMBAL_DEVICE_FLAGS_ACCEPTS_YAW_IN_EARTH_FRAME.
     */
    public final static int GIMBAL_MANAGER_FLAGS_ACCEPTS_YAW_IN_EARTH_FRAME = 128;
    /**
     * Based on GIMBAL_DEVICE_FLAGS_RC_EXCLUSIVE.
     */
    public final static int GIMBAL_MANAGER_FLAGS_RC_EXCLUSIVE = 256;
    /**
     * Based on GIMBAL_DEVICE_FLAGS_RC_MIXED.
     */
    public final static int GIMBAL_MANAGER_FLAGS_RC_MIXED = 512;
}
