/**
 * Generated class : PARAM_ACK
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface PARAM_ACK
 * Result from PARAM_EXT_SET message.
 **/
public interface PARAM_ACK {
    /**
     * Parameter value ACCEPTED and SET
     */
    public final static int PARAM_ACK_ACCEPTED = 0;
    /**
     * Parameter value UNKNOWN/UNSUPPORTED
     */
    public final static int PARAM_ACK_VALUE_UNSUPPORTED = 1;
    /**
     * Parameter failed to set
     */
    public final static int PARAM_ACK_FAILED = 2;
    /**
     * Parameter value received but not yet set/accepted. A subsequent PARAM_EXT_ACK with the final result will follow once operation is completed. This is returned immediately for parameters that take longer to set, indicating that the the parameter was received and does not need to be resent.
     */
    public final static int PARAM_ACK_IN_PROGRESS = 3;
}
