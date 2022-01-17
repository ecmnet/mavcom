/**
 * Generated class : WINCH_ACTIONS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface WINCH_ACTIONS Winch actions.
 **/
public interface WINCH_ACTIONS {
	/**
	 * Allow motor to freewheel.
	 */
	public final static int WINCH_RELAXED = 0;
	/**
	 * Wind or unwind specified length of line, optionally using specified rate.
	 */
	public final static int WINCH_RELATIVE_LENGTH_CONTROL = 1;
	/**
	 * Wind or unwind line at specified rate.
	 */
	public final static int WINCH_RATE_CONTROL = 2;
	/**
	 * Perform the locking sequence to relieve motor while in the fully retracted
	 * position. Only action and instance command parameters are used, others are
	 * ignored.
	 */
	public final static int WINCH_LOCK = 3;
	/**
	 * Sequence of drop, slow down, touch down, reel up, lock. Only action and
	 * instance command parameters are used, others are ignored.
	 */
	public final static int WINCH_DELIVER = 4;
	/**
	 * Engage motor and hold current position. Only action and instance command
	 * parameters are used, others are ignored.
	 */
	public final static int WINCH_HOLD = 5;
	/**
	 * Return the reel to the fully retracted position. Only action and instance
	 * command parameters are used, others are ignored.
	 */
	public final static int WINCH_RETRACT = 6;
	/**
	 * Load the reel with line. The winch will calculate the total loaded length and
	 * stop when the tension exceeds a threshold. Only action and instance command
	 * parameters are used, others are ignored.
	 */
	public final static int WINCH_LOAD_LINE = 7;
	/**
	 * Spool out the entire length of the line. Only action and instance command
	 * parameters are used, others are ignored.
	 */
	public final static int WINCH_ABANDON_LINE = 8;
}
