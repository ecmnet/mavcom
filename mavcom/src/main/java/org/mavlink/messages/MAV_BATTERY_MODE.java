/**
 * Generated class : MAV_BATTERY_MODE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface MAV_BATTERY_MODE Battery mode. Note, the normal operation mode
 * (i.e. when flying) should be reported as MAV_BATTERY_MODE_UNKNOWN to allow
 * message trimming in normal flight.
 **/
public interface MAV_BATTERY_MODE {
	/**
	 * Battery mode not supported/unknown battery mode/normal operation.
	 */
	public final static int MAV_BATTERY_MODE_UNKNOWN = 0;
	/**
	 * Battery is auto discharging (towards storage level).
	 */
	public final static int MAV_BATTERY_MODE_AUTO_DISCHARGING = 1;
	/**
	 * Battery in hot-swap mode (current limited to prevent spikes that might damage
	 * sensitive electrical circuits).
	 */
	public final static int MAV_BATTERY_MODE_HOT_SWAP = 2;
}
