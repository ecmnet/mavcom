/**
 * Generated class : COMP_METADATA_TYPE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface COMP_METADATA_TYPE
 * Possible values for COMPONENT_INFORMATION.comp_metadata_type.
 **/
public interface COMP_METADATA_TYPE {
    /**
     * General information which also includes information on other optional supported COMP_METADATA_TYPE's. Must be supported. Only downloadable from vehicle.
     */
    public final static int COMP_METADATA_TYPE_GENERAL = 0;
    /**
     * Parameter meta data.
     */
    public final static int COMP_METADATA_TYPE_PARAMETER = 1;
    /**
     * Meta data which specifies the commands the vehicle supports. (WIP)
     */
    public final static int COMP_METADATA_TYPE_COMMANDS = 2;
    /**
     * Meta data which specifies potential external peripherals that do not talk MAVLink
     */
    public final static int COMP_METADATA_TYPE_PERIPHERALS = 3;
}
