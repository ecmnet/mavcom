/**
 * Generated class : MAV_FUEL_TYPE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_FUEL_TYPE
 * Fuel types for use in FUEL_TYPE. Fuel types specify the units for the maximum, available and consumed fuel, and for the flow rates.
 **/
public interface MAV_FUEL_TYPE {
    /**
     * Not specified. Fuel levels are normalized (i.e. maximum is 1, and other levels are relative to 1).
     */
    public final static int MAV_FUEL_TYPE_UNKNOWN = 0;
    /**
     * A generic liquid fuel. Fuel levels are in millilitres (ml). Fuel rates are in millilitres/second.
     */
    public final static int MAV_FUEL_TYPE_LIQUID = 1;
    /**
     * A gas tank. Fuel levels are in kilo-Pascal (kPa), and flow rates are in milliliters per second (ml/s).
     */
    public final static int MAV_FUEL_TYPE_GAS = 2;
}
