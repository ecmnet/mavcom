/**
 * Generated class : MAV_TYPE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_TYPE
 * MAVLINK component type reported in HEARTBEAT message. Flight controllers must report the type of the vehicle on which they are mounted (e.g. MAV_TYPE_OCTOROTOR). All other components must report a value appropriate for their type (e.g. a camera must use MAV_TYPE_CAMERA).
 **/
public interface MAV_TYPE {
    /**
     * Generic micro air vehicle
     */
    public final static int MAV_TYPE_GENERIC = 0;
    /**
     * Fixed wing aircraft.
     */
    public final static int MAV_TYPE_FIXED_WING = 1;
    /**
     * Quadrotor
     */
    public final static int MAV_TYPE_QUADROTOR = 2;
    /**
     * Coaxial helicopter
     */
    public final static int MAV_TYPE_COAXIAL = 3;
    /**
     * Normal helicopter with tail rotor.
     */
    public final static int MAV_TYPE_HELICOPTER = 4;
    /**
     * Ground installation
     */
    public final static int MAV_TYPE_ANTENNA_TRACKER = 5;
    /**
     * Operator control unit / ground control station
     */
    public final static int MAV_TYPE_GCS = 6;
    /**
     * Airship, controlled
     */
    public final static int MAV_TYPE_AIRSHIP = 7;
    /**
     * Free balloon, uncontrolled
     */
    public final static int MAV_TYPE_FREE_BALLOON = 8;
    /**
     * Rocket
     */
    public final static int MAV_TYPE_ROCKET = 9;
    /**
     * Ground rover
     */
    public final static int MAV_TYPE_GROUND_ROVER = 10;
    /**
     * Surface vessel, boat, ship
     */
    public final static int MAV_TYPE_SURFACE_BOAT = 11;
    /**
     * Submarine
     */
    public final static int MAV_TYPE_SUBMARINE = 12;
    /**
     * Hexarotor
     */
    public final static int MAV_TYPE_HEXAROTOR = 13;
    /**
     * Octorotor
     */
    public final static int MAV_TYPE_OCTOROTOR = 14;
    /**
     * Tricopter
     */
    public final static int MAV_TYPE_TRICOPTER = 15;
    /**
     * Flapping wing
     */
    public final static int MAV_TYPE_FLAPPING_WING = 16;
    /**
     * Kite
     */
    public final static int MAV_TYPE_KITE = 17;
    /**
     * Onboard companion controller
     */
    public final static int MAV_TYPE_ONBOARD_CONTROLLER = 18;
    /**
     * Two-rotor Tailsitter VTOL that additionally uses control surfaces in vertical operation. Note, value previously named MAV_TYPE_VTOL_DUOROTOR.
     */
    public final static int MAV_TYPE_VTOL_TAILSITTER_DUOROTOR = 19;
    /**
     * Quad-rotor Tailsitter VTOL using a V-shaped quad config in vertical operation. Note: value previously named MAV_TYPE_VTOL_QUADROTOR.
     */
    public final static int MAV_TYPE_VTOL_TAILSITTER_QUADROTOR = 20;
    /**
     * Tiltrotor VTOL. Fuselage and wings stay (nominally) horizontal in all flight phases. It able to tilt (some) rotors to provide thrust in cruise flight.
     */
    public final static int MAV_TYPE_VTOL_TILTROTOR = 21;
    /**
     * VTOL with separate fixed rotors for hover and cruise flight. Fuselage and wings stay (nominally) horizontal in all flight phases.
     */
    public final static int MAV_TYPE_VTOL_FIXEDROTOR = 22;
    /**
     * Tailsitter VTOL. Fuselage and wings orientation changes depending on flight phase: vertical for hover, horizontal for cruise. Use more specific VTOL MAV_TYPE_VTOL_TAILSITTER_DUOROTOR or MAV_TYPE_VTOL_TAILSITTER_QUADROTOR if appropriate.
     */
    public final static int MAV_TYPE_VTOL_TAILSITTER = 23;
    /**
     * Tiltwing VTOL. Fuselage stays horizontal in all flight phases. The whole wing, along with any attached engine, can tilt between vertical and horizontal mode.
     */
    public final static int MAV_TYPE_VTOL_TILTWING = 24;
    /**
     * VTOL reserved 5
     */
    public final static int MAV_TYPE_VTOL_RESERVED5 = 25;
    /**
     * Gimbal
     */
    public final static int MAV_TYPE_GIMBAL = 26;
    /**
     * ADSB system
     */
    public final static int MAV_TYPE_ADSB = 27;
    /**
     * Steerable, nonrigid airfoil
     */
    public final static int MAV_TYPE_PARAFOIL = 28;
    /**
     * Dodecarotor
     */
    public final static int MAV_TYPE_DODECAROTOR = 29;
    /**
     * Camera
     */
    public final static int MAV_TYPE_CAMERA = 30;
    /**
     * Charging station
     */
    public final static int MAV_TYPE_CHARGING_STATION = 31;
    /**
     * FLARM collision avoidance system
     */
    public final static int MAV_TYPE_FLARM = 32;
    /**
     * Servo
     */
    public final static int MAV_TYPE_SERVO = 33;
    /**
     * Open Drone ID. See https://mavlink.io/en/services/opendroneid.html.
     */
    public final static int MAV_TYPE_ODID = 34;
    /**
     * Decarotor
     */
    public final static int MAV_TYPE_DECAROTOR = 35;
    /**
     * Battery
     */
    public final static int MAV_TYPE_BATTERY = 36;
    /**
     * Parachute
     */
    public final static int MAV_TYPE_PARACHUTE = 37;
    /**
     * Log
     */
    public final static int MAV_TYPE_LOG = 38;
    /**
     * OSD
     */
    public final static int MAV_TYPE_OSD = 39;
    /**
     * IMU
     */
    public final static int MAV_TYPE_IMU = 40;
    /**
     * GPS
     */
    public final static int MAV_TYPE_GPS = 41;
    /**
     * Winch
     */
    public final static int MAV_TYPE_WINCH = 42;
    /**
     * Generic multirotor that does not fit into a specific type or whose type is unknown
     */
    public final static int MAV_TYPE_GENERIC_MULTIROTOR = 43;
}
