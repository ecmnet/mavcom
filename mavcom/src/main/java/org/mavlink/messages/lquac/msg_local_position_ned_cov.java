/**
 * Generated class : msg_local_position_ned_cov
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
 * Class msg_local_position_ned_cov
 * The filtered local position (e.g. fused computer vision and accelerometers). Coordinate frame is right-handed, Z-axis down (aeronautical frame, NED / north-east-down convention)
 **/
public class msg_local_position_ned_cov extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_LOCAL_POSITION_NED_COV = 64;
  private static final long serialVersionUID = MAVLINK_MSG_ID_LOCAL_POSITION_NED_COV;
  public msg_local_position_ned_cov() {
    this(1,1);
}
  public msg_local_position_ned_cov(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_LOCAL_POSITION_NED_COV;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 225;
}

  /**
   * Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude of the number.
   */
  public long time_usec;
  /**
   * X Position
   */
  public float x;
  /**
   * Y Position
   */
  public float y;
  /**
   * Z Position
   */
  public float z;
  /**
   * X Speed
   */
  public float vx;
  /**
   * Y Speed
   */
  public float vy;
  /**
   * Z Speed
   */
  public float vz;
  /**
   * X Acceleration
   */
  public float ax;
  /**
   * Y Acceleration
   */
  public float ay;
  /**
   * Z Acceleration
   */
  public float az;
  /**
   * Row-major representation of position, velocity and acceleration 9x9 cross-covariance matrix upper right triangle (states: x, y, z, vx, vy, vz, ax, ay, az; first nine entries are the first ROW, next eight entries are the second row, etc.). If unknown, assign NaN value to first element in the array.
   */
  public float[] covariance = new float[45];
  /**
   * Class id of the estimator this estimate originated from.
   */
  public int estimator_type;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_usec = (long)dis.readLong();
  x = (float)dis.readFloat();
  y = (float)dis.readFloat();
  z = (float)dis.readFloat();
  vx = (float)dis.readFloat();
  vy = (float)dis.readFloat();
  vz = (float)dis.readFloat();
  ax = (float)dis.readFloat();
  ay = (float)dis.readFloat();
  az = (float)dis.readFloat();
  for (int i=0; i<45; i++) {
    covariance[i] = (float)dis.readFloat();
  }
  estimator_type = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+225];
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
  dos.writeFloat(x);
  dos.writeFloat(y);
  dos.writeFloat(z);
  dos.writeFloat(vx);
  dos.writeFloat(vy);
  dos.writeFloat(vz);
  dos.writeFloat(ax);
  dos.writeFloat(ay);
  dos.writeFloat(az);
  for (int i=0; i<45; i++) {
    dos.writeFloat(covariance[i]);
  }
  dos.writeByte(estimator_type&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 225);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[235] = crcl;
  buffer[236] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_LOCAL_POSITION_NED_COV : " +   "  time_usec="+time_usec
+  "  x="+String.format("%#2.5f",(float)x)
+  "  y="+String.format("%#2.5f",(float)y)
+  "  z="+String.format("%#2.5f",(float)z)
+  "  vx="+String.format("%#2.5f",(float)vx)
+  "  vy="+String.format("%#2.5f",(float)vy)
+  "  vz="+String.format("%#2.5f",(float)vz)
+  "  ax="+String.format("%#2.5f",(float)ax)
+  "  ay="+String.format("%#2.5f",(float)ay)
+  "  az="+String.format("%#2.5f",(float)az)
+  "  covariance[0]="+String.format("%#2.5f",(float)covariance[0])
+  "  covariance[1]="+String.format("%#2.5f",(float)covariance[1])
+  "  covariance[2]="+String.format("%#2.5f",(float)covariance[2])
+  "  covariance[3]="+String.format("%#2.5f",(float)covariance[3])
+  "  covariance[4]="+String.format("%#2.5f",(float)covariance[4])
+  "  covariance[5]="+String.format("%#2.5f",(float)covariance[5])
+  "  covariance[6]="+String.format("%#2.5f",(float)covariance[6])
+  "  covariance[7]="+String.format("%#2.5f",(float)covariance[7])
+  "  covariance[8]="+String.format("%#2.5f",(float)covariance[8])
+  "  covariance[9]="+String.format("%#2.5f",(float)covariance[9])
+  "  covariance[10]="+String.format("%#2.5f",(float)covariance[10])
+  "  covariance[11]="+String.format("%#2.5f",(float)covariance[11])
+  "  covariance[12]="+String.format("%#2.5f",(float)covariance[12])
+  "  covariance[13]="+String.format("%#2.5f",(float)covariance[13])
+  "  covariance[14]="+String.format("%#2.5f",(float)covariance[14])
+  "  covariance[15]="+String.format("%#2.5f",(float)covariance[15])
+  "  covariance[16]="+String.format("%#2.5f",(float)covariance[16])
+  "  covariance[17]="+String.format("%#2.5f",(float)covariance[17])
+  "  covariance[18]="+String.format("%#2.5f",(float)covariance[18])
+  "  covariance[19]="+String.format("%#2.5f",(float)covariance[19])
+  "  covariance[20]="+String.format("%#2.5f",(float)covariance[20])
+  "  covariance[21]="+String.format("%#2.5f",(float)covariance[21])
+  "  covariance[22]="+String.format("%#2.5f",(float)covariance[22])
+  "  covariance[23]="+String.format("%#2.5f",(float)covariance[23])
+  "  covariance[24]="+String.format("%#2.5f",(float)covariance[24])
+  "  covariance[25]="+String.format("%#2.5f",(float)covariance[25])
+  "  covariance[26]="+String.format("%#2.5f",(float)covariance[26])
+  "  covariance[27]="+String.format("%#2.5f",(float)covariance[27])
+  "  covariance[28]="+String.format("%#2.5f",(float)covariance[28])
+  "  covariance[29]="+String.format("%#2.5f",(float)covariance[29])
+  "  covariance[30]="+String.format("%#2.5f",(float)covariance[30])
+  "  covariance[31]="+String.format("%#2.5f",(float)covariance[31])
+  "  covariance[32]="+String.format("%#2.5f",(float)covariance[32])
+  "  covariance[33]="+String.format("%#2.5f",(float)covariance[33])
+  "  covariance[34]="+String.format("%#2.5f",(float)covariance[34])
+  "  covariance[35]="+String.format("%#2.5f",(float)covariance[35])
+  "  covariance[36]="+String.format("%#2.5f",(float)covariance[36])
+  "  covariance[37]="+String.format("%#2.5f",(float)covariance[37])
+  "  covariance[38]="+String.format("%#2.5f",(float)covariance[38])
+  "  covariance[39]="+String.format("%#2.5f",(float)covariance[39])
+  "  covariance[40]="+String.format("%#2.5f",(float)covariance[40])
+  "  covariance[41]="+String.format("%#2.5f",(float)covariance[41])
+  "  covariance[42]="+String.format("%#2.5f",(float)covariance[42])
+  "  covariance[43]="+String.format("%#2.5f",(float)covariance[43])
+  "  covariance[44]="+String.format("%#2.5f",(float)covariance[44])
+  "  estimator_type="+estimator_type
;}
}
