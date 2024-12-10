/**
 * Generated class : ORBIT_YAW_BEHAVIOUR
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface ORBIT_YAW_BEHAVIOUR
 * Yaw behaviour during orbit flight.
 **/
public interface ORBIT_YAW_BEHAVIOUR {
    /**
     * Vehicle front points to the center (default).
     */
    public final static int ORBIT_YAW_BEHAVIOUR_HOLD_FRONT_TO_CIRCLE_CENTER = 0;
    /**
     * Vehicle front holds heading when message received.
     */
    public final static int ORBIT_YAW_BEHAVIOUR_HOLD_INITIAL_HEADING = 1;
    /**
     * Yaw uncontrolled.
     */
    public final static int ORBIT_YAW_BEHAVIOUR_UNCONTROLLED = 2;
    /**
     * Vehicle front follows flight path (tangential to circle).
     */
    public final static int ORBIT_YAW_BEHAVIOUR_HOLD_FRONT_TANGENT_TO_CIRCLE = 3;
    /**
     * Yaw controlled by RC input.
     */
    public final static int ORBIT_YAW_BEHAVIOUR_RC_CONTROLLED = 4;
    /**
     * Vehicle uses current yaw behaviour (unchanged). The vehicle-default yaw behaviour is used if this value is specified when orbit is first commanded.
     */
    public final static int ORBIT_YAW_BEHAVIOUR_UNCHANGED = 5;
}
