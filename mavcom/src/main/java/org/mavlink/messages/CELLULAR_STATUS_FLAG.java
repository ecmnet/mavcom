/**
 * Generated class : CELLULAR_STATUS_FLAG
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface CELLULAR_STATUS_FLAG
 * These flags encode the cellular network status
 **/
public interface CELLULAR_STATUS_FLAG {
    /**
     * State unknown or not reportable.
     */
    public final static int CELLULAR_STATUS_FLAG_UNKNOWN = 0;
    /**
     * Modem is unusable
     */
    public final static int CELLULAR_STATUS_FLAG_FAILED = 1;
    /**
     * Modem is being initialized
     */
    public final static int CELLULAR_STATUS_FLAG_INITIALIZING = 2;
    /**
     * Modem is locked
     */
    public final static int CELLULAR_STATUS_FLAG_LOCKED = 3;
    /**
     * Modem is not enabled and is powered down
     */
    public final static int CELLULAR_STATUS_FLAG_DISABLED = 4;
    /**
     * Modem is currently transitioning to the CELLULAR_STATUS_FLAG_DISABLED state
     */
    public final static int CELLULAR_STATUS_FLAG_DISABLING = 5;
    /**
     * Modem is currently transitioning to the CELLULAR_STATUS_FLAG_ENABLED state
     */
    public final static int CELLULAR_STATUS_FLAG_ENABLING = 6;
    /**
     * Modem is enabled and powered on but not registered with a network provider and not available for data connections
     */
    public final static int CELLULAR_STATUS_FLAG_ENABLED = 7;
    /**
     * Modem is searching for a network provider to register
     */
    public final static int CELLULAR_STATUS_FLAG_SEARCHING = 8;
    /**
     * Modem is registered with a network provider, and data connections and messaging may be available for use
     */
    public final static int CELLULAR_STATUS_FLAG_REGISTERED = 9;
    /**
     * Modem is disconnecting and deactivating the last active packet data bearer. This state will not be entered if more than one packet data bearer is active and one of the active bearers is deactivated
     */
    public final static int CELLULAR_STATUS_FLAG_DISCONNECTING = 10;
    /**
     * Modem is activating and connecting the first packet data bearer. Subsequent bearer activations when another bearer is already active do not cause this state to be entered
     */
    public final static int CELLULAR_STATUS_FLAG_CONNECTING = 11;
    /**
     * One or more packet data bearers is active and connected
     */
    public final static int CELLULAR_STATUS_FLAG_CONNECTED = 12;
}
