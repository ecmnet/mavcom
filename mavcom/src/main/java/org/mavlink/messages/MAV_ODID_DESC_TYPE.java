/**
 * Generated class : MAV_ODID_DESC_TYPE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_ODID_DESC_TYPE
 * 
 **/
public interface MAV_ODID_DESC_TYPE {
    /**
     * Optional free-form text description of the purpose of the flight.
     */
    public final static int MAV_ODID_DESC_TYPE_TEXT = 0;
    /**
     * Optional additional clarification when status == MAV_ODID_STATUS_EMERGENCY.
     */
    public final static int MAV_ODID_DESC_TYPE_EMERGENCY = 1;
    /**
     * Optional additional clarification when status != MAV_ODID_STATUS_EMERGENCY.
     */
    public final static int MAV_ODID_DESC_TYPE_EXTENDED_STATUS = 2;
}
