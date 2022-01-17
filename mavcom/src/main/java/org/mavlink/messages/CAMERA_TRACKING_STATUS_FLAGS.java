/**
 * Generated class : CAMERA_TRACKING_STATUS_FLAGS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface CAMERA_TRACKING_STATUS_FLAGS Camera tracking status flags
 **/
public interface CAMERA_TRACKING_STATUS_FLAGS {
	/**
	 * Camera is not tracking
	 */
	public final static int CAMERA_TRACKING_STATUS_FLAGS_IDLE = 0;
	/**
	 * Camera is tracking
	 */
	public final static int CAMERA_TRACKING_STATUS_FLAGS_ACTIVE = 1;
	/**
	 * Camera tracking in error state
	 */
	public final static int CAMERA_TRACKING_STATUS_FLAGS_ERROR = 2;
}
