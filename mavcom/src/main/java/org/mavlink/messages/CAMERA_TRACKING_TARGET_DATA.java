/**
 * Generated class : CAMERA_TRACKING_TARGET_DATA
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface CAMERA_TRACKING_TARGET_DATA
 * Camera tracking target data (shows where tracked target is within image)
 **/
public interface CAMERA_TRACKING_TARGET_DATA {
    /**
     * No target data
     */
    public final static int CAMERA_TRACKING_TARGET_NONE = 0;
    /**
     * Target data embedded in image data (proprietary)
     */
    public final static int CAMERA_TRACKING_TARGET_EMBEDDED = 1;
    /**
     * Target data rendered in image
     */
    public final static int CAMERA_TRACKING_TARGET_RENDERED = 2;
    /**
     * Target data within status message (Point or Rectangle)
     */
    public final static int CAMERA_TRACKING_TARGET_IN_STATUS = 4;
}
