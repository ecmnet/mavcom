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
    payload_length = 56;
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
   * Done in secs
   */
  public float fs;
  /**
   * Alpha X
   */
  public float ax;
  /**
   * Alpha Y
   */
  public float ay;
  /**
   * Beta X
   */
  public float bx;
  /**
   * Beta Y
   */
  public float by;
  /**
   * Gamma X
   */
  public float gx;
  /**
   * Gamma Y
   */
  public float gy;
  /**
   * Start X
   */
  public float sx;
  /**
   * Start Y
   */
  public float sy;
  /**
   * Start VX
   */
  public float svx;
  /**
   * Start VY
   */
  public float svy;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  tms = (long)dis.readLong();
  ls = (float)dis.readFloat();
  fs = (float)dis.readFloat();
  ax = (float)dis.readFloat();
  ay = (float)dis.readFloat();
  bx = (float)dis.readFloat();
  by = (float)dis.readFloat();
  gx = (float)dis.readFloat();
  gy = (float)dis.readFloat();
  sx = (float)dis.readFloat();
  sy = (float)dis.readFloat();
  svx = (float)dis.readFloat();
  svy = (float)dis.readFloat();
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+56];
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
  dos.writeFloat(fs);
  dos.writeFloat(ax);
  dos.writeFloat(ay);
  dos.writeFloat(bx);
  dos.writeFloat(by);
  dos.writeFloat(gx);
  dos.writeFloat(gy);
  dos.writeFloat(sx);
  dos.writeFloat(sy);
  dos.writeFloat(svx);
  dos.writeFloat(svy);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 56);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[66] = crcl;
  buffer[67] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_MSP_TRAJECTORY : " +   "  tms="+tms
+  "  ls="+format((float)ls)
+  "  fs="+format((float)fs)
+  "  ax="+format((float)ax)
+  "  ay="+format((float)ay)
+  "  bx="+format((float)bx)
+  "  by="+format((float)by)
+  "  gx="+format((float)gx)
+  "  gy="+format((float)gy)
+  "  sx="+format((float)sx)
+  "  sy="+format((float)sy)
+  "  svx="+format((float)svx)
+  "  svy="+format((float)svy)
;}

}

