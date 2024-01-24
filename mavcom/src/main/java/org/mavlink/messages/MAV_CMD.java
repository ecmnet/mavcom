/**
 * Generated class : MAV_CMD
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_CMD
 * Commands to be executed by the MAV. They can be executed on user request, or as part of a mission script. If the action is used in a mission, the parameter mapping to the waypoint/mission message is as follows: Param 1, Param 2, Param 3, Param 4, X: Param 5, Y:Param 6, Z:Param 7. This command list is similar what ARINC 424 is for commercial aircraft: A data format how to interpret waypoint/mission data. NaN and INT32_MAX may be used in float/integer params (respectively) to indicate optional/default values (e.g. to use the component's current yaw or latitude rather than a specific value). See https://mavlink.io/en/guide/xml_schema.html#MAV_CMD for information about the structure of the MAV_CMD entries
 **/
public interface MAV_CMD {
    /**
     * Navigate to waypoint. This is intended for use in missions (for guided commands outside of missions use MAV_CMD_DO_REPOSITION).
     * PARAM 1 : Hold time. (ignored by fixed wing, time to stay at waypoint for rotary wing)
     * PARAM 2 : Acceptance radius (if the sphere with this radius is hit, the waypoint counts as reached)
     * PARAM 3 : 0 to pass through the WP, if > 0 radius to pass by WP. Positive value for clockwise orbit, negative value for counter-clockwise orbit. Allows trajectory control.
     * PARAM 4 : Desired yaw angle at waypoint (rotary wing). NaN to use the current system yaw heading mode (e.g. yaw towards next waypoint, yaw to home, etc.).
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_NAV_WAYPOINT = 16;
    /**
     * Loiter around this waypoint an unlimited amount of time
     * PARAM 1 : Empty
     * PARAM 2 : Empty
     * PARAM 3 : Loiter radius around waypoint for forward-only moving vehicles (not multicopters). If positive loiter clockwise, else counter-clockwise
     * PARAM 4 : Desired yaw angle. NaN to use the current system yaw heading mode (e.g. yaw towards next waypoint, yaw to home, etc.).
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_NAV_LOITER_UNLIM = 17;
    /**
     * Loiter around this waypoint for X turns
     * PARAM 1 : Number of turns.
     * PARAM 2 : Leave loiter circle only once heading towards the next waypoint (0 = False)
     * PARAM 3 : Loiter radius around waypoint for forward-only moving vehicles (not multicopters). If positive loiter clockwise, else counter-clockwise
     * PARAM 4 : Loiter circle exit location and/or path to next waypoint ("xtrack") for forward-only moving vehicles (not multicopters). 0 for the vehicle to converge towards the center xtrack when it leaves the loiter (the line between the centers of the current and next waypoint), 1 to converge to the direct line between the location that the vehicle exits the loiter radius and the next waypoint. Otherwise the angle (in degrees) between the tangent of the loiter circle and the center xtrack at which the vehicle must leave the loiter (and converge to the center xtrack). NaN to use the current system default xtrack behaviour.
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_NAV_LOITER_TURNS = 18;
    /**
     * Loiter at the specified latitude, longitude and altitude for a certain amount of time. Multicopter vehicles stop at the point (within a vehicle-specific acceptance radius). Forward-only moving vehicles (e.g. fixed-wing) circle the point with the specified radius/direction. If the Heading Required parameter (2) is non-zero forward moving aircraft will only leave the loiter circle once heading towards the next waypoint.
     * PARAM 1 : Loiter time (only starts once Lat, Lon and Alt is reached).
     * PARAM 2 : Leave loiter circle only once heading towards the next waypoint (0 = False)
     * PARAM 3 : Loiter radius around waypoint for forward-only moving vehicles (not multicopters). If positive loiter clockwise, else counter-clockwise.
     * PARAM 4 : Loiter circle exit location and/or path to next waypoint ("xtrack") for forward-only moving vehicles (not multicopters). 0 for the vehicle to converge towards the center xtrack when it leaves the loiter (the line between the centers of the current and next waypoint), 1 to converge to the direct line between the location that the vehicle exits the loiter radius and the next waypoint. Otherwise the angle (in degrees) between the tangent of the loiter circle and the center xtrack at which the vehicle must leave the loiter (and converge to the center xtrack). NaN to use the current system default xtrack behaviour.
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_NAV_LOITER_TIME = 19;
    /**
     * Return to launch location
     * PARAM 1 : Empty
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_NAV_RETURN_TO_LAUNCH = 20;
    /**
     * Land at location.
     * PARAM 1 : Minimum target altitude if landing is aborted (0 = undefined/use system default).
     * PARAM 2 : Precision land mode.
     * PARAM 3 : Empty.
     * PARAM 4 : Desired yaw angle. NaN to use the current system yaw heading mode (e.g. yaw towards next waypoint, yaw to home, etc.).
     * PARAM 5 : Latitude.
     * PARAM 6 : Longitude.
     * PARAM 7 : Landing altitude (ground level in current frame).
     */
    public final static int MAV_CMD_NAV_LAND = 21;
    /**
     * Takeoff from ground / hand. Vehicles that support multiple takeoff modes (e.g. VTOL quadplane) should take off using the currently configured mode.
     * PARAM 1 : Minimum pitch (if airspeed sensor present), desired pitch without sensor
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Yaw angle (if magnetometer present), ignored without magnetometer. NaN to use the current system yaw heading mode (e.g. yaw towards next waypoint, yaw to home, etc.).
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_NAV_TAKEOFF = 22;
    /**
     * Land at local position (local frame only)
     * PARAM 1 : Landing target number (if available)
     * PARAM 2 : Maximum accepted offset from desired landing position - computed magnitude from spherical coordinates: d = sqrt(x^2 + y^2 + z^2), which gives the maximum accepted distance between the desired landing position and the position where the vehicle is about to land
     * PARAM 3 : Landing descend rate
     * PARAM 4 : Desired yaw angle
     * PARAM 5 : Y-axis position
     * PARAM 6 : X-axis position
     * PARAM 7 : Z-axis / ground level position
     */
    public final static int MAV_CMD_NAV_LAND_LOCAL = 23;
    /**
     * Takeoff from local position (local frame only)
     * PARAM 1 : Minimum pitch (if airspeed sensor present), desired pitch without sensor
     * PARAM 2 : Empty
     * PARAM 3 : Takeoff ascend rate
     * PARAM 4 : Yaw angle (if magnetometer or another yaw estimation source present), ignored without one of these
     * PARAM 5 : Y-axis position
     * PARAM 6 : X-axis position
     * PARAM 7 : Z-axis position
     */
    public final static int MAV_CMD_NAV_TAKEOFF_LOCAL = 24;
    /**
     * Vehicle following, i.e. this waypoint represents the position of a moving vehicle
     * PARAM 1 : Following logic to use (e.g. loitering or sinusoidal following) - depends on specific autopilot implementation
     * PARAM 2 : Ground speed of vehicle to be followed
     * PARAM 3 : Radius around waypoint. If positive loiter clockwise, else counter-clockwise
     * PARAM 4 : Desired yaw angle.
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_NAV_FOLLOW = 25;
    /**
     * Continue on the current course and climb/descend to specified altitude.  When the altitude is reached continue to the next command (i.e., don't proceed to the next command until the desired altitude is reached.
     * PARAM 1 : Climb or Descend (0 = Neutral, command completes when within 5m of this command's altitude, 1 = Climbing, command completes when at or above this command's altitude, 2 = Descending, command completes when at or below this command's altitude.
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Desired altitude
     */
    public final static int MAV_CMD_NAV_CONTINUE_AND_CHANGE_ALT = 30;
    /**
     * Begin loiter at the specified Latitude and Longitude.  If Lat=Lon=0, then loiter at the current position.  Don't consider the navigation command complete (don't leave loiter) until the altitude has been reached. Additionally, if the Heading Required parameter is non-zero the aircraft will not leave the loiter until heading toward the next waypoint.
     * PARAM 1 : Leave loiter circle only once heading towards the next waypoint (0 = False)
     * PARAM 2 : Loiter radius around waypoint for forward-only moving vehicles (not multicopters). If positive loiter clockwise, negative counter-clockwise, 0 means no change to standard loiter.
     * PARAM 3 : Empty
     * PARAM 4 : Loiter circle exit location and/or path to next waypoint ("xtrack") for forward-only moving vehicles (not multicopters). 0 for the vehicle to converge towards the center xtrack when it leaves the loiter (the line between the centers of the current and next waypoint), 1 to converge to the direct line between the location that the vehicle exits the loiter radius and the next waypoint. Otherwise the angle (in degrees) between the tangent of the loiter circle and the center xtrack at which the vehicle must leave the loiter (and converge to the center xtrack). NaN to use the current system default xtrack behaviour.
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_NAV_LOITER_TO_ALT = 31;
    /**
     * Begin following a target
     * PARAM 1 : System ID (of the FOLLOW_TARGET beacon). Send 0 to disable follow-me and return to the default position hold mode.
     * PARAM 2 : Reserved
     * PARAM 3 : Reserved
     * PARAM 4 : Altitude mode: 0: Keep current altitude, 1: keep altitude difference to target, 2: go to a fixed altitude above home.
     * PARAM 5 : Altitude above home. (used if mode=2)
     * PARAM 6 : Reserved
     * PARAM 7 : Time to land in which the MAV should go to the default position hold mode after a message RX timeout.
     */
    public final static int MAV_CMD_DO_FOLLOW = 32;
    /**
     * Reposition the MAV after a follow target command has been sent
     * PARAM 1 : Camera q1 (where 0 is on the ray from the camera to the tracking device)
     * PARAM 2 : Camera q2
     * PARAM 3 : Camera q3
     * PARAM 4 : Camera q4
     * PARAM 5 : altitude offset from target
     * PARAM 6 : X offset from target
     * PARAM 7 : Y offset from target
     */
    public final static int MAV_CMD_DO_FOLLOW_REPOSITION = 33;
    /**
     * Start orbiting on the circumference of a circle defined by the parameters. Setting values to NaN/INT32_MAX (as appropriate) results in using defaults.
     * PARAM 1 : Radius of the circle. Positive: orbit clockwise. Negative: orbit counter-clockwise. NaN: Use vehicle default radius, or current radius if already orbiting.
     * PARAM 2 : Tangential Velocity. NaN: Use vehicle default velocity, or current velocity if already orbiting.
     * PARAM 3 : Yaw behavior of the vehicle.
     * PARAM 4 : Orbit around the centre point for this many radians (i.e. for a three-quarter orbit set 270*Pi/180). 0: Orbit forever. NaN: Use vehicle default, or current value if already orbiting.
     * PARAM 5 : Center point latitude (if no MAV_FRAME specified) / X coordinate according to MAV_FRAME. INT32_MAX (or NaN if sent in COMMAND_LONG): Use current vehicle position, or current center if already orbiting.
     * PARAM 6 : Center point longitude (if no MAV_FRAME specified) / Y coordinate according to MAV_FRAME. INT32_MAX (or NaN if sent in COMMAND_LONG): Use current vehicle position, or current center if already orbiting.
     * PARAM 7 : Center point altitude (MSL) (if no MAV_FRAME specified) / Z coordinate according to MAV_FRAME. NaN: Use current vehicle altitude.
     */
    public final static int MAV_CMD_DO_ORBIT = 34;
    /**
     * Sets the region of interest (ROI) for a sensor set or the vehicle itself. This can then be used by the vehicle's control system to control the vehicle attitude and the attitude of various sensors such as cameras.
     * PARAM 1 : Region of interest mode.
     * PARAM 2 : Waypoint index/ target ID. (see MAV_ROI enum)
     * PARAM 3 : ROI index (allows a vehicle to manage multiple ROI's)
     * PARAM 4 : Empty
     * PARAM 5 : x the location of the fixed ROI (see MAV_FRAME)
     * PARAM 6 : y
     * PARAM 7 : z
     */
    public final static int MAV_CMD_NAV_ROI = 80;
    /**
     * Control autonomous path planning on the MAV.
     * PARAM 1 : 0: Disable local obstacle avoidance / local path planning (without resetting map), 1: Enable local path planning, 2: Enable and reset local path planning
     * PARAM 2 : 0: Disable full path planning (without resetting map), 1: Enable, 2: Enable and reset map/occupancy grid, 3: Enable and reset planned route, but not occupancy grid
     * PARAM 3 : Empty
     * PARAM 4 : Yaw angle at goal
     * PARAM 5 : Latitude/X of goal
     * PARAM 6 : Longitude/Y of goal
     * PARAM 7 : Altitude/Z of goal
     */
    public final static int MAV_CMD_NAV_PATHPLANNING = 81;
    /**
     * Navigate to waypoint using a spline path.
     * PARAM 1 : Hold time. (ignored by fixed wing, time to stay at waypoint for rotary wing)
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Latitude/X of goal
     * PARAM 6 : Longitude/Y of goal
     * PARAM 7 : Altitude/Z of goal
     */
    public final static int MAV_CMD_NAV_SPLINE_WAYPOINT = 82;
    /**
     * Takeoff from ground using VTOL mode, and transition to forward flight with specified heading. The command should be ignored by vehicles that dont support both VTOL and fixed-wing flight (multicopters, boats,etc.).
     * PARAM 1 : Empty
     * PARAM 2 : Front transition heading.
     * PARAM 3 : Empty
     * PARAM 4 : Yaw angle. NaN to use the current system yaw heading mode (e.g. yaw towards next waypoint, yaw to home, etc.).
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_NAV_VTOL_TAKEOFF = 84;
    /**
     * Land using VTOL mode
     * PARAM 1 : Landing behaviour.
     * PARAM 2 : Empty
     * PARAM 3 : Approach altitude (with the same reference as the Altitude field). NaN if unspecified.
     * PARAM 4 : Yaw angle. NaN to use the current system yaw heading mode (e.g. yaw towards next waypoint, yaw to home, etc.).
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude (ground level) relative to the current coordinate frame. NaN to use system default landing altitude (ignore value).
     */
    public final static int MAV_CMD_NAV_VTOL_LAND = 85;
    /**
     * hand control over to an external controller
     * PARAM 1 : On / Off (> 0.5f on)
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_NAV_GUIDED_ENABLE = 92;
    /**
     * Delay the next navigation command a number of seconds or until a specified time
     * PARAM 1 : Delay (-1 to enable time-of-day fields)
     * PARAM 2 : hour (24h format, UTC, -1 to ignore)
     * PARAM 3 : minute (24h format, UTC, -1 to ignore)
     * PARAM 4 : second (24h format, UTC, -1 to ignore)
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_NAV_DELAY = 93;
    /**
     * Descend and place payload. Vehicle moves to specified location, descends until it detects a hanging payload has reached the ground, and then releases the payload. If ground is not detected before the reaching the maximum descent value (param1), the command will complete without releasing the payload.
     * PARAM 1 : Maximum distance to descend.
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_NAV_PAYLOAD_PLACE = 94;
    /**
     * NOP - This command is only used to mark the upper limit of the NAV/ACTION commands in the enumeration
     * PARAM 1 : Empty
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_NAV_LAST = 95;
    /**
     * Delay mission state machine.
     * PARAM 1 : Delay
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_CONDITION_DELAY = 112;
    /**
     * Ascend/descend to target altitude at specified rate. Delay mission state machine until desired altitude reached.
     * PARAM 1 : Descent / Ascend rate.
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Target Altitude
     */
    public final static int MAV_CMD_CONDITION_CHANGE_ALT = 113;
    /**
     * Delay mission state machine until within desired distance of next NAV point.
     * PARAM 1 : Distance.
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_CONDITION_DISTANCE = 114;
    /**
     * Reach a certain target angle.
     * PARAM 1 : target angle [0-360]. Absolute angles: 0 is north. Relative angle: 0 is initial yaw. Direction set by param3.
     * PARAM 2 : angular speed
     * PARAM 3 : direction: -1: counter clockwise, 0: shortest direction, 1: clockwise
     * PARAM 4 : 0: absolute angle, 1: relative offset
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_CONDITION_YAW = 115;
    /**
     * NOP - This command is only used to mark the upper limit of the CONDITION commands in the enumeration
     * PARAM 1 : Empty
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_CONDITION_LAST = 159;
    /**
     * Set system mode.
     * PARAM 1 : Mode
     * PARAM 2 : Custom mode - this is system specific, please refer to the individual autopilot specifications for details.
     * PARAM 3 : Custom sub mode - this is system specific, please refer to the individual autopilot specifications for details.
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_SET_MODE = 176;
    /**
     * Jump to the desired command in the mission list.  Repeat this action only the specified number of times
     * PARAM 1 : Sequence number
     * PARAM 2 : Repeat count
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_JUMP = 177;
    /**
     * Change speed and/or throttle set points. The value persists until it is overridden or there is a mode change
     * PARAM 1 : Speed type of value set in param2 (such as airspeed, ground speed, and so on)
     * PARAM 2 : Speed (-1 indicates no change, -2 indicates return to default vehicle speed)
     * PARAM 3 : Throttle (-1 indicates no change, -2 indicates return to default vehicle throttle value)
     * PARAM 4 : 
     * PARAM 5 : 
     * PARAM 6 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_DO_CHANGE_SPEED = 178;
    /**
     * Sets the home position to either to the current position or a specified position.
          The home position is the default position that the system will return to and land on.
          The position is set automatically by the system during the takeoff (and may also be set using this command).
          Note: the current home position may be emitted in a HOME_POSITION message on request (using MAV_CMD_REQUEST_MESSAGE with param1=242).
     * PARAM 1 : Use current (1=use current location, 0=use specified location)
     * PARAM 2 : Roll angle (of surface). Range: -180..180 degrees. NAN or 0 means value not set. 0.01 indicates zero roll.
     * PARAM 3 : Pitch angle (of surface). Range: -90..90 degrees. NAN or 0 means value not set. 0.01 means zero pitch.
     * PARAM 4 : Yaw angle. NaN to use default heading. Range: -180..180 degrees.
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_DO_SET_HOME = 179;
    /**
     * Set a system parameter.  Caution!  Use of this command requires knowledge of the numeric enumeration value of the parameter.
     * PARAM 1 : Parameter number
     * PARAM 2 : Parameter value
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_SET_PARAMETER = 180;
    /**
     * Set a relay to a condition.
     * PARAM 1 : Relay instance number.
     * PARAM 2 : Setting. (1=on, 0=off, others possible depending on system hardware)
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_SET_RELAY = 181;
    /**
     * Cycle a relay on and off for a desired number of cycles with a desired period.
     * PARAM 1 : Relay instance number.
     * PARAM 2 : Cycle count.
     * PARAM 3 : Cycle time.
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_REPEAT_RELAY = 182;
    /**
     * Set a servo to a desired PWM value.
     * PARAM 1 : Servo instance number.
     * PARAM 2 : Pulse Width Modulation.
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_SET_SERVO = 183;
    /**
     * Cycle a between its nominal setting and a desired PWM for a desired number of cycles with a desired period.
     * PARAM 1 : Servo instance number.
     * PARAM 2 : Pulse Width Modulation.
     * PARAM 3 : Cycle count.
     * PARAM 4 : Cycle time.
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_REPEAT_SERVO = 184;
    /**
     * Terminate flight immediately.
          Flight termination immediately and irreversibly terminates the current flight, returning the vehicle to ground.
          The vehicle will ignore RC or other input until it has been power-cycled.
          Termination may trigger safety measures, including: disabling motors and deployment of parachute on multicopters, and setting flight surfaces to initiate a landing pattern on fixed-wing).
          On multicopters without a parachute it may trigger a crash landing.
          Support for this command can be tested using the protocol bit: MAV_PROTOCOL_CAPABILITY_FLIGHT_TERMINATION.
          Support for this command can also be tested by sending the command with param1=0 (< 0.5); the ACK should be either MAV_RESULT_FAILED or MAV_RESULT_UNSUPPORTED.
     * PARAM 1 : Flight termination activated if > 0.5. Otherwise not activated and ACK with MAV_RESULT_FAILED.
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_FLIGHTTERMINATION = 185;
    /**
     * Change altitude set point.
     * PARAM 1 : Altitude.
     * PARAM 2 : Frame of new altitude.
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_CHANGE_ALTITUDE = 186;
    /**
     * Sets actuators (e.g. servos) to a desired value. The actuator numbers are mapped to specific outputs (e.g. on any MAIN or AUX PWM or UAVCAN) using a flight-stack specific mechanism (i.e. a parameter).
     * PARAM 1 : Actuator 1 value, scaled from [-1 to 1]. NaN to ignore.
     * PARAM 2 : Actuator 2 value, scaled from [-1 to 1]. NaN to ignore.
     * PARAM 3 : Actuator 3 value, scaled from [-1 to 1]. NaN to ignore.
     * PARAM 4 : Actuator 4 value, scaled from [-1 to 1]. NaN to ignore.
     * PARAM 5 : Actuator 5 value, scaled from [-1 to 1]. NaN to ignore.
     * PARAM 6 : Actuator 6 value, scaled from [-1 to 1]. NaN to ignore.
     * PARAM 7 : Index of actuator set (i.e if set to 1, Actuator 1 becomes Actuator 7)
     */
    public final static int MAV_CMD_DO_SET_ACTUATOR = 187;
    /**
     * Mission command to perform a landing. This is used as a marker in a mission to tell the autopilot where a sequence of mission items that represents a landing starts.
	  It may also be sent via a COMMAND_LONG to trigger a landing, in which case the nearest (geographically) landing sequence in the mission will be used.
	  The Latitude/Longitude/Altitude is optional, and may be set to 0 if not needed. If specified then it will be used to help find the closest landing sequence.
     * PARAM 1 : Empty
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_DO_LAND_START = 189;
    /**
     * Mission command to perform a landing from a rally point.
     * PARAM 1 : Break altitude
     * PARAM 2 : Landing speed
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_RALLY_LAND = 190;
    /**
     * Mission command to safely abort an autonomous landing.
     * PARAM 1 : Altitude
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_GO_AROUND = 191;
    /**
     * Reposition the vehicle to a specific WGS84 global position. This command is intended for guided commands (for missions use MAV_CMD_NAV_WAYPOINT instead).
     * PARAM 1 : Ground speed, less than 0 (-1) for default
     * PARAM 2 : Bitmask of option flags.
     * PARAM 3 : Loiter radius for planes. Positive values only, direction is controlled by Yaw value. A value of zero or NaN is ignored. 
     * PARAM 4 : Yaw heading. NaN to use the current system yaw heading mode (e.g. yaw towards next waypoint, yaw to home, etc.). For planes indicates loiter direction (0: clockwise, 1: counter clockwise)
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_DO_REPOSITION = 192;
    /**
     * If in a GPS controlled position mode, hold the current position or continue.
     * PARAM 1 : 0: Pause current mission or reposition command, hold current position. 1: Continue mission. A VTOL capable vehicle should enter hover mode (multicopter and VTOL planes). A plane should loiter with the default loiter radius.
     * PARAM 2 : Reserved
     * PARAM 3 : Reserved
     * PARAM 4 : Reserved
     * PARAM 5 : Reserved
     * PARAM 6 : Reserved
     * PARAM 7 : Reserved
     */
    public final static int MAV_CMD_DO_PAUSE_CONTINUE = 193;
    /**
     * Set moving direction to forward or reverse.
     * PARAM 1 : Direction (0=Forward, 1=Reverse)
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_SET_REVERSE = 194;
    /**
     * Sets the region of interest (ROI) to a location. This can then be used by the vehicle's control system to control the vehicle attitude and the attitude of various sensors such as cameras. This command can be sent to a gimbal manager but not to a gimbal device. A gimbal is not to react to this message.
     * PARAM 1 : Component ID of gimbal device to address (or 1-6 for non-MAVLink gimbal), 0 for all gimbal device components. Send command multiple times for more than one gimbal (but not all gimbals).
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Latitude of ROI location
     * PARAM 6 : Longitude of ROI location
     * PARAM 7 : Altitude of ROI location
     */
    public final static int MAV_CMD_DO_SET_ROI_LOCATION = 195;
    /**
     * Sets the region of interest (ROI) to be toward next waypoint, with optional pitch/roll/yaw offset. This can then be used by the vehicle's control system to control the vehicle attitude and the attitude of various sensors such as cameras. This command can be sent to a gimbal manager but not to a gimbal device. A gimbal device is not to react to this message.
     * PARAM 1 : Component ID of gimbal device to address (or 1-6 for non-MAVLink gimbal), 0 for all gimbal device components. Send command multiple times for more than one gimbal (but not all gimbals).
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Pitch offset from next waypoint, positive pitching up
     * PARAM 6 : Roll offset from next waypoint, positive rolling to the right
     * PARAM 7 : Yaw offset from next waypoint, positive yawing to the right
     */
    public final static int MAV_CMD_DO_SET_ROI_WPNEXT_OFFSET = 196;
    /**
     * Cancels any previous ROI command returning the vehicle/sensors to default flight characteristics. This can then be used by the vehicle's control system to control the vehicle attitude and the attitude of various sensors such as cameras. This command can be sent to a gimbal manager but not to a gimbal device. A gimbal device is not to react to this message. After this command the gimbal manager should go back to manual input if available, and otherwise assume a neutral position.
     * PARAM 1 : Component ID of gimbal device to address (or 1-6 for non-MAVLink gimbal), 0 for all gimbal device components. Send command multiple times for more than one gimbal (but not all gimbals).
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_SET_ROI_NONE = 197;
    /**
     * Mount tracks system with specified system ID. Determination of target vehicle position may be done with GLOBAL_POSITION_INT or any other means. This command can be sent to a gimbal manager but not to a gimbal device. A gimbal device is not to react to this message.
     * PARAM 1 : System ID
     * PARAM 2 : Component ID of gimbal device to address (or 1-6 for non-MAVLink gimbal), 0 for all gimbal device components. Send command multiple times for more than one gimbal (but not all gimbals).
     */
    public final static int MAV_CMD_DO_SET_ROI_SYSID = 198;
    /**
     * Control onboard camera system.
     * PARAM 1 : Camera ID (-1 for all)
     * PARAM 2 : Transmission: 0: disabled, 1: enabled compressed, 2: enabled raw
     * PARAM 3 : Transmission mode: 0: video stream, >0: single images every n seconds
     * PARAM 4 : Recording: 0: disabled, 1: enabled compressed, 2: enabled raw
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_CONTROL_VIDEO = 200;
    /**
     * Sets the region of interest (ROI) for a sensor set or the vehicle itself. This can then be used by the vehicle's control system to control the vehicle attitude and the attitude of various sensors such as cameras.
     * PARAM 1 : Region of interest mode.
     * PARAM 2 : Waypoint index/ target ID (depends on param 1).
     * PARAM 3 : Region of interest index. (allows a vehicle to manage multiple ROI's)
     * PARAM 4 : Empty
     * PARAM 5 : MAV_ROI_WPNEXT: pitch offset from next waypoint, MAV_ROI_LOCATION: latitude
     * PARAM 6 : MAV_ROI_WPNEXT: roll offset from next waypoint, MAV_ROI_LOCATION: longitude
     * PARAM 7 : MAV_ROI_WPNEXT: yaw offset from next waypoint, MAV_ROI_LOCATION: altitude
     */
    public final static int MAV_CMD_DO_SET_ROI = 201;
    /**
     * Configure digital camera. This is a fallback message for systems that have not yet implemented PARAM_EXT_XXX messages and camera definition files (see https://mavlink.io/en/services/camera_def.html ).
     * PARAM 1 : Modes: P, TV, AV, M, Etc.
     * PARAM 2 : Shutter speed: Divisor number for one second.
     * PARAM 3 : Aperture: F stop number.
     * PARAM 4 : ISO number e.g. 80, 100, 200, Etc.
     * PARAM 5 : Exposure type enumerator.
     * PARAM 6 : Command Identity.
     * PARAM 7 : Main engine cut-off time before camera trigger. (0 means no cut-off)
     */
    public final static int MAV_CMD_DO_DIGICAM_CONFIGURE = 202;
    /**
     * Control digital camera. This is a fallback message for systems that have not yet implemented PARAM_EXT_XXX messages and camera definition files (see https://mavlink.io/en/services/camera_def.html ).
     * PARAM 1 : Session control e.g. show/hide lens
     * PARAM 2 : Zoom's absolute position
     * PARAM 3 : Zooming step value to offset zoom from the current position
     * PARAM 4 : Focus Locking, Unlocking or Re-locking
     * PARAM 5 : Shooting Command
     * PARAM 6 : Command Identity
     * PARAM 7 : Test shot identifier. If set to 1, image will only be captured, but not counted towards internal frame count.
     */
    public final static int MAV_CMD_DO_DIGICAM_CONTROL = 203;
    /**
     * Mission command to configure a camera or antenna mount
     * PARAM 1 : Mount operation mode
     * PARAM 2 : stabilize roll? (1 = yes, 0 = no)
     * PARAM 3 : stabilize pitch? (1 = yes, 0 = no)
     * PARAM 4 : stabilize yaw? (1 = yes, 0 = no)
     * PARAM 5 : roll input (0 = angle body frame, 1 = angular rate, 2 = angle absolute frame)
     * PARAM 6 : pitch input (0 = angle body frame, 1 = angular rate, 2 = angle absolute frame)
     * PARAM 7 : yaw input (0 = angle body frame, 1 = angular rate, 2 = angle absolute frame)
     */
    public final static int MAV_CMD_DO_MOUNT_CONFIGURE = 204;
    /**
     * Mission command to control a camera or antenna mount
     * PARAM 1 : pitch depending on mount mode (degrees or degrees/second depending on pitch input).
     * PARAM 2 : roll depending on mount mode (degrees or degrees/second depending on roll input).
     * PARAM 3 : yaw depending on mount mode (degrees or degrees/second depending on yaw input).
     * PARAM 4 : altitude depending on mount mode.
     * PARAM 5 : latitude, set if appropriate mount mode.
     * PARAM 6 : longitude, set if appropriate mount mode.
     * PARAM 7 : Mount mode.
     */
    public final static int MAV_CMD_DO_MOUNT_CONTROL = 205;
    /**
     * Mission command to set camera trigger distance for this flight. The camera is triggered each time this distance is exceeded. This command can also be used to set the shutter integration time for the camera.
     * PARAM 1 : Camera trigger distance. 0 to stop triggering.
     * PARAM 2 : Camera shutter integration time. -1 or 0 to ignore
     * PARAM 3 : Trigger camera once immediately. (0 = no trigger, 1 = trigger)
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_SET_CAM_TRIGG_DIST = 206;
    /**
     * Mission command to enable the geofence
     * PARAM 1 : enable? (0=disable, 1=enable, 2=disable_floor_only)
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_FENCE_ENABLE = 207;
    /**
     * Mission item/command to release a parachute or enable/disable auto release.
     * PARAM 1 : Action
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_PARACHUTE = 208;
    /**
     * Command to perform motor test.
     * PARAM 1 : Motor instance number (from 1 to max number of motors on the vehicle).
     * PARAM 2 : Throttle type (whether the Throttle Value in param3 is a percentage, PWM value, etc.)
     * PARAM 3 : Throttle value.
     * PARAM 4 : Timeout between tests that are run in sequence.
     * PARAM 5 : Motor count. Number of motors to test in sequence: 0/1=one motor, 2= two motors, etc. The Timeout (param4) is used between tests.
     * PARAM 6 : Motor test order.
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_MOTOR_TEST = 209;
    /**
     * Change to/from inverted flight.
     * PARAM 1 : Inverted flight. (0=normal, 1=inverted)
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_INVERTED_FLIGHT = 210;
    /**
     * Mission command to operate a gripper.
     * PARAM 1 : Gripper instance number.
     * PARAM 2 : Gripper action to perform.
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_GRIPPER = 211;
    /**
     * Enable/disable autotune.
     * PARAM 1 : Enable (1: enable, 0:disable).
     * PARAM 2 : Specify which axis are autotuned. 0 indicates autopilot default settings.
     * PARAM 3 : Empty.
     * PARAM 4 : Empty.
     * PARAM 5 : Empty.
     * PARAM 6 : Empty.
     * PARAM 7 : Empty.
     */
    public final static int MAV_CMD_DO_AUTOTUNE_ENABLE = 212;
    /**
     * Sets a desired vehicle turn angle and speed change.
     * PARAM 1 : Yaw angle to adjust steering by.
     * PARAM 2 : Speed.
     * PARAM 3 : Final angle. (0=absolute, 1=relative)
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_NAV_SET_YAW_SPEED = 213;
    /**
     * Mission command to set camera trigger interval for this flight. If triggering is enabled, the camera is triggered each time this interval expires. This command can also be used to set the shutter integration time for the camera.
     * PARAM 1 : Camera trigger cycle time. -1 or 0 to ignore.
     * PARAM 2 : Camera shutter integration time. Should be less than trigger cycle time. -1 or 0 to ignore.
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_SET_CAM_TRIGG_INTERVAL = 214;
    /**
     * Mission command to control a camera or antenna mount, using a quaternion as reference.
     * PARAM 1 : quaternion param q1, w (1 in null-rotation)
     * PARAM 2 : quaternion param q2, x (0 in null-rotation)
     * PARAM 3 : quaternion param q3, y (0 in null-rotation)
     * PARAM 4 : quaternion param q4, z (0 in null-rotation)
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_MOUNT_CONTROL_QUAT = 220;
    /**
     * set id of master controller
     * PARAM 1 : System ID
     * PARAM 2 : Component ID
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_GUIDED_MASTER = 221;
    /**
     * Set limits for external control
     * PARAM 1 : Timeout - maximum time that external controller will be allowed to control vehicle. 0 means no timeout.
     * PARAM 2 : Altitude (MSL) min - if vehicle moves below this alt, the command will be aborted and the mission will continue. 0 means no lower altitude limit.
     * PARAM 3 : Altitude (MSL) max - if vehicle moves above this alt, the command will be aborted and the mission will continue. 0 means no upper altitude limit.
     * PARAM 4 : Horizontal move limit - if vehicle moves more than this distance from its location at the moment the command was executed, the command will be aborted and the mission will continue. 0 means no horizontal move limit.
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_GUIDED_LIMITS = 222;
    /**
     * Control vehicle engine. This is interpreted by the vehicles engine controller to change the target engine state. It is intended for vehicles with internal combustion engines
     * PARAM 1 : 0: Stop engine, 1:Start Engine
     * PARAM 2 : 0: Warm start, 1:Cold start. Controls use of choke where applicable
     * PARAM 3 : Height delay. This is for commanding engine start only after the vehicle has gained the specified height. Used in VTOL vehicles during takeoff to start engine after the aircraft is off the ground. Zero for no delay.
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_ENGINE_CONTROL = 223;
    /**
     * Set the mission item with sequence number seq as the current item and emit MISSION_CURRENT (whether or not the mission number changed).
          If a mission is currently being executed, the system will continue to this new mission item on the shortest path, skipping any intermediate mission items.
	  Note that mission jump repeat counters are not reset unless param2 is set (see MAV_CMD_DO_JUMP param2).

          This command may trigger a mission state-machine change on some systems: for example from MISSION_STATE_NOT_STARTED or MISSION_STATE_PAUSED to MISSION_STATE_ACTIVE.
          If the system is in mission mode, on those systems this command might therefore start, restart or resume the mission.
          If the system is not in mission mode this command must not trigger a switch to mission mode.

          The mission may be "reset" using param2.
          Resetting sets jump counters to initial values (to reset counters without changing the current mission item set the param1 to `-1`).
          Resetting also explicitly changes a mission state of MISSION_STATE_COMPLETE to MISSION_STATE_PAUSED or MISSION_STATE_ACTIVE, potentially allowing it to resume when it is (next) in a mission mode.

	  The command will ACK with MAV_RESULT_FAILED if the sequence number is out of range (including if there is no mission item).
     * PARAM 1 : Mission sequence value to set. -1 for the current mission item (use to reset mission without changing current mission item).
     * PARAM 2 : Resets mission. 1: true, 0: false. Resets jump counters to initial values and changes mission state "completed" to be "active" or "paused".
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_SET_MISSION_CURRENT = 224;
    /**
     * NOP - This command is only used to mark the upper limit of the DO commands in the enumeration
     * PARAM 1 : Empty
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_DO_LAST = 240;
    /**
     * Trigger calibration. This command will be only accepted if in pre-flight mode. Except for Temperature Calibration, only one sensor should be set in a single message and all others should be zero.
     * PARAM 1 : 1: gyro calibration, 3: gyro temperature calibration
     * PARAM 2 : 1: magnetometer calibration
     * PARAM 3 : 1: ground pressure calibration
     * PARAM 4 : 1: radio RC calibration, 2: RC trim calibration
     * PARAM 5 : 1: accelerometer calibration, 2: board level calibration, 3: accelerometer temperature calibration, 4: simple accelerometer calibration
     * PARAM 6 : 1: APM: compass/motor interference calibration (PX4: airspeed calibration, deprecated), 2: airspeed calibration
     * PARAM 7 : 1: ESC calibration, 3: barometer temperature calibration
     */
    public final static int MAV_CMD_PREFLIGHT_CALIBRATION = 241;
    /**
     * Set sensor offsets. This command will be only accepted if in pre-flight mode.
     * PARAM 1 : Sensor to adjust the offsets for: 0: gyros, 1: accelerometer, 2: magnetometer, 3: barometer, 4: optical flow, 5: second magnetometer, 6: third magnetometer
     * PARAM 2 : X axis offset (or generic dimension 1), in the sensor's raw units
     * PARAM 3 : Y axis offset (or generic dimension 2), in the sensor's raw units
     * PARAM 4 : Z axis offset (or generic dimension 3), in the sensor's raw units
     * PARAM 5 : Generic dimension 4, in the sensor's raw units
     * PARAM 6 : Generic dimension 5, in the sensor's raw units
     * PARAM 7 : Generic dimension 6, in the sensor's raw units
     */
    public final static int MAV_CMD_PREFLIGHT_SET_SENSOR_OFFSETS = 242;
    /**
     * Trigger UAVCAN configuration (actuator ID assignment and direction mapping). Note that this maps to the legacy UAVCAN v0 function UAVCAN_ENUMERATE, which is intended to be executed just once during initial vehicle configuration (it is not a normal pre-flight command and has been poorly named).
     * PARAM 1 : 1: Trigger actuator ID assignment and direction mapping. 0: Cancel command.
     * PARAM 2 : Reserved
     * PARAM 3 : Reserved
     * PARAM 4 : Reserved
     * PARAM 5 : Reserved
     * PARAM 6 : Reserved
     * PARAM 7 : Reserved
     */
    public final static int MAV_CMD_PREFLIGHT_UAVCAN = 243;
    /**
     * Request storage of different parameter values and logs. This command will be only accepted if in pre-flight mode.
     * PARAM 1 : Action to perform on the persistent parameter storage
     * PARAM 2 : Action to perform on the persistent mission storage
     * PARAM 3 : Onboard logging: 0: Ignore, 1: Start default rate logging, -1: Stop logging, > 1: logging rate (e.g. set to 1000 for 1000 Hz logging)
     * PARAM 4 : Reserved
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_PREFLIGHT_STORAGE = 245;
    /**
     * Request the reboot or shutdown of system components.
     * PARAM 1 : 0: Do nothing for autopilot, 1: Reboot autopilot, 2: Shutdown autopilot, 3: Reboot autopilot and keep it in the bootloader until upgraded.
     * PARAM 2 : 0: Do nothing for onboard computer, 1: Reboot onboard computer, 2: Shutdown onboard computer, 3: Reboot onboard computer and keep it in the bootloader until upgraded.
     * PARAM 3 : 0: Do nothing for component, 1: Reboot component, 2: Shutdown component, 3: Reboot component and keep it in the bootloader until upgraded
     * PARAM 4 : MAVLink Component ID targeted in param3 (0 for all components).
     * PARAM 5 : Reserved (set to 0)
     * PARAM 6 : Reserved (set to 0)
     * PARAM 7 : WIP: ID (e.g. camera ID -1 for all IDs)
     */
    public final static int MAV_CMD_PREFLIGHT_REBOOT_SHUTDOWN = 246;
    /**
     * Override current mission with command to pause mission, pause mission and move to position, continue/resume mission. When param 1 indicates that the mission is paused (MAV_GOTO_DO_HOLD), param 2 defines whether it holds in place or moves to another position.
     * PARAM 1 : MAV_GOTO_DO_HOLD: pause mission and either hold or move to specified position (depending on param2), MAV_GOTO_DO_CONTINUE: resume mission.
     * PARAM 2 : MAV_GOTO_HOLD_AT_CURRENT_POSITION: hold at current position, MAV_GOTO_HOLD_AT_SPECIFIED_POSITION: hold at specified position.
     * PARAM 3 : Coordinate frame of hold point.
     * PARAM 4 : Desired yaw angle.
     * PARAM 5 : Latitude/X position.
     * PARAM 6 : Longitude/Y position.
     * PARAM 7 : Altitude/Z position.
     */
    public final static int MAV_CMD_OVERRIDE_GOTO = 252;
    /**
     * Mission command to set a Camera Auto Mount Pivoting Oblique Survey (Replaces CAM_TRIGG_DIST for this purpose). The camera is triggered each time this distance is exceeded, then the mount moves to the next position. Params 4~6 set-up the angle limits and number of positions for oblique survey, where mount-enabled vehicles automatically roll the camera between shots to emulate an oblique camera setup (providing an increased HFOV). This command can also be used to set the shutter integration time for the camera.
     * PARAM 1 : Camera trigger distance. 0 to stop triggering.
     * PARAM 2 : Camera shutter integration time. 0 to ignore
     * PARAM 3 : The minimum interval in which the camera is capable of taking subsequent pictures repeatedly. 0 to ignore.
     * PARAM 4 : Total number of roll positions at which the camera will capture photos (images captures spread evenly across the limits defined by param5).
     * PARAM 5 : Angle limits that the camera can be rolled to left and right of center.
     * PARAM 6 : Fixed pitch angle that the camera will hold in oblique mode if the mount is actuated in the pitch axis.
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_OBLIQUE_SURVEY = 260;
    /**
     * start running a mission
     * PARAM 1 : first_item: the first mission item to run
     * PARAM 2 : last_item:  the last mission item to run (after this item is run, the mission ends)
     */
    public final static int MAV_CMD_MISSION_START = 300;
    /**
     * Actuator testing command. This is similar to MAV_CMD_DO_MOTOR_TEST but operates on the level of output functions, i.e. it is possible to test Motor1 independent from which output it is configured on. Autopilots typically refuse this command while armed.
     * PARAM 1 : Output value: 1 means maximum positive output, 0 to center servos or minimum motor thrust (expected to spin), -1 for maximum negative (if not supported by the motors, i.e. motor is not reversible, smaller than 0 maps to NaN). And NaN maps to disarmed (stop the motors).
     * PARAM 2 : Timeout after which the test command expires and the output is restored to the previous value. A timeout has to be set for safety reasons. A timeout of 0 means to restore the previous value immediately.
     * PARAM 3 : 
     * PARAM 4 : 
     * PARAM 5 : Actuator Output function
     * PARAM 6 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_ACTUATOR_TEST = 310;
    /**
     * Actuator configuration command.
     * PARAM 1 : Actuator configuration action
     * PARAM 2 : 
     * PARAM 3 : 
     * PARAM 4 : 
     * PARAM 5 : Actuator Output function
     * PARAM 6 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_CONFIGURE_ACTUATOR = 311;
    /**
     * Arms / Disarms a component
     * PARAM 1 : 0: disarm, 1: arm
     * PARAM 2 : 0: arm-disarm unless prevented by safety checks (i.e. when landed), 21196: force arming/disarming (e.g. allow arming to override preflight checks and disarming in flight)
     */
    public final static int MAV_CMD_COMPONENT_ARM_DISARM = 400;
    /**
     * Instructs a target system to run pre-arm checks.
          This allows preflight checks to be run on demand, which may be useful on systems that normally run them at low rate, or which do not trigger checks when the armable state might have changed.
          This command should return MAV_RESULT_ACCEPTED if it will run the checks.
          The results of the checks are usually then reported in SYS_STATUS messages (this is system-specific).
          The command should return MAV_RESULT_TEMPORARILY_REJECTED if the system is already armed.
     */
    public final static int MAV_CMD_RUN_PREARM_CHECKS = 401;
    /**
     * Turns illuminators ON/OFF. An illuminator is a light source that is used for lighting up dark areas external to the system: e.g. a torch or searchlight (as opposed to a light source for illuminating the system itself, e.g. an indicator light).
     * PARAM 1 : 0: Illuminators OFF, 1: Illuminators ON
     */
    public final static int MAV_CMD_ILLUMINATOR_ON_OFF = 405;
    /**
     * Request the home position from the vehicle.
	  The vehicle will ACK the command and then emit the HOME_POSITION message.
     * PARAM 1 : Reserved
     * PARAM 2 : Reserved
     * PARAM 3 : Reserved
     * PARAM 4 : Reserved
     * PARAM 5 : Reserved
     * PARAM 6 : Reserved
     * PARAM 7 : Reserved
     */
    public final static int MAV_CMD_GET_HOME_POSITION = 410;
    /**
     * Inject artificial failure for testing purposes. Note that autopilots should implement an additional protection before accepting this command such as a specific param setting.
     * PARAM 1 : The unit which is affected by the failure.
     * PARAM 2 : The type how the failure manifests itself.
     * PARAM 3 : Instance affected by failure (0 to signal all).
     */
    public final static int MAV_CMD_INJECT_FAILURE = 420;
    /**
     * Starts receiver pairing.
     * PARAM 1 : 0:Spektrum.
     * PARAM 2 : RC type.
     */
    public final static int MAV_CMD_START_RX_PAIR = 500;
    /**
     * Request the interval between messages for a particular MAVLink message ID.
          The receiver should ACK the command and then emit its response in a MESSAGE_INTERVAL message.
     * PARAM 1 : The MAVLink message ID
     */
    public final static int MAV_CMD_GET_MESSAGE_INTERVAL = 510;
    /**
     * Set the interval between messages for a particular MAVLink message ID. This interface replaces REQUEST_DATA_STREAM.
     * PARAM 1 : The MAVLink message ID
     * PARAM 2 : The interval between two messages. -1: disable. 0: request default rate (which may be zero).
     * PARAM 7 : Target address of message stream (if message has target address fields). 0: Flight-stack default (recommended), 1: address of requestor, 2: broadcast.
     */
    public final static int MAV_CMD_SET_MESSAGE_INTERVAL = 511;
    /**
     * Request the target system(s) emit a single instance of a specified message (i.e. a "one-shot" version of MAV_CMD_SET_MESSAGE_INTERVAL).
     * PARAM 1 : The MAVLink message ID of the requested message.
     * PARAM 2 : Use for index ID, if required. Otherwise, the use of this parameter (if any) must be defined in the requested message. By default assumed not used (0).
     * PARAM 3 : The use of this parameter (if any), must be defined in the requested message. By default assumed not used (0).
     * PARAM 4 : The use of this parameter (if any), must be defined in the requested message. By default assumed not used (0).
     * PARAM 5 : The use of this parameter (if any), must be defined in the requested message. By default assumed not used (0).
     * PARAM 6 : The use of this parameter (if any), must be defined in the requested message. By default assumed not used (0).
     * PARAM 7 : Target address for requested message (if message has target address fields). 0: Flight-stack default, 1: address of requestor, 2: broadcast.
     */
    public final static int MAV_CMD_REQUEST_MESSAGE = 512;
    /**
     * Request MAVLink protocol version compatibility. All receivers should ACK the command and then emit their capabilities in an PROTOCOL_VERSION message
     * PARAM 1 : 1: Request supported protocol versions by all nodes on the network
     * PARAM 2 : Reserved (all remaining params)
     */
    public final static int MAV_CMD_REQUEST_PROTOCOL_VERSION = 519;
    /**
     * Request autopilot capabilities. The receiver should ACK the command and then emit its capabilities in an AUTOPILOT_VERSION message
     * PARAM 1 : 1: Request autopilot version
     * PARAM 2 : Reserved (all remaining params)
     */
    public final static int MAV_CMD_REQUEST_AUTOPILOT_CAPABILITIES = 520;
    /**
     * Request camera information (CAMERA_INFORMATION).
     * PARAM 1 : 0: No action 1: Request camera capabilities
     * PARAM 2 : Reserved (all remaining params)
     */
    public final static int MAV_CMD_REQUEST_CAMERA_INFORMATION = 521;
    /**
     * Request camera settings (CAMERA_SETTINGS).
     * PARAM 1 : 0: No Action 1: Request camera settings
     * PARAM 2 : Reserved (all remaining params)
     */
    public final static int MAV_CMD_REQUEST_CAMERA_SETTINGS = 522;
    /**
     * Request storage information (STORAGE_INFORMATION). Use the command's target_component to target a specific component's storage.
     * PARAM 1 : Storage ID (0 for all, 1 for first, 2 for second, etc.)
     * PARAM 2 : 0: No Action 1: Request storage information
     * PARAM 3 : Reserved (all remaining params)
     */
    public final static int MAV_CMD_REQUEST_STORAGE_INFORMATION = 525;
    /**
     * Format a storage medium. Once format is complete, a STORAGE_INFORMATION message is sent. Use the command's target_component to target a specific component's storage.
     * PARAM 1 : Storage ID (1 for first, 2 for second, etc.)
     * PARAM 2 : Format storage (and reset image log). 0: No action 1: Format storage
     * PARAM 3 : Reset Image Log (without formatting storage medium). This will reset CAMERA_CAPTURE_STATUS.image_count and CAMERA_IMAGE_CAPTURED.image_index. 0: No action 1: Reset Image Log
     * PARAM 4 : Reserved (all remaining params)
     */
    public final static int MAV_CMD_STORAGE_FORMAT = 526;
    /**
     * Request camera capture status (CAMERA_CAPTURE_STATUS)
     * PARAM 1 : 0: No Action 1: Request camera capture status
     * PARAM 2 : Reserved (all remaining params)
     */
    public final static int MAV_CMD_REQUEST_CAMERA_CAPTURE_STATUS = 527;
    /**
     * Request flight information (FLIGHT_INFORMATION)
     * PARAM 1 : 1: Request flight information
     * PARAM 2 : Reserved (all remaining params)
     */
    public final static int MAV_CMD_REQUEST_FLIGHT_INFORMATION = 528;
    /**
     * Reset all camera settings to Factory Default
     * PARAM 1 : 0: No Action 1: Reset all settings
     * PARAM 2 : Reserved (all remaining params)
     */
    public final static int MAV_CMD_RESET_CAMERA_SETTINGS = 529;
    /**
     * Set camera running mode. Use NaN for reserved values. GCS will send a MAV_CMD_REQUEST_VIDEO_STREAM_STATUS command after a mode change if the camera supports video streaming.
     * PARAM 1 : Reserved (Set to 0)
     * PARAM 2 : Camera mode
     * PARAM 3 : 
     * PARAM 4 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_SET_CAMERA_MODE = 530;
    /**
     * Set camera zoom. Camera must respond with a CAMERA_SETTINGS message (on success).
     * PARAM 1 : Zoom type
     * PARAM 2 : Zoom value. The range of valid values depend on the zoom type.
     * PARAM 3 : 
     * PARAM 4 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_SET_CAMERA_ZOOM = 531;
    /**
     * Set camera focus. Camera must respond with a CAMERA_SETTINGS message (on success).
     * PARAM 1 : Focus type
     * PARAM 2 : Focus value
     * PARAM 3 : 
     * PARAM 4 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_SET_CAMERA_FOCUS = 532;
    /**
     * Set that a particular storage is the preferred location for saving photos, videos, and/or other media (e.g. to set that an SD card is used for storing videos).
          There can only be one preferred save location for each particular media type: setting a media usage flag will clear/reset that same flag if set on any other storage.
          If no flag is set the system should use its default storage.
          A target system can choose to always use default storage, in which case it should ACK the command with MAV_RESULT_UNSUPPORTED.
          A target system can choose to not allow a particular storage to be set as preferred storage, in which case it should ACK the command with MAV_RESULT_DENIED.
     * PARAM 1 : Storage ID (1 for first, 2 for second, etc.)
     * PARAM 2 : Usage flags
     */
    public final static int MAV_CMD_SET_STORAGE_USAGE = 533;
    /**
     * Tagged jump target. Can be jumped to with MAV_CMD_DO_JUMP_TAG.
     * PARAM 1 : Tag.
     */
    public final static int MAV_CMD_JUMP_TAG = 600;
    /**
     * Jump to the matching tag in the mission list. Repeat this action for the specified number of times. A mission should contain a single matching tag for each jump. If this is not the case then a jump to a missing tag should complete the mission, and a jump where there are multiple matching tags should always select the one with the lowest mission sequence number.
     * PARAM 1 : Target tag to jump to.
     * PARAM 2 : Repeat count.
     */
    public final static int MAV_CMD_DO_JUMP_TAG = 601;
    /**
     * Set gimbal manager pitch/yaw setpoints (low rate command). It is possible to set combinations of the values below. E.g. an angle as well as a desired angular rate can be used to get to this angle at a certain angular rate, or an angular rate only will result in continuous turning. NaN is to be used to signal unset. Note: only the gimbal manager will react to this command - it will be ignored by a gimbal device. Use GIMBAL_MANAGER_SET_PITCHYAW if you need to stream pitch/yaw setpoints at higher rate.
     * PARAM 1 : Pitch angle (positive to pitch up, relative to vehicle for FOLLOW mode, relative to world horizon for LOCK mode).
     * PARAM 2 : Yaw angle (positive to yaw to the right, relative to vehicle for FOLLOW mode, absolute to North for LOCK mode).
     * PARAM 3 : Pitch rate (positive to pitch up).
     * PARAM 4 : Yaw rate (positive to yaw to the right).
     * PARAM 5 : Gimbal manager flags to use.
     * PARAM 7 : Component ID of gimbal device to address (or 1-6 for non-MAVLink gimbal), 0 for all gimbal device components. Send command multiple times for more than one gimbal (but not all gimbals).
     */
    public final static int MAV_CMD_DO_GIMBAL_MANAGER_PITCHYAW = 1000;
    /**
     * Gimbal configuration to set which sysid/compid is in primary and secondary control.
     * PARAM 1 : Sysid for primary control (0: no one in control, -1: leave unchanged, -2: set itself in control (for missions where the own sysid is still unknown), -3: remove control if currently in control).
     * PARAM 2 : Compid for primary control (0: no one in control, -1: leave unchanged, -2: set itself in control (for missions where the own sysid is still unknown), -3: remove control if currently in control).
     * PARAM 3 : Sysid for secondary control (0: no one in control, -1: leave unchanged, -2: set itself in control (for missions where the own sysid is still unknown), -3: remove control if currently in control).
     * PARAM 4 : Compid for secondary control (0: no one in control, -1: leave unchanged, -2: set itself in control (for missions where the own sysid is still unknown), -3: remove control if currently in control).
     * PARAM 7 : Component ID of gimbal device to address (or 1-6 for non-MAVLink gimbal), 0 for all gimbal device components. Send command multiple times for more than one gimbal (but not all gimbals).
     */
    public final static int MAV_CMD_DO_GIMBAL_MANAGER_CONFIGURE = 1001;
    /**
     * Start image capture sequence. CAMERA_IMAGE_CAPTURED must be emitted after each capture.

          Param1 (id) may be used to specify the target camera: 0: all cameras, 1 to 6: autopilot-connected cameras, 7-255: MAVLink camera component ID.
          It is needed in order to target specific cameras connected to the autopilot, or specific sensors in a multi-sensor camera (neither of which have a distinct MAVLink component ID).
          It is also needed to specify the target camera in missions.

          When used in a mission, an autopilot should execute the MAV_CMD for a specified local camera (param1 = 1-6), or resend it as a command if it is intended for a MAVLink camera (param1 = 7 - 255), setting the command's target_component as the param1 value (and setting param1 in the command to zero).
          If the param1 is 0 the autopilot should do both.
          
          When sent in a command the target MAVLink address is set using target_component.
          If addressed specifically to an autopilot: param1 should be used in the same way as it is for missions (though command should NACK with MAV_RESULT_DENIED if a specified local camera does not exist).
          If addressed to a MAVLink camera, param 1 can be used to address all cameras (0), or to separately address 1 to 7 individual sensors. Other values should be NACKed with MAV_RESULT_DENIED.
          If the command is broadcast (target_component is 0) then param 1 should be set to 0 (any other value should be NACKED with MAV_RESULT_DENIED). An autopilot would trigger any local cameras and forward the command to all channels.
     * PARAM 1 : Target camera ID. 7 to 255: MAVLink camera component id. 1 to 6 for cameras that don't have a distinct component id (such as autopilot-attached cameras). 0: all cameras. This is used to specifically target autopilot-connected cameras or individual sensors in a multi-sensor MAVLink camera. It is also used to target specific cameras when the MAV_CMD is used in a mission
     * PARAM 2 : Desired elapsed time between two consecutive pictures (in seconds). Minimum values depend on hardware (typically greater than 2 seconds).
     * PARAM 3 : Total number of images to capture. 0 to capture forever/until MAV_CMD_IMAGE_STOP_CAPTURE.
     * PARAM 4 : Capture sequence number starting from 1. This is only valid for single-capture (param3 == 1), otherwise set to 0. Increment the capture ID for each capture command to prevent double captures when a command is re-transmitted.
     * PARAM 5 : 
     * PARAM 6 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_IMAGE_START_CAPTURE = 2000;
    /**
     * Stop image capture sequence.
        
          Param1 (id) may be used to specify the target camera: 0: all cameras, 1 to 6: autopilot-connected cameras, 7-255: MAVLink camera component ID.
          It is needed in order to target specific cameras connected to the autopilot, or specific sensors in a multi-sensor camera (neither of which have a distinct MAVLink component ID).
          It is also needed to specify the target camera in missions.

          When used in a mission, an autopilot should execute the MAV_CMD for a specified local camera (param1 = 1-6), or resend it as a command if it is intended for a MAVLink camera (param1 = 7 - 255), setting the command's target_component as the param1 value (and setting param1 in the command to zero).
          If the param1 is 0 the autopilot should do both.

          When sent in a command the target MAVLink address is set using target_component.
          If addressed specifically to an autopilot: param1 should be used in the same way as it is for missions (though command should NACK with MAV_RESULT_DENIED if a specified local camera does not exist).
          If addressed to a MAVLink camera, param1 can be used to address all cameras (0), or to separately address 1 to 7 individual sensors. Other values should be NACKed with MAV_RESULT_DENIED.
          If the command is broadcast (target_component is 0) then param 1 should be set to 0 (any other value should be NACKED with MAV_RESULT_DENIED). An autopilot would trigger any local cameras and forward the command to all channels.
     * PARAM 1 : Target camera ID. 7 to 255: MAVLink camera component id. 1 to 6 for cameras that don't have a distinct component id (such as autopilot-attached cameras). 0: all cameras. This is used to specifically target autopilot-connected cameras or individual sensors in a multi-sensor MAVLink camera. It is also used to target specific cameras when the MAV_CMD is used in a mission
     * PARAM 2 : 
     * PARAM 3 : 
     * PARAM 4 : 
     * PARAM 5 : 
     * PARAM 6 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_IMAGE_STOP_CAPTURE = 2001;
    /**
     * Re-request a CAMERA_IMAGE_CAPTURED message.
     * PARAM 1 : Sequence number for missing CAMERA_IMAGE_CAPTURED message
     * PARAM 2 : 
     * PARAM 3 : 
     * PARAM 4 : 
     * PARAM 5 : 
     * PARAM 6 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_REQUEST_CAMERA_IMAGE_CAPTURE = 2002;
    /**
     * Enable or disable on-board camera triggering system.
     * PARAM 1 : Trigger enable/disable (0 for disable, 1 for start), -1 to ignore
     * PARAM 2 : 1 to reset the trigger sequence, -1 or 0 to ignore
     * PARAM 3 : 1 to pause triggering, but without switching the camera off or retracting it. -1 to ignore
     */
    public final static int MAV_CMD_DO_TRIGGER_CONTROL = 2003;
    /**
     * If the camera supports point visual tracking (CAMERA_CAP_FLAGS_HAS_TRACKING_POINT is set), this command allows to initiate the tracking.
     * PARAM 1 : Point to track x value (normalized 0..1, 0 is left, 1 is right).
     * PARAM 2 : Point to track y value (normalized 0..1, 0 is top, 1 is bottom).
     * PARAM 3 : Point radius (normalized 0..1, 0 is image left, 1 is image right).
     */
    public final static int MAV_CMD_CAMERA_TRACK_POINT = 2004;
    /**
     * If the camera supports rectangle visual tracking (CAMERA_CAP_FLAGS_HAS_TRACKING_RECTANGLE is set), this command allows to initiate the tracking.
     * PARAM 1 : Top left corner of rectangle x value (normalized 0..1, 0 is left, 1 is right).
     * PARAM 2 : Top left corner of rectangle y value (normalized 0..1, 0 is top, 1 is bottom).
     * PARAM 3 : Bottom right corner of rectangle x value (normalized 0..1, 0 is left, 1 is right).
     * PARAM 4 : Bottom right corner of rectangle y value (normalized 0..1, 0 is top, 1 is bottom).
     */
    public final static int MAV_CMD_CAMERA_TRACK_RECTANGLE = 2005;
    /**
     * Stops ongoing tracking.
     */
    public final static int MAV_CMD_CAMERA_STOP_TRACKING = 2010;
    /**
     * Starts video capture (recording).
     * PARAM 1 : Video Stream ID (0 for all streams)
     * PARAM 2 : Frequency CAMERA_CAPTURE_STATUS messages should be sent while recording (0 for no messages, otherwise frequency)
     * PARAM 3 : 
     * PARAM 4 : 
     * PARAM 5 : 
     * PARAM 6 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_VIDEO_START_CAPTURE = 2500;
    /**
     * Stop the current video capture (recording).
     * PARAM 1 : Video Stream ID (0 for all streams)
     * PARAM 2 : 
     * PARAM 3 : 
     * PARAM 4 : 
     * PARAM 5 : 
     * PARAM 6 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_VIDEO_STOP_CAPTURE = 2501;
    /**
     * Start video streaming
     * PARAM 1 : Video Stream ID (0 for all streams, 1 for first, 2 for second, etc.)
     */
    public final static int MAV_CMD_VIDEO_START_STREAMING = 2502;
    /**
     * Stop the given video stream
     * PARAM 1 : Video Stream ID (0 for all streams, 1 for first, 2 for second, etc.)
     */
    public final static int MAV_CMD_VIDEO_STOP_STREAMING = 2503;
    /**
     * Request video stream information (VIDEO_STREAM_INFORMATION)
     * PARAM 1 : Video Stream ID (0 for all streams, 1 for first, 2 for second, etc.)
     */
    public final static int MAV_CMD_REQUEST_VIDEO_STREAM_INFORMATION = 2504;
    /**
     * Request video stream status (VIDEO_STREAM_STATUS)
     * PARAM 1 : Video Stream ID (0 for all streams, 1 for first, 2 for second, etc.)
     */
    public final static int MAV_CMD_REQUEST_VIDEO_STREAM_STATUS = 2505;
    /**
     * Request to start streaming logging data over MAVLink (see also LOGGING_DATA message)
     * PARAM 1 : Format: 0: ULog
     * PARAM 2 : Reserved (set to 0)
     * PARAM 3 : Reserved (set to 0)
     * PARAM 4 : Reserved (set to 0)
     * PARAM 5 : Reserved (set to 0)
     * PARAM 6 : Reserved (set to 0)
     * PARAM 7 : Reserved (set to 0)
     */
    public final static int MAV_CMD_LOGGING_START = 2510;
    /**
     * Request to stop streaming log data over MAVLink
     * PARAM 1 : Reserved (set to 0)
     * PARAM 2 : Reserved (set to 0)
     * PARAM 3 : Reserved (set to 0)
     * PARAM 4 : Reserved (set to 0)
     * PARAM 5 : Reserved (set to 0)
     * PARAM 6 : Reserved (set to 0)
     * PARAM 7 : Reserved (set to 0)
     */
    public final static int MAV_CMD_LOGGING_STOP = 2511;
    /**
     * 
     * PARAM 1 : Landing gear ID (default: 0, -1 for all)
     * PARAM 2 : Landing gear position (Down: 0, Up: 1, NaN for no change)
     * PARAM 3 : 
     * PARAM 4 : 
     * PARAM 5 : 
     * PARAM 6 : 
     * PARAM 7 : 
     */
    public final static int MAV_CMD_AIRFRAME_CONFIGURATION = 2520;
    /**
     * Request to start/stop transmitting over the high latency telemetry
     * PARAM 1 : Control transmission over high latency telemetry (0: stop, 1: start)
     * PARAM 2 : Empty
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Empty
     * PARAM 6 : Empty
     * PARAM 7 : Empty
     */
    public final static int MAV_CMD_CONTROL_HIGH_LATENCY = 2600;
    /**
     * Create a panorama at the current position
     * PARAM 1 : Viewing angle horizontal of the panorama (+- 0.5 the total angle)
     * PARAM 2 : Viewing angle vertical of panorama.
     * PARAM 3 : Speed of the horizontal rotation.
     * PARAM 4 : Speed of the vertical rotation.
     */
    public final static int MAV_CMD_PANORAMA_CREATE = 2800;
    /**
     * Request VTOL transition
     * PARAM 1 : The target VTOL state. For normal transitions, only MAV_VTOL_STATE_MC and MAV_VTOL_STATE_FW can be used.
     * PARAM 2 : Force immediate transition to the specified MAV_VTOL_STATE. 1: Force immediate, 0: normal transition. Can be used, for example, to trigger an emergency "Quadchute". Caution: Can be dangerous/damage vehicle, depending on autopilot implementation of this command.
     */
    public final static int MAV_CMD_DO_VTOL_TRANSITION = 3000;
    /**
     * Request authorization to arm the vehicle to a external entity, the arm authorizer is responsible to request all data that is needs from the vehicle before authorize or deny the request.
		If approved the COMMAND_ACK message progress field should be set with period of time that this authorization is valid in seconds.
		If the authorization is denied COMMAND_ACK.result_param2 should be set with one of the reasons in ARM_AUTH_DENIED_REASON.
     * PARAM 1 : Vehicle system id, this way ground station can request arm authorization on behalf of any vehicle
     */
    public final static int MAV_CMD_ARM_AUTHORIZATION_REQUEST = 3001;
    /**
     * This command sets the submode to standard guided when vehicle is in guided mode. The vehicle holds position and altitude and the user can input the desired velocities along all three axes.
     */
    public final static int MAV_CMD_SET_GUIDED_SUBMODE_STANDARD = 4000;
    /**
     * This command sets submode circle when vehicle is in guided mode. Vehicle flies along a circle facing the center of the circle. The user can input the velocity along the circle and change the radius. If no input is given the vehicle will hold position.
     * PARAM 1 : Radius of desired circle in CIRCLE_MODE
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : Target latitude of center of circle in CIRCLE_MODE
     * PARAM 6 : Target longitude of center of circle in CIRCLE_MODE
     */
    public final static int MAV_CMD_SET_GUIDED_SUBMODE_CIRCLE = 4001;
    /**
     * Delay mission state machine until gate has been reached.
     * PARAM 1 : Geometry: 0: orthogonal to path between previous and next waypoint.
     * PARAM 2 : Altitude: 0: ignore altitude
     * PARAM 3 : Empty
     * PARAM 4 : Empty
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_CONDITION_GATE = 4501;
    /**
     * Fence return point (there can only be one such point in a geofence definition). If rally points are supported they should be used instead.
     * PARAM 1 : Reserved
     * PARAM 2 : Reserved
     * PARAM 3 : Reserved
     * PARAM 4 : Reserved
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_NAV_FENCE_RETURN_POINT = 5000;
    /**
     * Fence vertex for an inclusion polygon (the polygon must not be self-intersecting). The vehicle must stay within this area. Minimum of 3 vertices required.
     * PARAM 1 : Polygon vertex count
     * PARAM 2 : Vehicle must be inside ALL inclusion zones in a single group, vehicle must be inside at least one group, must be the same for all points in each polygon
     * PARAM 3 : Reserved
     * PARAM 4 : Reserved
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Reserved
     */
    public final static int MAV_CMD_NAV_FENCE_POLYGON_VERTEX_INCLUSION = 5001;
    /**
     * Fence vertex for an exclusion polygon (the polygon must not be self-intersecting). The vehicle must stay outside this area. Minimum of 3 vertices required.
     * PARAM 1 : Polygon vertex count
     * PARAM 2 : Reserved
     * PARAM 3 : Reserved
     * PARAM 4 : Reserved
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Reserved
     */
    public final static int MAV_CMD_NAV_FENCE_POLYGON_VERTEX_EXCLUSION = 5002;
    /**
     * Circular fence area. The vehicle must stay inside this area.
     * PARAM 1 : Radius.
     * PARAM 2 : Vehicle must be inside ALL inclusion zones in a single group, vehicle must be inside at least one group
     * PARAM 3 : Reserved
     * PARAM 4 : Reserved
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Reserved
     */
    public final static int MAV_CMD_NAV_FENCE_CIRCLE_INCLUSION = 5003;
    /**
     * Circular fence area. The vehicle must stay outside this area.
     * PARAM 1 : Radius.
     * PARAM 2 : Reserved
     * PARAM 3 : Reserved
     * PARAM 4 : Reserved
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Reserved
     */
    public final static int MAV_CMD_NAV_FENCE_CIRCLE_EXCLUSION = 5004;
    /**
     * Rally point. You can have multiple rally points defined.
     * PARAM 1 : Reserved
     * PARAM 2 : Reserved
     * PARAM 3 : Reserved
     * PARAM 4 : Reserved
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude
     */
    public final static int MAV_CMD_NAV_RALLY_POINT = 5100;
    /**
     * Commands the vehicle to respond with a sequence of messages UAVCAN_NODE_INFO, one message per every UAVCAN node that is online. Note that some of the response messages can be lost, which the receiver can detect easily by checking whether every received UAVCAN_NODE_STATUS has a matching message UAVCAN_NODE_INFO received earlier; if not, this command should be sent again in order to request re-transmission of the node information messages.
     * PARAM 1 : Reserved (set to 0)
     * PARAM 2 : Reserved (set to 0)
     * PARAM 3 : Reserved (set to 0)
     * PARAM 4 : Reserved (set to 0)
     * PARAM 5 : Reserved (set to 0)
     * PARAM 6 : Reserved (set to 0)
     * PARAM 7 : Reserved (set to 0)
     */
    public final static int MAV_CMD_UAVCAN_GET_NODE_INFO = 5200;
    /**
     * Trigger the start of an ADSB-out IDENT. This should only be used when requested to do so by an Air Traffic Controller in controlled airspace. This starts the IDENT which is then typically held for 18 seconds by the hardware per the Mode A, C, and S transponder spec.
     * PARAM 1 : Reserved (set to 0)
     * PARAM 2 : Reserved (set to 0)
     * PARAM 3 : Reserved (set to 0)
     * PARAM 4 : Reserved (set to 0)
     * PARAM 5 : Reserved (set to 0)
     * PARAM 6 : Reserved (set to 0)
     * PARAM 7 : Reserved (set to 0)
     */
    public final static int MAV_CMD_DO_ADSB_OUT_IDENT = 10001;
    /**
     * Deploy payload on a Lat / Lon / Alt position. This includes the navigation to reach the required release position and velocity.
     * PARAM 1 : Operation mode. 0: prepare single payload deploy (overwriting previous requests), but do not execute it. 1: execute payload deploy immediately (rejecting further deploy commands during execution, but allowing abort). 2: add payload deploy to existing deployment list.
     * PARAM 2 : Desired approach vector in compass heading. A negative value indicates the system can define the approach vector at will.
     * PARAM 3 : Desired ground speed at release time. This can be overridden by the airframe in case it needs to meet minimum airspeed. A negative value indicates the system can define the ground speed at will.
     * PARAM 4 : Minimum altitude clearance to the release position. A negative value indicates the system can define the clearance at will.
     * PARAM 5 : Latitude.
     * PARAM 6 : Longitude.
     * PARAM 7 : Altitude (MSL)
     */
    public final static int MAV_CMD_PAYLOAD_PREPARE_DEPLOY = 30001;
    /**
     * Control the payload deployment.
     * PARAM 1 : Operation mode. 0: Abort deployment, continue normal mission. 1: switch to payload deployment mode. 100: delete first payload deployment request. 101: delete all payload deployment requests.
     * PARAM 2 : Reserved
     * PARAM 3 : Reserved
     * PARAM 4 : Reserved
     * PARAM 5 : Reserved
     * PARAM 6 : Reserved
     * PARAM 7 : Reserved
     */
    public final static int MAV_CMD_PAYLOAD_CONTROL_DEPLOY = 30002;
    /**
     * Magnetometer calibration based on provided known yaw. This allows for fast calibration using WMM field tables in the vehicle, given only the known yaw of the vehicle. If Latitude and longitude are both zero then use the current vehicle location.
     * PARAM 1 : Yaw of vehicle in earth frame.
     * PARAM 2 : CompassMask, 0 for all.
     * PARAM 3 : Latitude.
     * PARAM 4 : Longitude.
     * PARAM 5 : Empty.
     * PARAM 6 : Empty.
     * PARAM 7 : Empty.
     */
    public final static int MAV_CMD_FIXED_MAG_CAL_YAW = 42006;
    /**
     * Command to operate winch.
     * PARAM 1 : Winch instance number.
     * PARAM 2 : Action to perform.
     * PARAM 3 : Length of line to release (negative to wind).
     * PARAM 4 : Release rate (negative to wind).
     * PARAM 5 : Empty.
     * PARAM 6 : Empty.
     * PARAM 7 : Empty.
     */
    public final static int MAV_CMD_DO_WINCH = 42600;
    /**
     * Provide an external position estimate for use when dead-reckoning. This is meant to be used for occasional position resets that may be provided by a external system such as a remote pilot using landmarks over a video link.
     * PARAM 1 : Timestamp that this message was sent as a time in the transmitters time domain. The sender should wrap this time back to zero based on required timing accuracy for the application and the limitations of a 32 bit float. For example, wrapping at 10 hours would give approximately 1ms accuracy. Recipient must handle time wrap in any timing jitter correction applied to this field. Wrap rollover time should not be at not more than 250 seconds, which would give approximately 10 microsecond accuracy.
     * PARAM 2 : The time spent in processing the sensor data that is the basis for this position. The recipient can use this to improve time alignment of the data. Set to zero if not known.
     * PARAM 3 : estimated one standard deviation accuracy of the measurement. Set to NaN if not known.
     * PARAM 4 : Empty
     * PARAM 5 : Latitude
     * PARAM 6 : Longitude
     * PARAM 7 : Altitude, not used. Should be sent as NaN. May be supported in a future version of this message.
     */
    public final static int MAV_CMD_EXTERNAL_POSITION_ESTIMATE = 43003;
    /**
     * User defined waypoint item. Ground Station will show the Vehicle as flying through this item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : Latitude unscaled
     * PARAM 6 : Longitude unscaled
     * PARAM 7 : Altitude (MSL)
     */
    public final static int MAV_CMD_WAYPOINT_USER_1 = 31000;
    /**
     * User defined waypoint item. Ground Station will show the Vehicle as flying through this item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : Latitude unscaled
     * PARAM 6 : Longitude unscaled
     * PARAM 7 : Altitude (MSL)
     */
    public final static int MAV_CMD_WAYPOINT_USER_2 = 31001;
    /**
     * User defined waypoint item. Ground Station will show the Vehicle as flying through this item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : Latitude unscaled
     * PARAM 6 : Longitude unscaled
     * PARAM 7 : Altitude (MSL)
     */
    public final static int MAV_CMD_WAYPOINT_USER_3 = 31002;
    /**
     * User defined waypoint item. Ground Station will show the Vehicle as flying through this item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : Latitude unscaled
     * PARAM 6 : Longitude unscaled
     * PARAM 7 : Altitude (MSL)
     */
    public final static int MAV_CMD_WAYPOINT_USER_4 = 31003;
    /**
     * User defined waypoint item. Ground Station will show the Vehicle as flying through this item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : Latitude unscaled
     * PARAM 6 : Longitude unscaled
     * PARAM 7 : Altitude (MSL)
     */
    public final static int MAV_CMD_WAYPOINT_USER_5 = 31004;
    /**
     * User defined spatial item. Ground Station will not show the Vehicle as flying through this item. Example: ROI item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : Latitude unscaled
     * PARAM 6 : Longitude unscaled
     * PARAM 7 : Altitude (MSL)
     */
    public final static int MAV_CMD_SPATIAL_USER_1 = 31005;
    /**
     * User defined spatial item. Ground Station will not show the Vehicle as flying through this item. Example: ROI item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : Latitude unscaled
     * PARAM 6 : Longitude unscaled
     * PARAM 7 : Altitude (MSL)
     */
    public final static int MAV_CMD_SPATIAL_USER_2 = 31006;
    /**
     * User defined spatial item. Ground Station will not show the Vehicle as flying through this item. Example: ROI item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : Latitude unscaled
     * PARAM 6 : Longitude unscaled
     * PARAM 7 : Altitude (MSL)
     */
    public final static int MAV_CMD_SPATIAL_USER_3 = 31007;
    /**
     * User defined spatial item. Ground Station will not show the Vehicle as flying through this item. Example: ROI item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : Latitude unscaled
     * PARAM 6 : Longitude unscaled
     * PARAM 7 : Altitude (MSL)
     */
    public final static int MAV_CMD_SPATIAL_USER_4 = 31008;
    /**
     * User defined spatial item. Ground Station will not show the Vehicle as flying through this item. Example: ROI item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : Latitude unscaled
     * PARAM 6 : Longitude unscaled
     * PARAM 7 : Altitude (MSL)
     */
    public final static int MAV_CMD_SPATIAL_USER_5 = 31009;
    /**
     * User defined command. Ground Station will not show the Vehicle as flying through this item. Example: MAV_CMD_DO_SET_PARAMETER item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : User defined
     * PARAM 6 : User defined
     * PARAM 7 : User defined
     */
    public final static int MAV_CMD_USER_1 = 31010;
    /**
     * User defined command. Ground Station will not show the Vehicle as flying through this item. Example: MAV_CMD_DO_SET_PARAMETER item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : User defined
     * PARAM 6 : User defined
     * PARAM 7 : User defined
     */
    public final static int MAV_CMD_USER_2 = 31011;
    /**
     * User defined command. Ground Station will not show the Vehicle as flying through this item. Example: MAV_CMD_DO_SET_PARAMETER item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : User defined
     * PARAM 6 : User defined
     * PARAM 7 : User defined
     */
    public final static int MAV_CMD_USER_3 = 31012;
    /**
     * User defined command. Ground Station will not show the Vehicle as flying through this item. Example: MAV_CMD_DO_SET_PARAMETER item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : User defined
     * PARAM 6 : User defined
     * PARAM 7 : User defined
     */
    public final static int MAV_CMD_USER_4 = 31013;
    /**
     * User defined command. Ground Station will not show the Vehicle as flying through this item. Example: MAV_CMD_DO_SET_PARAMETER item.
     * PARAM 1 : User defined
     * PARAM 2 : User defined
     * PARAM 3 : User defined
     * PARAM 4 : User defined
     * PARAM 5 : User defined
     * PARAM 6 : User defined
     * PARAM 7 : User defined
     */
    public final static int MAV_CMD_USER_5 = 31014;
    /**
     * Request forwarding of CAN packets from the given CAN bus to this component. CAN Frames are sent using CAN_FRAME and CANFD_FRAME messages
     * PARAM 1 : Bus number (0 to disable forwarding, 1 for first bus, 2 for 2nd bus, 3 for 3rd bus).
     * PARAM 2 : Empty.
     * PARAM 3 : Empty.
     * PARAM 4 : Empty.
     * PARAM 5 : Empty.
     * PARAM 6 : Empty.
     * PARAM 7 : Empty.
     */
    public final static int MAV_CMD_CAN_FORWARD = 32000;
}
