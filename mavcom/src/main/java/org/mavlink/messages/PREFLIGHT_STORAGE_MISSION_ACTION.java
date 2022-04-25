/**
 * Generated class : PREFLIGHT_STORAGE_MISSION_ACTION
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface PREFLIGHT_STORAGE_MISSION_ACTION
 * Actions for reading and writing plan information (mission, rally points, geofence) between persistent and volatile storage when using MAV_CMD_PREFLIGHT_STORAGE.
        (Commonly missions are loaded from persistent storage (flash/EEPROM) into volatile storage (RAM) on startup and written back when they are changed.)
 **/
public interface PREFLIGHT_STORAGE_MISSION_ACTION {
    /**
     * Read current mission data from persistent storage
     */
    public final static int MISSION_READ_PERSISTENT = 0;
    /**
     * Write current mission data to persistent storage
     */
    public final static int MISSION_WRITE_PERSISTENT = 1;
    /**
     * Erase all mission data stored on the vehicle (both persistent and volatile storage)
     */
    public final static int MISSION_RESET_DEFAULT = 2;
}
