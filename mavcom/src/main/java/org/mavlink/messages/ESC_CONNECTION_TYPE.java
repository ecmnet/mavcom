/**
 * Generated class : ESC_CONNECTION_TYPE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface ESC_CONNECTION_TYPE Indicates the ESC connection type.
 **/
public interface ESC_CONNECTION_TYPE {
	/**
	 * Traditional PPM ESC.
	 */
	public final static int ESC_CONNECTION_TYPE_PPM = 0;
	/**
	 * Serial Bus connected ESC.
	 */
	public final static int ESC_CONNECTION_TYPE_SERIAL = 1;
	/**
	 * One Shot PPM ESC.
	 */
	public final static int ESC_CONNECTION_TYPE_ONESHOT = 2;
	/**
	 * I2C ESC.
	 */
	public final static int ESC_CONNECTION_TYPE_I2C = 3;
	/**
	 * CAN-Bus ESC.
	 */
	public final static int ESC_CONNECTION_TYPE_CAN = 4;
	/**
	 * DShot ESC.
	 */
	public final static int ESC_CONNECTION_TYPE_DSHOT = 5;
}
