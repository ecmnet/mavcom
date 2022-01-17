/**
 * Generated class : FENCE_ACTION
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface FENCE_ACTION Actions following geofence breach.
 **/
public interface FENCE_ACTION {
	/**
	 * Disable fenced mode. If used in a plan this would mean the next fence is
	 * disabled.
	 */
	public final static int FENCE_ACTION_NONE = 0;
	/**
	 * Fly to geofence MAV_CMD_NAV_FENCE_RETURN_POINT in GUIDED mode. Note: This
	 * action is only supported by ArduPlane, and may not be supported in all
	 * versions.
	 */
	public final static int FENCE_ACTION_GUIDED = 1;
	/**
	 * Report fence breach, but don't take action
	 */
	public final static int FENCE_ACTION_REPORT = 2;
	/**
	 * Fly to geofence MAV_CMD_NAV_FENCE_RETURN_POINT with manual throttle control
	 * in GUIDED mode. Note: This action is only supported by ArduPlane, and may not
	 * be supported in all versions.
	 */
	public final static int FENCE_ACTION_GUIDED_THR_PASS = 3;
	/**
	 * Return/RTL mode.
	 */
	public final static int FENCE_ACTION_RTL = 4;
	/**
	 * Hold at current location.
	 */
	public final static int FENCE_ACTION_HOLD = 5;
	/**
	 * Termination failsafe. Motors are shut down (some flight stacks may trigger
	 * other failsafe actions).
	 */
	public final static int FENCE_ACTION_TERMINATE = 6;
	/**
	 * Land at current location.
	 */
	public final static int FENCE_ACTION_LAND = 7;
}
