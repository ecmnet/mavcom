/**
 * Generated class : MSP_AUTOCONTROL_MODE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MSP_AUTOCONTROL_MODE
 * Auto-Control modes (Bit 0-15)
 **/
public interface MSP_AUTOCONTROL_MODE {
    /**
     * Abort request: Terminates any autopilot action and returns to POSHOLD
     */
    public final static int ABORT = 0;
    /**
     * JumpBack mode
     */
    public final static int OBSTACLE_STOP = 1;
    /**
     * JumpBack mode
     */
    public final static int OBSTACLE_AVOIDANCE = 2;
    /**
     * JumpBack mode
     */
    public final static int COLLISION_PREVENTION = 3;
    /**
     * Follow person
     */
    public final static int FOLLOW_OBJECT = 4;
    /**
     * Interactive mode (by mouse)
     */
    public final static int INTERACTIVE = 9;
    /**
     * Takeoff procedure
     */
    public final static int TAKEOFF_PROCEDURE = 10;
    /**
     * Precision locking
     */
    public final static int PRECISION_LOCK = 11;
    /**
     * Enable STIL_MODE1
     */
    public final static int SITL_MODE1 = 14;
    /**
     * Enable STIL_MODE2
     */
    public final static int SITL_MODE2 = 15;
}
