/**
 * Generated class : PARAM_TRANSACTION_ACTION
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface PARAM_TRANSACTION_ACTION Possible parameter transaction actions.
 **/
public interface PARAM_TRANSACTION_ACTION {
	/**
	 * Commit the current parameter transaction.
	 */
	public final static int PARAM_TRANSACTION_ACTION_START = 0;
	/**
	 * Commit the current parameter transaction.
	 */
	public final static int PARAM_TRANSACTION_ACTION_COMMIT = 1;
	/**
	 * Cancel the current parameter transaction.
	 */
	public final static int PARAM_TRANSACTION_ACTION_CANCEL = 2;
}
