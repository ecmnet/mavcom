/**
 * Generated class : NAV_VTOL_LAND_OPTIONS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface NAV_VTOL_LAND_OPTIONS
 * 
 **/
public interface NAV_VTOL_LAND_OPTIONS {
    /**
     * Default autopilot landing behaviour.
     */
    public final static int NAV_VTOL_LAND_OPTIONS_DEFAULT = 0;
    /**
     * Descend in fixed wing mode, transitioning to multicopter mode for vertical landing when close to the ground.
          The fixed wing descent pattern is at the discretion of the vehicle (e.g. transition altitude, loiter direction, radius, and speed, etc.).
     */
    public final static int NAV_VTOL_LAND_OPTIONS_FW_DESCENT = 1;
    /**
     * Land in multicopter mode on reaching the landing co-ordinates (the whole landing is by "hover descent").
     */
    public final static int NAV_VTOL_LAND_OPTIONS_HOVER_DESCENT = 2;
}
