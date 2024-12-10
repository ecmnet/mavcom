/**
 * Generated class : ILLUMINATOR_ERROR_FLAGS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface ILLUMINATOR_ERROR_FLAGS
 * Illuminator module error flags (bitmap, 0 means no error)
 **/
public interface ILLUMINATOR_ERROR_FLAGS {
    /**
     * Illuminator thermal throttling error.
     */
    public final static int ILLUMINATOR_ERROR_FLAGS_THERMAL_THROTTLING = 1;
    /**
     * Illuminator over temperature shutdown error.
     */
    public final static int ILLUMINATOR_ERROR_FLAGS_OVER_TEMPERATURE_SHUTDOWN = 2;
    /**
     * Illuminator thermistor failure.
     */
    public final static int ILLUMINATOR_ERROR_FLAGS_THERMISTOR_FAILURE = 4;
}
