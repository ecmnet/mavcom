/**
 * Generated class : SAFETY_SWITCH_STATE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface SAFETY_SWITCH_STATE
 * Possible safety switch states.
 **/
public interface SAFETY_SWITCH_STATE {
    /**
     * Safety switch is engaged and vehicle should be safe to approach.
     */
    public final static int SAFETY_SWITCH_STATE_SAFE = 0;
    /**
     * Safety switch is NOT engaged and motors, propellers and other actuators should be considered active.
     */
    public final static int SAFETY_SWITCH_STATE_DANGEROUS = 1;
}
