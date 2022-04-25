/**
 * Generated class : MAV_ODID_STATUS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_ODID_STATUS
 * 
 **/
public interface MAV_ODID_STATUS {
    /**
     * The status of the (UA) Unmanned Aircraft is undefined.
     */
    public final static int MAV_ODID_STATUS_UNDECLARED = 0;
    /**
     * The UA is on the ground.
     */
    public final static int MAV_ODID_STATUS_GROUND = 1;
    /**
     * The UA is in the air.
     */
    public final static int MAV_ODID_STATUS_AIRBORNE = 2;
    /**
     * The UA is having an emergency.
     */
    public final static int MAV_ODID_STATUS_EMERGENCY = 3;
    /**
     * The remote ID system is failing or unreliable in some way.
     */
    public final static int MAV_ODID_STATUS_REMOTE_ID_SYSTEM_FAILURE = 4;
}
