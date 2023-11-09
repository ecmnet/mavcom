/**
 * Generated class : msg_response_event_error
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
 * Class msg_response_event_error
 * Response to a REQUEST_EVENT in case of an error (e.g. the event is not available anymore).
 **/
public class msg_response_event_error extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR = 413;
  private static final long serialVersionUID = MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR;
  public msg_response_event_error() {
    this(1,1);
}
  public msg_response_event_error(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 7;
}

  /**
   * Sequence number.
   */
  public int sequence;
  /**
   * Oldest Sequence number that is still available after the sequence set in REQUEST_EVENT.
   */
  public int sequence_oldest_available;
  /**
   * System ID
   */
  public int target_system;
  /**
   * Component ID
   */
  public int target_component;
  /**
   * Error reason.
   */
  public int reason;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  sequence = (int)dis.readUnsignedShort()&0x00FFFF;
  sequence_oldest_available = (int)dis.readUnsignedShort()&0x00FFFF;
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
  reason = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+7];
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
  dos.writeShort(sequence&0x00FFFF);
  dos.writeShort(sequence_oldest_available&0x00FFFF);
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.writeByte(reason&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 7);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[17] = crcl;
  buffer[18] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR : " +   "  sequence="+sequence
+  "  sequence_oldest_available="+sequence_oldest_available
+  "  target_system="+target_system
+  "  target_component="+target_component
+  "  reason="+reason
;}

}

