/**
 * Generated class : msg_raw_rpm
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
 * Class msg_raw_rpm
 * RPM sensor data message.
 **/
public class msg_raw_rpm extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_RAW_RPM = 339;
  private static final long serialVersionUID = MAVLINK_MSG_ID_RAW_RPM;
  public msg_raw_rpm() {
    this(1,1);
}
  public msg_raw_rpm(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_RAW_RPM;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 5;
}

  /**
   * Indicated rate
   */
  public float frequency;
  /**
   * Index of this RPM sensor (0-indexed)
   */
  public int index;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  frequency = (float)dis.readFloat();
  index = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+5];
   LittleEndianDataOutputStream dos = new LittleEndianDataOutputStream(new ByteArrayOutputStream());
  dos.writeByte((byte)0xFD);
  dos.writeByte(payload_length & 0x00FF);
  dos.writeByte(incompat & 0x00FF);
  dos.writeByte(compat & 0x00FF);
  dos.writeByte(packet & 0x00FF);
  dos.writeByte(sysId & 0x007F);
  dos.writeByte(componentId & 0x007F);
  dos.writeByte(messageType & 0x00FF);
  dos.writeByte((messageType >> 8) & 0x00FF);
  dos.writeByte((messageType >> 16) & 0x00FF);
  dos.writeFloat(frequency);
  dos.writeByte(index&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 5);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[15] = crcl;
  buffer[16] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_RAW_RPM : " +   "  frequency="+format((float)frequency)
+  "  index="+index
;}

}

