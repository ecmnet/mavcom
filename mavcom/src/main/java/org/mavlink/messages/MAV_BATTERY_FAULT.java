/**
 * Generated class : MAV_BATTERY_FAULT
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_BATTERY_FAULT
 * Smart battery supply status/fault flags (bitmask) for health indication. The battery must also report either MAV_BATTERY_CHARGE_STATE_FAILED or MAV_BATTERY_CHARGE_STATE_UNHEALTHY if any of these are set.
 **/
public interface MAV_BATTERY_FAULT {
    /**
     * Battery has deep discharged.
     */
    public final static int MAV_BATTERY_FAULT_DEEP_DISCHARGE = 1;
    /**
     * Voltage spikes.
     */
    public final static int MAV_BATTERY_FAULT_SPIKES = 2;
    /**
     * One or more cells have failed. Battery should also report MAV_BATTERY_CHARGE_STATE_FAILE (and should not be used).
     */
    public final static int MAV_BATTERY_FAULT_CELL_FAIL = 4;
    /**
     * Over-current fault.
     */
    public final static int MAV_BATTERY_FAULT_OVER_CURRENT = 8;
    /**
     * Over-temperature fault.
     */
    public final static int MAV_BATTERY_FAULT_OVER_TEMPERATURE = 16;
    /**
     * Under-temperature fault.
     */
    public final static int MAV_BATTERY_FAULT_UNDER_TEMPERATURE = 32;
    /**
     * Vehicle voltage is not compatible with this battery (batteries on same power rail should have similar voltage).
     */
    public final static int MAV_BATTERY_FAULT_INCOMPATIBLE_VOLTAGE = 64;
    /**
     * Battery firmware is not compatible with current autopilot firmware.
     */
    public final static int MAV_BATTERY_FAULT_INCOMPATIBLE_FIRMWARE = 128;
    /**
     * Battery is not compatible due to cell configuration (e.g. 5s1p when vehicle requires 6s).
     */
    public final static int BATTERY_FAULT_INCOMPATIBLE_CELLS_CONFIGURATION = 256;
}
