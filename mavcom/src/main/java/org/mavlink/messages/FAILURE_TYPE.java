/**
 * Generated class : FAILURE_TYPE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface FAILURE_TYPE List of possible failure type to inject.
 **/
public interface FAILURE_TYPE {
	/**
	 * No failure injected, used to reset a previous failure.
	 */
	public final static int FAILURE_TYPE_OK = 0;
	/**
	 * Sets unit off, so completely non-responsive.
	 */
	public final static int FAILURE_TYPE_OFF = 1;
	/**
	 * Unit is stuck e.g. keeps reporting the same value.
	 */
	public final static int FAILURE_TYPE_STUCK = 2;
	/**
	 * Unit is reporting complete garbage.
	 */
	public final static int FAILURE_TYPE_GARBAGE = 3;
	/**
	 * Unit is consistently wrong.
	 */
	public final static int FAILURE_TYPE_WRONG = 4;
	/**
	 * Unit is slow, so e.g. reporting at slower than expected rate.
	 */
	public final static int FAILURE_TYPE_SLOW = 5;
	/**
	 * Data of unit is delayed in time.
	 */
	public final static int FAILURE_TYPE_DELAYED = 6;
	/**
	 * Unit is sometimes working, sometimes not.
	 */
	public final static int FAILURE_TYPE_INTERMITTENT = 7;
}
