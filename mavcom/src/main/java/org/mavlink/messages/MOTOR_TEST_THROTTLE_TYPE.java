/**
 * Generated class : MOTOR_TEST_THROTTLE_TYPE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface MOTOR_TEST_THROTTLE_TYPE Defines how throttle value is represented
 * in MAV_CMD_DO_MOTOR_TEST.
 **/
public interface MOTOR_TEST_THROTTLE_TYPE {
	/**
	 * Throttle as a percentage (0 ~ 100)
	 */
	public final static int MOTOR_TEST_THROTTLE_PERCENT = 0;
	/**
	 * Throttle as an absolute PWM value (normally in range of 1000~2000).
	 */
	public final static int MOTOR_TEST_THROTTLE_PWM = 1;
	/**
	 * Throttle pass-through from pilot's transmitter.
	 */
	public final static int MOTOR_TEST_THROTTLE_PILOT = 2;
	/**
	 * Per-motor compass calibration test.
	 */
	public final static int MOTOR_TEST_COMPASS_CAL = 3;
}
