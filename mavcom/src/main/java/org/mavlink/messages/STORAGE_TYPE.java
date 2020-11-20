/**
 * Generated class : STORAGE_TYPE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface STORAGE_TYPE
 * Flags to indicate the type of storage.
 **/
public interface STORAGE_TYPE {
    /**
     * Storage type is not known.
     */
    public final static int STORAGE_TYPE_UNKNOWN = 0;
    /**
     * Storage type is USB device.
     */
    public final static int STORAGE_TYPE_USB_STICK = 1;
    /**
     * Storage type is SD card.
     */
    public final static int STORAGE_TYPE_SD = 2;
    /**
     * Storage type is microSD card.
     */
    public final static int STORAGE_TYPE_MICROSD = 3;
    /**
     * Storage type is CFast.
     */
    public final static int STORAGE_TYPE_CF = 4;
    /**
     * Storage type is CFexpress.
     */
    public final static int STORAGE_TYPE_CFE = 5;
    /**
     * Storage type is XQD.
     */
    public final static int STORAGE_TYPE_XQD = 6;
    /**
     * Storage type is HD mass storage type.
     */
    public final static int STORAGE_TYPE_HD = 7;
    /**
     * Storage type is other, not listed type.
     */
    public final static int STORAGE_TYPE_OTHER = 254;
}
