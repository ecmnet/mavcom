/**
 * Generated class : GIMBAL_DEVICE_FLAGS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface GIMBAL_DEVICE_FLAGS
 * Flags for gimbal device (lower level) operation.
 **/
public interface GIMBAL_DEVICE_FLAGS {
    /**
     * Set to retracted safe position (no stabilization), takes presedence over all other flags.
     */
    public final static int GIMBAL_DEVICE_FLAGS_RETRACT = 1;
    /**
     * Set to neutral/default position, taking precedence over all other flags except RETRACT. Neutral is commonly forward-facing and horizontal (roll=pitch=yaw=0) but may be any orientation.
     */
    public final static int GIMBAL_DEVICE_FLAGS_NEUTRAL = 2;
    /**
     * Lock roll angle to absolute angle relative to horizon (not relative to vehicle). This is generally the default with a stabilizing gimbal.
     */
    public final static int GIMBAL_DEVICE_FLAGS_ROLL_LOCK = 4;
    /**
     * Lock pitch angle to absolute angle relative to horizon (not relative to vehicle). This is generally the default with a stabilizing gimbal.
     */
    public final static int GIMBAL_DEVICE_FLAGS_PITCH_LOCK = 8;
    /**
     * Lock yaw angle to absolute angle relative to North (not relative to vehicle). If this flag is set, the yaw angle and z component of angular velocity are relative to North (earth frame, x-axis pointing North), else they are relative to the vehicle heading (vehicle frame, earth frame rotated so that the x-axis is pointing forward).
     */
    public final static int GIMBAL_DEVICE_FLAGS_YAW_LOCK = 16;
}
