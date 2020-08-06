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
     * Winch thread is fully retracted
     */
    public final static int MAV_WINCH_STATUS_FULLY_RETRACTED = 2;
    /**
     * Winch motor is moving
     */
    public final static int MAV_WINCH_STATUS_MOVING = 4;
    /**
     * Winch clutch is engaged allowing motor to move freely
     */
    public final static int MAV_WINCH_STATUS_CLUTCH_ENGAGED = 8;
}
