/**
 * Generated class : WINCH_ACTIONS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface WINCH_ACTIONS
 * Winch actions.
 **/
public interface WINCH_ACTIONS {
    /**
     * Relax winch.
     */
    public final static int WINCH_RELAXED = 0;
    /**
     * Wind or unwind specified length of cable, optionally using specified rate.
     */
    public final static int WINCH_RELATIVE_LENGTH_CONTROL = 1;
    /**
     * Wind or unwind cable at specified rate.
     */
    public final static int WINCH_RATE_CONTROL = 2;
}
