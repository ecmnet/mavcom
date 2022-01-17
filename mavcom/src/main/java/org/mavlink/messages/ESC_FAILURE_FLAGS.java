/**
 * Generated class : ESC_FAILURE_FLAGS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface ESC_FAILURE_FLAGS Flags to report ESC failures.
 **/
public interface ESC_FAILURE_FLAGS {
	/**
	 * No ESC failure.
	 */
	public final static int ESC_FAILURE_NONE = 0;
	/**
	 * Over current failure.
	 */
	public final static int ESC_FAILURE_OVER_CURRENT = 1;
	/**
	 * Over voltage failure.
	 */
	public final static int ESC_FAILURE_OVER_VOLTAGE = 2;
	/**
	 * Over temperature failure.
	 */
	public final static int ESC_FAILURE_OVER_TEMPERATURE = 4;
	/**
	 * Over RPM failure.
	 */
	public final static int ESC_FAILURE_OVER_RPM = 8;
	/**
	 * Inconsistent command failure i.e. out of bounds.
	 */
	public final static int ESC_FAILURE_INCONSISTENT_CMD = 16;
	/**
	 * Motor stuck failure.
	 */
	public final static int ESC_FAILURE_MOTOR_STUCK = 32;
	/**
	 * Generic ESC failure.
	 */
	public final static int ESC_FAILURE_GENERIC = 64;
}
