/**
 * Generated class : MAV_ODID_OPERATOR_LOCATION_TYPE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_ODID_OPERATOR_LOCATION_TYPE
 * 
 **/
public interface MAV_ODID_OPERATOR_LOCATION_TYPE {
    /**
     * The location of the operator is the same as the take-off location.
     */
    public final static int MAV_ODID_OPERATOR_LOCATION_TYPE_TAKEOFF = 0;
    /**
     * The location of the operator is based on live GNSS data.
     */
    public final static int MAV_ODID_OPERATOR_LOCATION_TYPE_LIVE_GNSS = 1;
    /**
     * The location of the operator is a fixed location.
     */
    public final static int MAV_ODID_OPERATOR_LOCATION_TYPE_FIXED = 2;
}
