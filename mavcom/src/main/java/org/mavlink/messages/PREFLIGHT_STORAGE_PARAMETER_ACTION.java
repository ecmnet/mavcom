/**
 * Generated class : PREFLIGHT_STORAGE_PARAMETER_ACTION
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface PREFLIGHT_STORAGE_PARAMETER_ACTION
 * Actions for reading/writing parameters between persistent and volatile storage when using MAV_CMD_PREFLIGHT_STORAGE.
        (Commonly parameters are loaded from persistent storage (flash/EEPROM) into volatile storage (RAM) on startup and written back when they are changed.)
 **/
public interface PREFLIGHT_STORAGE_PARAMETER_ACTION {
    /**
     * Read all parameters from persistent storage. Replaces values in volatile storage.
     */
    public final static int PARAM_READ_PERSISTENT = 0;
    /**
     * Write all parameter values to persistent storage (flash/EEPROM)
     */
    public final static int PARAM_WRITE_PERSISTENT = 1;
    /**
     * Reset all user configurable parameters to their default value (including airframe selection, sensor calibration data, safety settings, and so on). Does not reset values that contain operation counters and vehicle computed statistics.
     */
    public final static int PARAM_RESET_CONFIG_DEFAULT = 2;
    /**
     * Reset only sensor calibration parameters to factory defaults (or firmware default if not available)
     */
    public final static int PARAM_RESET_SENSOR_DEFAULT = 3;
    /**
     * Reset all parameters, including operation counters, to default values
     */
    public final static int PARAM_RESET_ALL_DEFAULT = 4;
}
