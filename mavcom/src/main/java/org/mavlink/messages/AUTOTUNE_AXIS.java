/**
 * Generated class : AUTOTUNE_AXIS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface AUTOTUNE_AXIS
 * Enable axes that will be tuned via autotuning. Used in MAV_CMD_DO_AUTOTUNE_ENABLE.
 **/
public interface AUTOTUNE_AXIS {
    /**
     * Flight stack tunes axis according to its default settings.
     */
    public final static int AUTOTUNE_AXIS_DEFAULT = 0;
    /**
     * Autotune roll axis.
     */
    public final static int AUTOTUNE_AXIS_ROLL = 1;
    /**
     * Autotune pitch axis.
     */
    public final static int AUTOTUNE_AXIS_PITCH = 2;
    /**
     * Autotune yaw axis.
     */
    public final static int AUTOTUNE_AXIS_YAW = 4;
}
