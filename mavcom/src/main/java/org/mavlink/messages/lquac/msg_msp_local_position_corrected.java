/**
 * Generated class : msg_msp_local_position_corrected
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
 * Class msg_msp_local_position_corrected
 * Corrected local position
 **/
public class msg_msp_local_position_corrected extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_MSP_LOCAL_POSITION_CORRECTED = 186;
  private static final long serialVersionUID = MAVLINK_MSG_ID_MSP_LOCAL_POSITION_CORRECTED;
  public msg_msp_local_position_corrected() {
    this(1,1);
}
  public msg_msp_local_position_corrected(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_MSP_LOCAL_POSITION_CORRECTED;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 34;
}

  /**
   * Timestamp
   */
  public long tms;
  /**
   * Corrected LPosX
   */
  public float cx;
  /**
   * Corrected LPosY
   */
  public float cy;
  /**
   * Corrected LPosZ
   */
  public float cz;
  /**
   * X GroundTruth
   */
  public float gx;
  /**
   * Y GroundTruth
   */
  public float gy;
  /**
   * Z GroundTruth
   */
  public float gz;
  /**
   * ResetCounter
   */
  public int counter;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  tms = (long)dis.readLong();
  cx = (float)dis.readFloat();
  cy = (float)dis.readFloat();
  cz = (float)dis.readFloat();
  gx = (float)dis.readFloat();
  gy = (float)dis.readFloat();
  gz = (float)dis.readFloat();
  counter = (int)dis.readUnsignedShort()&0x00FFFF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+34];
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
  dos.writeFloat(cx);
  dos.writeFloat(cy);
  dos.writeFloat(cz);
  dos.writeFloat(gx);
  dos.writeFloat(gy);
  dos.writeFloat(gz);
  dos.writeShort(counter&0x00FFFF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 34);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[44] = crcl;
  buffer[45] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_MSP_LOCAL_POSITION_CORRECTED : " +   "  tms="+tms
+  "  cx="+format((float)cx)
+  "  cy="+format((float)cy)
+  "  cz="+format((float)cz)
+  "  gx="+format((float)gx)
+  "  gy="+format((float)gy)
+  "  gz="+format((float)gz)
+  "  counter="+counter
;}

}

