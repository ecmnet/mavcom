/**
 * Generated class : STORAGE_USAGE_FLAG
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface STORAGE_USAGE_FLAG
 * Flags to indicate usage for a particular storage (see `STORAGE_INFORMATION.storage_usage` and `MAV_CMD_SET_STORAGE_USAGE`).
 **/
public interface STORAGE_USAGE_FLAG {
    /**
     * Always set to 1 (indicates `STORAGE_INFORMATION.storage_usage` is supported).
     */
    public final static int STORAGE_USAGE_FLAG_SET = 1;
    /**
     * Storage for saving photos.
     */
    public final static int STORAGE_USAGE_FLAG_PHOTO = 2;
    /**
     * Storage for saving videos.
     */
    public final static int STORAGE_USAGE_FLAG_VIDEO = 4;
    /**
     * Storage for saving logs.
     */
    public final static int STORAGE_USAGE_FLAG_LOGS = 8;
}
