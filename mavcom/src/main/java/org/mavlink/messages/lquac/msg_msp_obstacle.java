/**
 * Generated class : msg_msp_obstacle
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
 * Class msg_msp_obstacle
 * MSP MICRO SLAM Data encoded in longs
 **/
public class msg_msp_obstacle extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_MSP_OBSTACLE = 187;
  private static final long serialVersionUID = MAVLINK_MSG_ID_MSP_OBSTACLE;
  public msg_msp_obstacle() {
    this(1,1);
}
  public msg_msp_obstacle(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_MSP_OBSTACLE;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 26;
}

  /**
   * Timestamp
   */
  public long tms;
  /**
   * Distance
   */
  public float dm;
  /**
   * Obstacle X
   */
  public float ox;
  /**
   * Obstacle Y
   */
  public float oy;
  /**
   * Obstacle Z
   */
  public float oz;
  /**
   * Id
   */
  public int id;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  tms = (long)dis.readLong();
  dm = (float)dis.readFloat();
  ox = (float)dis.readFloat();
  oy = (float)dis.readFloat();
  oz = (float)dis.readFloat();
  id = (int)dis.readUnsignedShort()&0x00FFFF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+26];
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
  dos.writeFloat(dm);
  dos.writeFloat(ox);
  dos.writeFloat(oy);
  dos.writeFloat(oz);
  dos.writeShort(id&0x00FFFF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 26);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[36] = crcl;
  buffer[37] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_MSP_OBSTACLE : " +   "  tms="+tms
+  "  dm="+format((float)dm)
+  "  ox="+format((float)ox)
+  "  oy="+format((float)oy)
+  "  oz="+format((float)oz)
+  "  id="+id
;}

}

