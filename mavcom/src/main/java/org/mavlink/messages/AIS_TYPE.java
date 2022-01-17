/**
 * Generated class : AIS_TYPE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;

/**
 * Interface AIS_TYPE Type of AIS vessel, enum duplicated from AIS standard,
 * https://gpsd.gitlab.io/gpsd/AIVDM.html
 **/
public interface AIS_TYPE {
	/**
	 * Not available (default).
	 */
	public final static int AIS_TYPE_UNKNOWN = 0;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_1 = 1;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_2 = 2;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_3 = 3;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_4 = 4;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_5 = 5;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_6 = 6;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_7 = 7;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_8 = 8;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_9 = 9;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_10 = 10;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_11 = 11;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_12 = 12;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_13 = 13;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_14 = 14;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_15 = 15;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_16 = 16;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_17 = 17;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_18 = 18;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_19 = 19;
	/**
	 * Wing In Ground effect.
	 */
	public final static int AIS_TYPE_WIG = 20;
	/**
	 * null
	 */
	public final static int AIS_TYPE_WIG_HAZARDOUS_A = 21;
	/**
	 * null
	 */
	public final static int AIS_TYPE_WIG_HAZARDOUS_B = 22;
	/**
	 * null
	 */
	public final static int AIS_TYPE_WIG_HAZARDOUS_C = 23;
	/**
	 * null
	 */
	public final static int AIS_TYPE_WIG_HAZARDOUS_D = 24;
	/**
	 * null
	 */
	public final static int AIS_TYPE_WIG_RESERVED_1 = 25;
	/**
	 * null
	 */
	public final static int AIS_TYPE_WIG_RESERVED_2 = 26;
	/**
	 * null
	 */
	public final static int AIS_TYPE_WIG_RESERVED_3 = 27;
	/**
	 * null
	 */
	public final static int AIS_TYPE_WIG_RESERVED_4 = 28;
	/**
	 * null
	 */
	public final static int AIS_TYPE_WIG_RESERVED_5 = 29;
	/**
	 * null
	 */
	public final static int AIS_TYPE_FISHING = 30;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TOWING = 31;
	/**
	 * Towing: length exceeds 200m or breadth exceeds 25m.
	 */
	public final static int AIS_TYPE_TOWING_LARGE = 32;
	/**
	 * Dredging or other underwater ops.
	 */
	public final static int AIS_TYPE_DREDGING = 33;
	/**
	 * null
	 */
	public final static int AIS_TYPE_DIVING = 34;
	/**
	 * null
	 */
	public final static int AIS_TYPE_MILITARY = 35;
	/**
	 * null
	 */
	public final static int AIS_TYPE_SAILING = 36;
	/**
	 * null
	 */
	public final static int AIS_TYPE_PLEASURE = 37;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_20 = 38;
	/**
	 * null
	 */
	public final static int AIS_TYPE_RESERVED_21 = 39;
	/**
	 * High Speed Craft.
	 */
	public final static int AIS_TYPE_HSC = 40;
	/**
	 * null
	 */
	public final static int AIS_TYPE_HSC_HAZARDOUS_A = 41;
	/**
	 * null
	 */
	public final static int AIS_TYPE_HSC_HAZARDOUS_B = 42;
	/**
	 * null
	 */
	public final static int AIS_TYPE_HSC_HAZARDOUS_C = 43;
	/**
	 * null
	 */
	public final static int AIS_TYPE_HSC_HAZARDOUS_D = 44;
	/**
	 * null
	 */
	public final static int AIS_TYPE_HSC_RESERVED_1 = 45;
	/**
	 * null
	 */
	public final static int AIS_TYPE_HSC_RESERVED_2 = 46;
	/**
	 * null
	 */
	public final static int AIS_TYPE_HSC_RESERVED_3 = 47;
	/**
	 * null
	 */
	public final static int AIS_TYPE_HSC_RESERVED_4 = 48;
	/**
	 * null
	 */
	public final static int AIS_TYPE_HSC_UNKNOWN = 49;
	/**
	 * null
	 */
	public final static int AIS_TYPE_PILOT = 50;
	/**
	 * Search And Rescue vessel.
	 */
	public final static int AIS_TYPE_SAR = 51;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TUG = 52;
	/**
	 * null
	 */
	public final static int AIS_TYPE_PORT_TENDER = 53;
	/**
	 * Anti-pollution equipment.
	 */
	public final static int AIS_TYPE_ANTI_POLLUTION = 54;
	/**
	 * null
	 */
	public final static int AIS_TYPE_LAW_ENFORCEMENT = 55;
	/**
	 * null
	 */
	public final static int AIS_TYPE_SPARE_LOCAL_1 = 56;
	/**
	 * null
	 */
	public final static int AIS_TYPE_SPARE_LOCAL_2 = 57;
	/**
	 * null
	 */
	public final static int AIS_TYPE_MEDICAL_TRANSPORT = 58;
	/**
	 * Noncombatant ship according to RR Resolution No. 18.
	 */
	public final static int AIS_TYPE_NONECOMBATANT = 59;
	/**
	 * null
	 */
	public final static int AIS_TYPE_PASSENGER = 60;
	/**
	 * null
	 */
	public final static int AIS_TYPE_PASSENGER_HAZARDOUS_A = 61;
	/**
	 * null
	 */
	public final static int AIS_TYPE_PASSENGER_HAZARDOUS_B = 62;
	/**
	 * null
	 */
	public final static int AIS_TYPE_AIS_TYPE_PASSENGER_HAZARDOUS_C = 63;
	/**
	 * null
	 */
	public final static int AIS_TYPE_PASSENGER_HAZARDOUS_D = 64;
	/**
	 * null
	 */
	public final static int AIS_TYPE_PASSENGER_RESERVED_1 = 65;
	/**
	 * null
	 */
	public final static int AIS_TYPE_PASSENGER_RESERVED_2 = 66;
	/**
	 * null
	 */
	public final static int AIS_TYPE_PASSENGER_RESERVED_3 = 67;
	/**
	 * null
	 */
	public final static int AIS_TYPE_AIS_TYPE_PASSENGER_RESERVED_4 = 68;
	/**
	 * null
	 */
	public final static int AIS_TYPE_PASSENGER_UNKNOWN = 69;
	/**
	 * null
	 */
	public final static int AIS_TYPE_CARGO = 70;
	/**
	 * null
	 */
	public final static int AIS_TYPE_CARGO_HAZARDOUS_A = 71;
	/**
	 * null
	 */
	public final static int AIS_TYPE_CARGO_HAZARDOUS_B = 72;
	/**
	 * null
	 */
	public final static int AIS_TYPE_CARGO_HAZARDOUS_C = 73;
	/**
	 * null
	 */
	public final static int AIS_TYPE_CARGO_HAZARDOUS_D = 74;
	/**
	 * null
	 */
	public final static int AIS_TYPE_CARGO_RESERVED_1 = 75;
	/**
	 * null
	 */
	public final static int AIS_TYPE_CARGO_RESERVED_2 = 76;
	/**
	 * null
	 */
	public final static int AIS_TYPE_CARGO_RESERVED_3 = 77;
	/**
	 * null
	 */
	public final static int AIS_TYPE_CARGO_RESERVED_4 = 78;
	/**
	 * null
	 */
	public final static int AIS_TYPE_CARGO_UNKNOWN = 79;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TANKER = 80;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TANKER_HAZARDOUS_A = 81;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TANKER_HAZARDOUS_B = 82;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TANKER_HAZARDOUS_C = 83;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TANKER_HAZARDOUS_D = 84;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TANKER_RESERVED_1 = 85;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TANKER_RESERVED_2 = 86;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TANKER_RESERVED_3 = 87;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TANKER_RESERVED_4 = 88;
	/**
	 * null
	 */
	public final static int AIS_TYPE_TANKER_UNKNOWN = 89;
	/**
	 * null
	 */
	public final static int AIS_TYPE_OTHER = 90;
	/**
	 * null
	 */
	public final static int AIS_TYPE_OTHER_HAZARDOUS_A = 91;
	/**
	 * null
	 */
	public final static int AIS_TYPE_OTHER_HAZARDOUS_B = 92;
	/**
	 * null
	 */
	public final static int AIS_TYPE_OTHER_HAZARDOUS_C = 93;
	/**
	 * null
	 */
	public final static int AIS_TYPE_OTHER_HAZARDOUS_D = 94;
	/**
	 * null
	 */
	public final static int AIS_TYPE_OTHER_RESERVED_1 = 95;
	/**
	 * null
	 */
	public final static int AIS_TYPE_OTHER_RESERVED_2 = 96;
	/**
	 * null
	 */
	public final static int AIS_TYPE_OTHER_RESERVED_3 = 97;
	/**
	 * null
	 */
	public final static int AIS_TYPE_OTHER_RESERVED_4 = 98;
	/**
	 * null
	 */
	public final static int AIS_TYPE_OTHER_UNKNOWN = 99;
}
