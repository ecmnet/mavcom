/**
 * Generated class : ILLUMINATOR_MODE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface ILLUMINATOR_MODE
 * Modes of illuminator
 **/
public interface ILLUMINATOR_MODE {
    /**
     * Illuminator mode is not specified/unknown
     */
    public final static int ILLUMINATOR_MODE_UNKNOWN = 0;
    /**
     * Illuminator behavior is controlled by MAV_CMD_DO_ILLUMINATOR_CONFIGURE settings
     */
    public final static int ILLUMINATOR_MODE_INTERNAL_CONTROL = 1;
    /**
     * Illuminator behavior is controlled by external factors: e.g. an external hardware signal
     */
    public final static int ILLUMINATOR_MODE_EXTERNAL_SYNC = 2;
}
