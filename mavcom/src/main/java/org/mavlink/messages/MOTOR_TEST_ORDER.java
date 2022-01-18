/**
 * Generated class : MOTOR_TEST_ORDER
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MOTOR_TEST_ORDER
 * Sequence that motors are tested when using MAV_CMD_DO_MOTOR_TEST.
 **/
public interface MOTOR_TEST_ORDER {
    /**
     * Default autopilot motor test method.
     */
    public final static int MOTOR_TEST_ORDER_DEFAULT = 0;
    /**
     * Motor numbers are specified as their index in a predefined vehicle-specific sequence.
     */
    public final static int MOTOR_TEST_ORDER_SEQUENCE = 1;
    /**
     * Motor numbers are specified as the output as labeled on the board.
     */
    public final static int MOTOR_TEST_ORDER_BOARD = 2;
}
