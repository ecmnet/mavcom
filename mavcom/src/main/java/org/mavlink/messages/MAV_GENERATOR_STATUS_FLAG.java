/**
 * Generated class : MAV_GENERATOR_STATUS_FLAG
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_GENERATOR_STATUS_FLAG
 * Flags to report status/failure cases for a power generator (used in GENERATOR_STATUS). Note that FAULTS are conditions that cause the generator to fail. Warnings are conditions that require attention before the next use (they indicate the system is not operating properly).
 **/
public interface MAV_GENERATOR_STATUS_FLAG {
    /**
     * Generator is off.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_OFF = 1;
    /**
     * Generator is ready to start generating power.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_READY = 2;
    /**
     * Generator is generating power.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_GENERATING = 4;
    /**
     * Generator is charging the batteries (generating enough power to charge and provide the load).
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_CHARGING = 8;
    /**
     * Generator is operating at a reduced maximum power.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_REDUCED_POWER = 16;
    /**
     * Generator is providing the maximum output.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_MAXPOWER = 32;
    /**
     * Generator is near the maximum operating temperature, cooling is insufficient.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_OVERTEMP_WARNING = 64;
    /**
     * Generator hit the maximum operating temperature and shutdown.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_OVERTEMP_FAULT = 128;
    /**
     * Power electronics are near the maximum operating temperature, cooling is insufficient.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_ELECTRONICS_OVERTEMP_WARNING = 256;
    /**
     * Power electronics hit the maximum operating temperature and shutdown.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_ELECTRONICS_OVERTEMP_FAULT = 512;
    /**
     * Power electronics experienced a fault and shutdown.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_ELECTRONICS_FAULT = 1024;
    /**
     * The power source supplying the generator failed e.g. mechanical generator stopped, tether is no longer providing power, solar cell is in shade, hydrogen reaction no longer happening.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_POWERSOURCE_FAULT = 2048;
    /**
     * Generator controller having communication problems.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_COMMUNICATION_WARNING = 4096;
    /**
     * Power electronic or generator cooling system error.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_COOLING_WARNING = 8192;
    /**
     * Generator controller power rail experienced a fault.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_POWER_RAIL_FAULT = 16384;
    /**
     * Generator controller exceeded the overcurrent threshold and shutdown to prevent damage.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_OVERCURRENT_FAULT = 32768;
    /**
     * Generator controller detected a high current going into the batteries and shutdown to prevent battery damage.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_BATTERY_OVERCHARGE_CURRENT_FAULT = 65536;
    /**
     * Generator controller exceeded it's overvoltage threshold and shutdown to prevent it exceeding the voltage rating.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_OVERVOLTAGE_FAULT = 131072;
    /**
     * Batteries are under voltage (generator will not start).
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_BATTERY_UNDERVOLT_FAULT = 262144;
    /**
     * Generator start is inhibited by e.g. a safety switch.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_START_INHIBITED = 524288;
    /**
     * Generator requires maintenance.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_MAINTENANCE_REQUIRED = 1048576;
    /**
     * Generator is not ready to generate yet.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_WARMING_UP = 2097152;
    /**
     * Generator is idle.
     */
    public final static int MAV_GENERATOR_STATUS_FLAG_IDLE = 4194304;
}
