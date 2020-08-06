/**
 * Generated class : msg_param_v3_ack
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
 * Class msg_param_v3_ack
 * Response from a PARAM_V3_SET message.
 **/
public class msg_param_v3_ack extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_PARAM_V3_ACK = 327;
  private static final long serialVersionUID = MAVLINK_MSG_ID_PARAM_V3_ACK;
  public msg_param_v3_ack() {
    this(1,1);
}
  public msg_param_v3_ack(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_PARAM_V3_ACK;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 146;
}

  /**
   * Parameter id, terminated by NULL if the length is less than 16 human-readable chars and WITHOUT null termination (NULL) byte if the length is exactly 16 chars - applications have to provide 16+1 bytes storage if the ID is stored as string
   */
  public char[] param_id = new char[16];
  public void setParam_id(String tmp) {
    int len = Math.min(tmp.length(), 16);
    for (int i=0; i<len; i++) {
      param_id[i] = tmp.charAt(i);
    }
    for (int i=len; i<16; i++) {
      param_id[i] = 0;
    }
  }
  public String getParam_id() {
    String result="";
    for (int i=0; i<16; i++) {
      if (param_id[i] != 0) result=result+param_id[i]; else break;
    }
    return result;
  }
  /**
   * Parameter type.
   */
  public int param_type;
  /**
   * Result code.
   */
  public int param_result;
  /**
   * Parameter value (new value if PARAM_ACK_ACCEPTED, current value otherwise, zeros get trimmed)
   */
  public char[] param_value = new char[128];
  public void setParam_value(String tmp) {
    int len = Math.min(tmp.length(), 128);
    for (int i=0; i<len; i++) {
      param_value[i] = tmp.charAt(i);
    }
    for (int i=len; i<128; i++) {
      param_value[i] = 0;
    }
  }
  public String getParam_value() {
    String result="";
    for (int i=0; i<128; i++) {
      if (param_value[i] != 0) result=result+param_value[i]; else break;
    }
    return result;
  }
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  for (int i=0; i<16; i++) {
    param_id[i] = (char)dis.readByte();
  }
  param_type = (int)dis.readUnsignedByte()&0x00FF;
  param_result = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<128; i++) {
    param_value[i] = (char)dis.readByte();
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+146];
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
  for (int i=0; i<16; i++) {
    dos.writeByte(param_id[i]);
  }
  dos.writeByte(param_type&0x00FF);
  dos.writeByte(param_result&0x00FF);
  for (int i=0; i<128; i++) {
    dos.writeByte(param_value[i]);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 146);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[156] = crcl;
  buffer[157] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_PARAM_V3_ACK : " +   "  param_id="+getParam_id()
+  "  param_type="+param_type
+  "  param_result="+param_result
+  "  param_value="+getParam_value()
;}
}
