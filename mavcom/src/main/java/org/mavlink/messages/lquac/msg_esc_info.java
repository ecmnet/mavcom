/**
 * Generated class : msg_esc_info
 * DO NOT MODIFY!
 **/
package org.mavlink.messages.lquac;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.mavlink.IMAVLinkCRC;
import org.mavlink.MAVLinkCRC;
import org.mavlink.io.LittleEndianDataInputStream;
import org.mavlink.io.LittleEndianDataOutputStream;
import org.mavlink.messages.MAVLinkMessage;
/**
 * Class msg_esc_info
 * ESC information for lower rate streaming. Recommended streaming rate 1Hz. See ESC_STATUS for higher-rate ESC data.
 **/
public class msg_esc_info extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_ESC_INFO = 290;
  private static final long serialVersionUID = MAVLINK_MSG_ID_ESC_INFO;
  public msg_esc_info() {
    this(1,1);
}
  public msg_esc_info(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_ESC_INFO;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 42;
}

  /**
   * Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude the number.
   */
  public long time_usec;
  /**
   * Number of reported errors by each ESC since boot.
   */
  public long[] error_count = new long[4];
  /**
   * Counter of data packets received.
   */
  public int counter;
  /**
   * Bitmap of ESC failure flags.
   */
  public int[] failure_flags = new int[4];
  /**
   * Index of the first ESC in this message. minValue = 0, maxValue = 60, increment = 4.
   */
  public int index;
  /**
   * Total number of ESCs in all messages of this type. Message fields with an index higher than this should be ignored because they contain invalid data.
   */
  public int count;
  /**
   * Connection type protocol for all ESC.
   */
  public int connection_type;
  /**
   * Information regarding online/offline status of each ESC.
   */
  public int info;
  /**
   * Temperature measured by each ESC. UINT8_MAX if data not supplied by ESC.
   */
  public int[] temperature = new int[4];
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_usec = (long)dis.readLong();
  for (int i=0; i<4; i++) {
    error_count[i] = (int)dis.readInt()&0x00FFFFFFFF;
  }
  counter = (int)dis.readUnsignedShort()&0x00FFFF;
  for (int i=0; i<4; i++) {
    failure_flags[i] = (int)dis.readUnsignedShort()&0x00FFFF;
  }
  index = (int)dis.readUnsignedByte()&0x00FF;
  count = (int)dis.readUnsignedByte()&0x00FF;
  connection_type = (int)dis.readUnsignedByte()&0x00FF;
  info = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<4; i++) {
    temperature[i] = (int)dis.readUnsignedByte()&0x00FF;
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+42];
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
  for (int i=0; i<4; i++) {
    dos.writeInt((int)(error_count[i]&0x00FFFFFFFF));
  }
  dos.writeShort(counter&0x00FFFF);
  for (int i=0; i<4; i++) {
    dos.writeShort(failure_flags[i]&0x00FFFF);
  }
  dos.writeByte(index&0x00FF);
  dos.writeByte(count&0x00FF);
  dos.writeByte(connection_type&0x00FF);
  dos.writeByte(info&0x00FF);
  for (int i=0; i<4; i++) {
    dos.writeByte(temperature[i]&0x00FF);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 42);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[52] = crcl;
  buffer[53] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_ESC_INFO : " +   "  time_usec="+time_usec
+  "  error_count[0]="+error_count[0]
+  "  error_count[1]="+error_count[1]
+  "  error_count[2]="+error_count[2]
+  "  error_count[3]="+error_count[3]
+  "  counter="+counter
+  "  failure_flags[0]="+failure_flags[0]
+  "  failure_flags[1]="+failure_flags[1]
+  "  failure_flags[2]="+failure_flags[2]
+  "  failure_flags[3]="+failure_flags[3]
+  "  index="+index
+  "  count="+count
+  "  connection_type="+connection_type
+  "  info="+info
+  "  temperature[0]="+temperature[0]
+  "  temperature[1]="+temperature[1]
+  "  temperature[2]="+temperature[2]
+  "  temperature[3]="+temperature[3]
;}
}
