/**
 * Generated class : msg_home_position
 * DO NOT MODIFY!
 **/
package org.mavlink.messages.lquac;
import org.mavlink.messages.MAVLinkMessage;
import org.mavlink.IMAVLinkCRC;
import org.mavlink.MAVLinkCRC;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.mavlink.io.LittleEndianDataInputStream;
import org.mavlink.io.LittleEndianDataOutputStream;
/**
 * Class msg_home_position
 * Contains the home position.
	The home position is the default position that the system will return to and land on.
	The position must be set automatically by the system during the takeoff, and may also be explicitly set using MAV_CMD_DO_SET_HOME.
	The global and local positions encode the position in the respective coordinate frames, while the q parameter encodes the orientation of the surface.
	Under normal conditions it describes the heading and terrain slope, which can be used by the aircraft to adjust the approach.
	The approach 3D vector describes the point to which the system should fly in normal flight mode and then perform a landing sequence along the vector.
        Note: this message can be requested by sending the MAV_CMD_REQUEST_MESSAGE with param1=242 (or the deprecated MAV_CMD_GET_HOME_POSITION command).
 **/
public class msg_home_position extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_HOME_POSITION = 242;
  private static final long serialVersionUID = MAVLINK_MSG_ID_HOME_POSITION;
  public msg_home_position() {
    this(1,1);
}
  public msg_home_position(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_HOME_POSITION;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 60;
}

  /**
   * Latitude (WGS84)
   */
  public long latitude;
  /**
   * Longitude (WGS84)
   */
  public long longitude;
  /**
   * Altitude (MSL). Positive for up.
   */
  public long altitude;
  /**
   * Local X position of this position in the local coordinate frame (NED)
   */
  public float x;
  /**
   * Local Y position of this position in the local coordinate frame (NED)
   */
  public float y;
  /**
   * Local Z position of this position in the local coordinate frame (NED: positive "down")
   */
  public float z;
  /**
   * Quaternion indicating world-to-surface-normal and heading transformation of the takeoff position.
        Used to indicate the heading and slope of the ground.
        All fields should be set to NaN if an accurate quaternion for both heading and surface slope cannot be supplied.
   */
  public float[] q = new float[4];
  /**
   * Local X position of the end of the approach vector. Multicopters should set this position based on their takeoff path. Grass-landing fixed wing aircraft should set it the same way as multicopters. Runway-landing fixed wing aircraft should set it to the opposite direction of the takeoff, assuming the takeoff happened from the threshold / touchdown zone.
   */
  public float approach_x;
  /**
   * Local Y position of the end of the approach vector. Multicopters should set this position based on their takeoff path. Grass-landing fixed wing aircraft should set it the same way as multicopters. Runway-landing fixed wing aircraft should set it to the opposite direction of the takeoff, assuming the takeoff happened from the threshold / touchdown zone.
   */
  public float approach_y;
  /**
   * Local Z position of the end of the approach vector. Multicopters should set this position based on their takeoff path. Grass-landing fixed wing aircraft should set it the same way as multicopters. Runway-landing fixed wing aircraft should set it to the opposite direction of the takeoff, assuming the takeoff happened from the threshold / touchdown zone.
   */
  public float approach_z;
  /**
   * Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude of the number.
   */
  public long time_usec;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  latitude = (int)dis.readInt();
  longitude = (int)dis.readInt();
  altitude = (int)dis.readInt();
  x = (float)dis.readFloat();
  y = (float)dis.readFloat();
  z = (float)dis.readFloat();
  for (int i=0; i<4; i++) {
    q[i] = (float)dis.readFloat();
  }
  approach_x = (float)dis.readFloat();
  approach_y = (float)dis.readFloat();
  approach_z = (float)dis.readFloat();
  time_usec = (long)dis.readLong();
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+60];
   LittleEndianDataOutputStream dos = new LittleEndianDataOutputStream(new ByteArrayOutputStream());
  dos.writeByte((byte)0xFD);
  dos.writeByte(payload_length & 0x00FF);
  dos.writeByte(incompat & 0x00FF);
  dos.writeByte(compat & 0x00FF);
  dos.writeByte(packet & 0x00FF);
  dos.writeByte(sysId & 0x00FF);
  dos.writeByte(componentId & 0x00FF);
  dos.writeByte(messageType & 0x00FF);
  dos.writeByte((messageType >> 8) & 0x00FF);
  dos.writeByte((messageType >> 16) & 0x00FF);
  dos.writeInt((int)(latitude&0x00FFFFFFFF));
  dos.writeInt((int)(longitude&0x00FFFFFFFF));
  dos.writeInt((int)(altitude&0x00FFFFFFFF));
  dos.writeFloat(x);
  dos.writeFloat(y);
  dos.writeFloat(z);
  for (int i=0; i<4; i++) {
    dos.writeFloat(q[i]);
  }
  dos.writeFloat(approach_x);
  dos.writeFloat(approach_y);
  dos.writeFloat(approach_z);
  dos.writeLong(time_usec);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 60);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[70] = crcl;
  buffer[71] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_HOME_POSITION : " +   "  latitude="+latitude
+  "  longitude="+longitude
+  "  altitude="+altitude
+  "  x="+format((float)x)
+  "  y="+format((float)y)
+  "  z="+format((float)z)
+  "  q[0]="+format((float)q[0])
+  "  q[1]="+format((float)q[1])
+  "  q[2]="+format((float)q[2])
+  "  q[3]="+format((float)q[3])
+  "  approach_x="+format((float)approach_x)
+  "  approach_y="+format((float)approach_y)
+  "  approach_z="+format((float)approach_z)
+  "  time_usec="+time_usec
;}

}

