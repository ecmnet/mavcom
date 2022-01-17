/**
 * Generated class : AIS_NAV_STATUS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface AIS_NAV_STATUS Navigational status of AIS vessel, enum duplicated
 * from AIS standard, https://gpsd.gitlab.io/gpsd/AIVDM.html
 **/
public interface AIS_NAV_STATUS {
	/**
	 * Under way using engine.
	 */
	public final static int UNDER_WAY = 0;
	/**
	 * null
	 */
	public final static int AIS_NAV_ANCHORED = 1;
	/**
	 * null
	 */
	public final static int AIS_NAV_UN_COMMANDED = 2;
	/**
	 * null
	 */
	public final static int AIS_NAV_RESTRICTED_MANOEUVERABILITY = 3;
	/**
	 * null
	 */
	public final static int AIS_NAV_DRAUGHT_CONSTRAINED = 4;
	/**
	 * null
	 */
	public final static int AIS_NAV_MOORED = 5;
	/**
	 * null
	 */
	public final static int AIS_NAV_AGROUND = 6;
	/**
	 * null
	 */
	public final static int AIS_NAV_FISHING = 7;
	/**
	 * null
	 */
	public final static int AIS_NAV_SAILING = 8;
	/**
	 * null
	 */
	public final static int AIS_NAV_RESERVED_HSC = 9;
	/**
	 * null
	 */
	public final static int AIS_NAV_RESERVED_WIG = 10;
	/**
	 * null
	 */
	public final static int AIS_NAV_RESERVED_1 = 11;
	/**
	 * null
	 */
	public final static int AIS_NAV_RESERVED_2 = 12;
	/**
	 * null
	 */
	public final static int AIS_NAV_RESERVED_3 = 13;
	/**
	 * Search And Rescue Transponder.
	 */
	public final static int AIS_NAV_AIS_SART = 14;
	/**
	 * Not available (default).
	 */
	public final static int AIS_NAV_UNKNOWN = 15;
}
