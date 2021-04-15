/**
 * Generated class : ATTITUDE_TARGET_TYPEMASK
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface ATTITUDE_TARGET_TYPEMASK
 * Bitmap to indicate which dimensions should be ignored by the vehicle: a value of 0b00000000 indicates that none of the setpoint dimensions should be ignored.
 **/
public interface ATTITUDE_TARGET_TYPEMASK {
    /**
     * Ignore body roll rate
     */
    public final static int ATTITUDE_TARGET_TYPEMASK_BODY_ROLL_RATE_IGNORE = 1;
    /**
     * Ignore body pitch rate
     */
    public final static int ATTITUDE_TARGET_TYPEMASK_BODY_PITCH_RATE_IGNORE = 2;
    /**
     * Ignore body yaw rate
     */
    public final static int ATTITUDE_TARGET_TYPEMASK_BODY_YAW_RATE_IGNORE = 4;
    /**
     * Use 3D body thrust setpoint instead of throttle
     */
    public final static int ATTITUDE_TARGET_TYPEMASK_THRUST_BODY_SET = 32;
    /**
     * Ignore throttle
     */
    public final static int ATTITUDE_TARGET_TYPEMASK_THROTTLE_IGNORE = 64;
    /**
     * Ignore attitude
     */
    public final static int ATTITUDE_TARGET_TYPEMASK_ATTITUDE_IGNORE = 128;
}
