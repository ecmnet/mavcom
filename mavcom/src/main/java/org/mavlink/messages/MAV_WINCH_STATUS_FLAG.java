/**
 * Generated class : MAV_WINCH_STATUS_FLAG
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_WINCH_STATUS_FLAG
 * Winch status flags used in WINCH_STATUS
 **/
public interface MAV_WINCH_STATUS_FLAG {
    /**
     * Winch is healthy
     */
    public final static int MAV_WINCH_STATUS_HEALTHY = 1;
    /**
     * Winch line is fully retracted
     */
    public final static int MAV_WINCH_STATUS_FULLY_RETRACTED = 2;
    /**
     * Winch motor is moving
     */
    public final static int MAV_WINCH_STATUS_MOVING = 4;
    /**
     * Winch clutch is engaged allowing motor to move freely.
     */
    public final static int MAV_WINCH_STATUS_CLUTCH_ENGAGED = 8;
    /**
     * Winch is locked by locking mechanism.
     */
    public final static int MAV_WINCH_STATUS_LOCKED = 16;
    /**
     * Winch is gravity dropping payload.
     */
    public final static int MAV_WINCH_STATUS_DROPPING = 32;
    /**
     * Winch is arresting payload descent.
     */
    public final static int MAV_WINCH_STATUS_ARRESTING = 64;
    /**
     * Winch is using torque measurements to sense the ground.
     */
    public final static int MAV_WINCH_STATUS_GROUND_SENSE = 128;
    /**
     * Winch is returning to the fully retracted position.
     */
    public final static int MAV_WINCH_STATUS_RETRACTING = 256;
    /**
     * Winch is redelivering the payload. This is a failover state if the line tension goes above a threshold during RETRACTING.
     */
    public final static int MAV_WINCH_STATUS_REDELIVER = 512;
    /**
     * Winch is abandoning the line and possibly payload. Winch unspools the entire calculated line length. This is a failover state from REDELIVER if the number of attempts exceeds a threshold.
     */
    public final static int MAV_WINCH_STATUS_ABANDON_LINE = 1024;
    /**
     * Winch is engaging the locking mechanism.
     */
    public final static int MAV_WINCH_STATUS_LOCKING = 2048;
    /**
     * Winch is spooling on line.
     */
    public final static int MAV_WINCH_STATUS_LOAD_LINE = 4096;
    /**
     * Winch is loading a payload.
     */
    public final static int MAV_WINCH_STATUS_LOAD_PAYLOAD = 8192;
}
