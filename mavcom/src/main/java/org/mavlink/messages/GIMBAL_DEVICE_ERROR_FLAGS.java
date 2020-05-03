/**
 * Generated class : GIMBAL_DEVICE_ERROR_FLAGS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface GIMBAL_DEVICE_ERROR_FLAGS
 * Gimbal device (low level) error flags (bitmap, 0 means no error)
 **/
public interface GIMBAL_DEVICE_ERROR_FLAGS {
    /**
     * Gimbal device is limited by hardware roll limit.
     */
    public final static int GIMBAL_DEVICE_ERROR_FLAGS_AT_ROLL_LIMIT = 1;
    /**
     * Gimbal device is limited by hardware pitch limit.
     */
    public final static int GIMBAL_DEVICE_ERROR_FLAGS_AT_PITCH_LIMIT = 2;
    /**
     * Gimbal device is limited by hardware yaw limit.
     */
    public final static int GIMBAL_DEVICE_ERROR_FLAGS_AT_YAW_LIMIT = 4;
    /**
     * There is an error with the gimbal encoders.
     */
    public final static int GIMBAL_DEVICE_ERROR_FLAGS_ENCODER_ERROR = 8;
    /**
     * There is an error with the gimbal power source.
     */
    public final static int GIMBAL_DEVICE_ERROR_FLAGS_POWER_ERROR = 16;
    /**
     * There is an error with the gimbal motor's.
     */
    public final static int GIMBAL_DEVICE_ERROR_FLAGS_MOTOR_ERROR = 32;
    /**
     * There is an error with the gimbal's software.
     */
    public final static int GIMBAL_DEVICE_ERROR_FLAGS_SOFTWARE_ERROR = 64;
    /**
     * There is an error with the gimbal's communication.
     */
    public final static int GIMBAL_DEVICE_ERROR_FLAGS_COMMS_ERROR = 128;
}
