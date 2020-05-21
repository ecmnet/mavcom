/**
 * Generated class : msg_vicon_position_estimate
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
 * Class msg_vicon_position_estimate
 * Global position estimate from a Vicon motion system source.
 **/
public class msg_vicon_position_estimate extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_VICON_POSITION_ESTIMATE = 104;
  private static final long serialVersionUID = MAVLINK_MSG_ID_VICON_POSITION_ESTIMATE;
  public msg_vicon_position_estimate() {
    this(1,1);
}
  public msg_vicon_position_estimate(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_VICON_POSITION_ESTIMATE;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 116;
}

  /**
   * Timestamp (UNIX time or time since system boot)
   */
  public long usec;
  /**
   * Global X position
   */
  public float x;
  /**
   * Global Y position
   */
  public float y;
  /**
   * Global Z position
   */
  public float z;
  /**
   * Roll angle
   */
  public float roll;
  /**
   * Pitch angle
   */
  public float pitch;
  /**
   * Yaw angle
   */
  public float yaw;
  /**
   * Row-major representation of 6x6 pose cross-covariance matrix upper right triangle (states: x, y, z, roll, pitch, yaw; first six entries are the first ROW, next five entries are the second ROW, etc.). If unknown, assign NaN value to first element in the array.
   */
  public float[] covariance = new float[21];
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  usec = (long)dis.readLong();
  x = (float)dis.readFloat();
  y = (float)dis.readFloat();
  z = (float)dis.readFloat();
  roll = (float)dis.readFloat();
  pitch = (float)dis.readFloat();
  yaw = (float)dis.readFloat();
  for (int i=0; i<21; i++) {
    covariance[i] = (float)dis.readFloat();
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+116];
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
  dos.writeLong(usec);
  dos.writeFloat(x);
  dos.writeFloat(y);
  dos.writeFloat(z);
  dos.writeFloat(roll);
  dos.writeFloat(pitch);
  dos.writeFloat(yaw);
  for (int i=0; i<21; i++) {
    dos.writeFloat(covariance[i]);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 116);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[126] = crcl;
  buffer[127] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_VICON_POSITION_ESTIMATE : " +   "  usec="+usec
+  "  x="+format((float)x)
+  "  y="+format((float)y)
+  "  z="+format((float)z)
+  "  roll="+format((float)roll)
+  "  pitch="+format((float)pitch)
+  "  yaw="+format((float)yaw)
+  "  covariance[0]="+format((float)covariance[0])
+  "  covariance[1]="+format((float)covariance[1])
+  "  covariance[2]="+format((float)covariance[2])
+  "  covariance[3]="+format((float)covariance[3])
+  "  covariance[4]="+format((float)covariance[4])
+  "  covariance[5]="+format((float)covariance[5])
+  "  covariance[6]="+format((float)covariance[6])
+  "  covariance[7]="+format((float)covariance[7])
+  "  covariance[8]="+format((float)covariance[8])
+  "  covariance[9]="+format((float)covariance[9])
+  "  covariance[10]="+format((float)covariance[10])
+  "  covariance[11]="+format((float)covariance[11])
+  "  covariance[12]="+format((float)covariance[12])
+  "  covariance[13]="+format((float)covariance[13])
+  "  covariance[14]="+format((float)covariance[14])
+  "  covariance[15]="+format((float)covariance[15])
+  "  covariance[16]="+format((float)covariance[16])
+  "  covariance[17]="+format((float)covariance[17])
+  "  covariance[18]="+format((float)covariance[18])
+  "  covariance[19]="+format((float)covariance[19])
+  "  covariance[20]="+format((float)covariance[20])
;}
}
