/**
 * Generated class : PARAM_TRANSACTION_RESPONSE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface PARAM_TRANSACTION_RESPONSE
 * Possible responses from a PARAM_START_TRANSACTION and PARAM_COMMIT_TRANSACTION messages.
 **/
public interface PARAM_TRANSACTION_RESPONSE {
    /**
     * Transaction accepted.
     */
    public final static int PARAM_TRANSACTION_RESPONSE_ACCEPTED = 0;
    /**
     * Transaction failed.
     */
    public final static int PARAM_TRANSACTION_RESPONSE_FAILED = 1;
    /**
     * Transaction unsupported.
     */
    public final static int PARAM_TRANSACTION_RESPONSE_UNSUPPORTED = 2;
    /**
     * Transaction in progress.
     */
    public final static int PARAM_TRANSACTION_RESPONSE_INPROGRESS = 3;
}
