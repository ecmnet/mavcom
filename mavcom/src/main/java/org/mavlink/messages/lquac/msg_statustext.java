/**
 * Generated class : msg_statustext
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
 * Class msg_statustext
 * Status text message. These messages are printed in yellow in the COMM console of QGroundControl. WARNING: They consume quite some bandwidth, so use only for important status and error messages. If implemented wisely, these messages are buffered on the MCU and sent only at a limited rate (e.g. 10 Hz).
 **/
public class msg_statustext extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_STATUSTEXT = 253;
  private static final long serialVersionUID = MAVLINK_MSG_ID_STATUSTEXT;
  public msg_statustext() {
    this(1,1);
}
  public msg_statustext(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_STATUSTEXT;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 54;
}

  /**
   * Severity of status. Relies on the definitions within RFC-5424.
   */
  public int severity;
  /**
   * Status text message, without null termination character
   */
  public char[] text = new char[50];
  public void setText(String tmp) {
    int len = Math.min(tmp.length(), 50);
    for (int i=0; i<len; i++) {
      text[i] = tmp.charAt(i);
    }
    for (int i=len; i<50; i++) {
      text[i] = 0;
    }
  }
  public String getText() {
    String result="";
    for (int i=0; i<50; i++) {
      if (text[i] != 0) result=result+text[i]; else break;
    }
    return result;
  }
  /**
   * Unique (opaque) identifier for this statustext message.  May be used to reassemble a logical long-statustext message from a sequence of chunks.  A value of zero indicates this is the only chunk in the sequence and the message can be emitted immediately.
   */
  public int id;
  /**
   * This chunk's sequence number; indexing is from zero.  Any null character in the text field is taken to mean this was the last chunk.
   */
  public int chunk_seq;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  severity = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<50; i++) {
    text[i] = (char)dis.readByte();
  }
  id = (int)dis.readUnsignedShort()&0x00FFFF;
  chunk_seq = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+54];
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
  dos.writeByte(severity&0x00FF);
  for (int i=0; i<50; i++) {
    dos.writeByte(text[i]);
  }
  dos.writeShort(id&0x00FFFF);
  dos.writeByte(chunk_seq&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 54);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[64] = crcl;
  buffer[65] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_STATUSTEXT : " +   "  severity="+severity
+  "  text="+getText()
+  "  id="+id
+  "  chunk_seq="+chunk_seq
;}

public int getId() {
 return MAVLINK_MSG_ID_STATUSTEXT;
}

}

