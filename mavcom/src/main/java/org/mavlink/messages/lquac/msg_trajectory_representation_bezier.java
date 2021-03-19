/**
 * Generated class : msg_trajectory_representation_bezier
 * DO NOT MODIFY!
 **/
package org.mavlink.messages.lquac;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.mavlink.IMAVLinkCRC;
import org.mavlink.MAVLinkCRC;
import org.mavlink.io.LittleEndianDataInputStream;
import org.mavlink.io.LittleEndianDataOutputStream;
import org.mavlink.messages.MAVLinkMessage;
/**
 * Class msg_trajectory_representation_bezier
 * Describe a trajectory using an array of up-to 5 bezier control points in the local frame (MAV_FRAME_LOCAL_NED).
 **/
public class msg_trajectory_representation_bezier extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_TRAJECTORY_REPRESENTATION_BEZIER = 333;
  private static final long serialVersionUID = MAVLINK_MSG_ID_TRAJECTORY_REPRESENTATION_BEZIER;
  public msg_trajectory_representation_bezier() {
    this(1,1);
}
  public msg_trajectory_representation_bezier(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_TRAJECTORY_REPRESENTATION_BEZIER;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 109;
}

  /**
   * Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude of the number.
   */
  public long time_usec;
  /**
   * X-coordinate of bezier control points. Set to NaN if not being used
   */
  public float[] pos_x = new float[5];
  /**
   * Y-coordinate of bezier control points. Set to NaN if not being used
   */
  public float[] pos_y = new float[5];
  /**
   * Z-coordinate of bezier control points. Set to NaN if not being used
   */
  public float[] pos_z = new float[5];
  /**
   * Bezier time horizon. Set to NaN if velocity/acceleration should not be incorporated
   */
  public float[] delta = new float[5];
  /**
   * Yaw. Set to NaN for unchanged
   */
  public float[] pos_yaw = new float[5];
  /**
   * Number of valid control points (up-to 5 points are possible)
   */
  public int valid_points;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_usec = (long)dis.readLong();
  for (int i=0; i<5; i++) {
    pos_x[i] = (float)dis.readFloat();
  }
  for (int i=0; i<5; i++) {
    pos_y[i] = (float)dis.readFloat();
  }
  for (int i=0; i<5; i++) {
    pos_z[i] = (float)dis.readFloat();
  }
  for (int i=0; i<5; i++) {
    delta[i] = (float)dis.readFloat();
  }
  for (int i=0; i<5; i++) {
    pos_yaw[i] = (float)dis.readFloat();
  }
  valid_points = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+109];
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
  dos.writeLong(time_usec);
  for (int i=0; i<5; i++) {
    dos.writeFloat(pos_x[i]);
  }
  for (int i=0; i<5; i++) {
    dos.writeFloat(pos_y[i]);
  }
  for (int i=0; i<5; i++) {
    dos.writeFloat(pos_z[i]);
  }
  for (int i=0; i<5; i++) {
    dos.writeFloat(delta[i]);
  }
  for (int i=0; i<5; i++) {
    dos.writeFloat(pos_yaw[i]);
  }
  dos.writeByte(valid_points&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 109);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[119] = crcl;
  buffer[120] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_TRAJECTORY_REPRESENTATION_BEZIER : " +   "  time_usec="+time_usec
+  "  pos_x[0]="+format((float)pos_x[0])
+  "  pos_x[1]="+format((float)pos_x[1])
+  "  pos_x[2]="+format((float)pos_x[2])
+  "  pos_x[3]="+format((float)pos_x[3])
+  "  pos_x[4]="+format((float)pos_x[4])
+  "  pos_y[0]="+format((float)pos_y[0])
+  "  pos_y[1]="+format((float)pos_y[1])
+  "  pos_y[2]="+format((float)pos_y[2])
+  "  pos_y[3]="+format((float)pos_y[3])
+  "  pos_y[4]="+format((float)pos_y[4])
+  "  pos_z[0]="+format((float)pos_z[0])
+  "  pos_z[1]="+format((float)pos_z[1])
+  "  pos_z[2]="+format((float)pos_z[2])
+  "  pos_z[3]="+format((float)pos_z[3])
+  "  pos_z[4]="+format((float)pos_z[4])
+  "  delta[0]="+format((float)delta[0])
+  "  delta[1]="+format((float)delta[1])
+  "  delta[2]="+format((float)delta[2])
+  "  delta[3]="+format((float)delta[3])
+  "  delta[4]="+format((float)delta[4])
+  "  pos_yaw[0]="+format((float)pos_yaw[0])
+  "  pos_yaw[1]="+format((float)pos_yaw[1])
+  "  pos_yaw[2]="+format((float)pos_yaw[2])
+  "  pos_yaw[3]="+format((float)pos_yaw[3])
+  "  pos_yaw[4]="+format((float)pos_yaw[4])
+  "  valid_points="+valid_points
;}
}
