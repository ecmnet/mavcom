/**
 * Generated class : CELLULAR_NETWORK_FAILED_REASON
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface CELLULAR_NETWORK_FAILED_REASON
 * These flags are used to diagnose the failure state of CELLULAR_STATUS
 **/
public interface CELLULAR_NETWORK_FAILED_REASON {
    /**
     * No error
     */
    public final static int CELLULAR_NETWORK_FAILED_REASON_NONE = 0;
    /**
     * Error state is unknown
     */
    public final static int CELLULAR_NETWORK_FAILED_REASON_UNKNOWN = 1;
    /**
     * SIM is required for the modem but missing
     */
    public final static int CELLULAR_NETWORK_FAILED_REASON_SIM_MISSING = 2;
    /**
     * SIM is available, but not usable for connection
     */
    public final static int CELLULAR_NETWORK_FAILED_REASON_SIM_ERROR = 3;
}
