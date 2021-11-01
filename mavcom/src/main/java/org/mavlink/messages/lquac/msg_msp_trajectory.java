/**
 * Generated class : msg_msp_trajectory
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
 * Class msg_msp_trajectory
 * MSP Trajectory factors and length
 **/
public class msg_msp_trajectory extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_MSP_TRAJECTORY = 185;
  private static final long serialVersionUID = MAVLINK_MSG_ID_MSP_TRAJECTORY;
  public msg_msp_trajectory() {
    this(1,1);
}
  public msg_msp_trajectory(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_MSP_TRAJECTORY;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 48;
}

  /**
   * Timestamp
   */
  public long tms;
  /**
   * Length in secs
   */
  public float ls;
  /**
   * Alpha X
   */
  public float ax;
  /**
   * Alpha Y
   */
  public float ay;
  /**
   * Alpha Z
   */
  public float az;
  /**
   * Beta X
   */
  public float bx;
  /**
   * Beta Y
   */
  public float by;
  /**
   * Beta Z
   */
  public float bz;
  /**
   * Gamma X
   */
  public float gx;
  /**
   * Gamma Y
   */
  public float gy;
  /**
   * Gamma Z
   */
  public float gz;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  tms = (long)dis.readLong();
  ls = (float)dis.readFloat();
  ax = (float)dis.readFloat();
  ay = (float)dis.readFloat();
  az = (float)dis.readFloat();
  bx = (float)dis.readFloat();
  by = (float)dis.readFloat();
  bz = (float)dis.readFloat();
  gx = (float)dis.readFloat();
  gy = (float)dis.readFloat();
  gz = (float)dis.readFloat();
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+48];
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
  dos.writeLong(tms);
  dos.writeFloat(ls);
  dos.writeFloat(ax);
  dos.writeFloat(ay);
  dos.writeFloat(az);
  dos.writeFloat(bx);
  dos.writeFloat(by);
  dos.writeFloat(bz);
  dos.writeFloat(gx);
  dos.writeFloat(gy);
  dos.writeFloat(gz);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 48);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[58] = crcl;
  buffer[59] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_MSP_TRAJECTORY : " +   "  tms="+tms
+  "  ls="+format((float)ls)
+  "  ax="+format((float)ax)
+  "  ay="+format((float)ay)
+  "  az="+format((float)az)
+  "  bx="+format((float)bx)
+  "  by="+format((float)by)
+  "  bz="+format((float)bz)
+  "  gx="+format((float)gx)
+  "  gy="+format((float)gy)
+  "  gz="+format((float)gz)
;}

}

