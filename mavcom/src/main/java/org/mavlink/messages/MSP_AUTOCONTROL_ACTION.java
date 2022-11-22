/**
 * Generated class : MSP_AUTOCONTROL_ACTION
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MSP_AUTOCONTROL_ACTION
 * Auto-Control action (Bit 16-63)
 **/
public interface MSP_AUTOCONTROL_ACTION {
    /**
     * Returns to local home position and lands device
     */
    public final static int RTL = 16;
    /**
     * Execute a MSP mission (param3)
     */
    public final static int AUTO_MISSION = 18;
    /**
     * Execute a DEBUG1 sequence for testing
     */
    public final static int DEBUG_MODE1 = 19;
    /**
     * Execute a DEBUG2 sequence for testing
     */
    public final static int DEBUG_MODE2 = 20;
    /**
     * Saves a map corresponding to the global position
     */
    public final static int SAVE_MAP2D = 21;
    /**
     * Load a map corresponding to the global position
     */
    public final static int LOAD_MAP2D = 22;
    /**
     * Rotates to a given heading
     */
    public final static int ROTATE = 25;
    /**
     * Offboard takeoff
     */
    public final static int TAKEOFF = 26;
    /**
     * Offboard landing
     */
    public final static int LAND = 27;
    /**
     * Offboard precision lock
     */
    public final static int LOCK = 28;
    /**
     * Execute SITL action 1
     */
    public final static int SITL_ACTION1 = 61;
    /**
     * Execute SITL action 2
     */
    public final static int SITL_ACTION2 = 62;
    /**
     * Execute SITL action 3
     */
    public final static int SITL_ACTION3 = 63;
}
