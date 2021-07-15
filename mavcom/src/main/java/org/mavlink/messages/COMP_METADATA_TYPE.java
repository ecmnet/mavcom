/**
 * Generated class : COMP_METADATA_TYPE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface COMP_METADATA_TYPE
 * Supported component metadata types. These are used in the "general" metadata file returned by COMPONENT_INFORMATION to provide information about supported metadata types. The types are not used directly in MAVLink messages.
 **/
public interface COMP_METADATA_TYPE {
    /**
     * General information about the component. General metadata includes information about other COMP_METADATA_TYPEs supported by the component. This type must be supported and must be downloadable from vehicle.
     */
    public final static int COMP_METADATA_TYPE_GENERAL = 0;
    /**
     * Parameter meta data.
     */
    public final static int COMP_METADATA_TYPE_PARAMETER = 1;
    /**
     * Meta data that specifies which commands and command parameters the vehicle supports. (WIP)
     */
    public final static int COMP_METADATA_TYPE_COMMANDS = 2;
    /**
     * Meta data that specifies external non-MAVLink peripherals.
     */
    public final static int COMP_METADATA_TYPE_PERIPHERALS = 3;
    /**
     * Meta data for the events interface.
     */
    public final static int COMP_METADATA_TYPE_EVENTS = 4;
}
