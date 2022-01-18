/**
 * Generated class : ACTUATOR_CONFIGURATION
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface ACTUATOR_CONFIGURATION
 * Actuator configuration, used to change a setting on an actuator. Component information metadata can be used to know which outputs support which commands.
 **/
public interface ACTUATOR_CONFIGURATION {
    /**
     * Do nothing.
     */
    public final static int ACTUATOR_CONFIGURATION_NONE = 0;
    /**
     * Command the actuator to beep now.
     */
    public final static int ACTUATOR_CONFIGURATION_BEEP = 1;
    /**
     * Permanently set the actuator (ESC) to 3D mode (reversible thrust).
     */
    public final static int ACTUATOR_CONFIGURATION_3D_MODE_ON = 2;
    /**
     * Permanently set the actuator (ESC) to non 3D mode (non-reversible thrust).
     */
    public final static int ACTUATOR_CONFIGURATION_3D_MODE_OFF = 3;
    /**
     * Permanently set the actuator (ESC) to spin direction 1 (which can be clockwise or counter-clockwise).
     */
    public final static int ACTUATOR_CONFIGURATION_SPIN_DIRECTION1 = 4;
    /**
     * Permanently set the actuator (ESC) to spin direction 2 (opposite of direction 1).
     */
    public final static int ACTUATOR_CONFIGURATION_SPIN_DIRECTION2 = 5;
}
