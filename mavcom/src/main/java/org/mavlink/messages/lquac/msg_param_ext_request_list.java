/**
 * Generated class : msg_param_ext_request_list
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
 * Class msg_param_ext_request_list
 * Request all parameters of this component. All parameters should be emitted in response (as PARAM_EXT_VALUE or PARAM_EXT_VALUE_TRIMMED messages - see field: trimmed).
 **/
public class msg_param_ext_request_list extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_PARAM_EXT_REQUEST_LIST = 321;
  private static final long serialVersionUID = MAVLINK_MSG_ID_PARAM_EXT_REQUEST_LIST;
  public msg_param_ext_request_list() {
    this(1,1);
}
  public msg_param_ext_request_list(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_PARAM_EXT_REQUEST_LIST;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 3;
}

  /**
   * System ID
   */
  public int target_system;
  /**
   * Component ID
   */
  public int target_component;
  /**
   * Request _TRIMMED variants of PARAM_EXT_ messages. Set to 1 if _TRIMMED message variants are supported, and 0 otherwise. This signals the recipient that _TRIMMED messages are supported by the sender (and should be used if supported by the recipient).
   */
  public int trimmed;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
  trimmed = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+3];
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
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.writeByte(trimmed&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 3);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[13] = crcl;
  buffer[14] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_PARAM_EXT_REQUEST_LIST : " +   "  target_system="+target_system
+  "  target_component="+target_component
+  "  trimmed="+trimmed
;}
}
