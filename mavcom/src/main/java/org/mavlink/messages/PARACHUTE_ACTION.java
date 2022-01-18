/**
 * Generated class : PARACHUTE_ACTION
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface PARACHUTE_ACTION
 * Parachute actions. Trigger release and enable/disable auto-release.
 **/
public interface PARACHUTE_ACTION {
    /**
     * Disable auto-release of parachute (i.e. release triggered by crash detectors).
     */
    public final static int PARACHUTE_DISABLE = 0;
    /**
     * Enable auto-release of parachute.
     */
    public final static int PARACHUTE_ENABLE = 1;
    /**
     * Release parachute and kill motors.
     */
    public final static int PARACHUTE_RELEASE = 2;
}
