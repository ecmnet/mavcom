/**
 * Generated class : CELLULAR_CONFIG_RESPONSE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface CELLULAR_CONFIG_RESPONSE Possible responses from a CELLULAR_CONFIG
 * message.
 **/
public interface CELLULAR_CONFIG_RESPONSE {
	/**
	 * Changes accepted.
	 */
	public final static int CELLULAR_CONFIG_RESPONSE_ACCEPTED = 0;
	/**
	 * Invalid APN.
	 */
	public final static int CELLULAR_CONFIG_RESPONSE_APN_ERROR = 1;
	/**
	 * Invalid PIN.
	 */
	public final static int CELLULAR_CONFIG_RESPONSE_PIN_ERROR = 2;
	/**
	 * Changes rejected.
	 */
	public final static int CELLULAR_CONFIG_RESPONSE_REJECTED = 3;
	/**
	 * PUK is required to unblock SIM card.
	 */
	public final static int CELLULAR_CONFIG_BLOCKED_PUK_REQUIRED = 4;
}
