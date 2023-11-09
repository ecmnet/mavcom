/**
 * Generated class : msg_event
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
 * Class msg_event
 * Event message. Each new event from a particular component gets a new sequence number. The same message might be sent multiple times if (re-)requested. Most events are broadcast, some can be specific to a target component (as receivers keep track of the sequence for missed events, all events need to be broadcast. Thus we use destination_component instead of target_component).
 **/
public class msg_event extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_EVENT = 410;
  private static final long serialVersionUID = MAVLINK_MSG_ID_EVENT;
  public msg_event() {
    this(1,1);
}
  public msg_event(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_EVENT;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 53;
}

  /**
   * Event ID (as defined in the component metadata)
   */
  public long id;
  /**
   * Timestamp (time since system boot when the event happened).
   */
  public long event_time_boot_ms;
  /**
   * Sequence number.
   */
  public int sequence;
  /**
   * Component ID
   */
  public int destination_component;
  /**
   * System ID
   */
  public int destination_system;
  /**
   * Log levels: 4 bits MSB: internal (for logging purposes), 4 bits LSB: external. Levels: Emergency = 0, Alert = 1, Critical = 2, Error = 3, Warning = 4, Notice = 5, Info = 6, Debug = 7, Protocol = 8, Disabled = 9
   */
  public int log_levels;
  /**
   * Arguments (depend on event ID).
   */
  public int[] arguments = new int[40];
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  id = (int)dis.readInt()&0x00FFFFFFFF;
  event_time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  sequence = (int)dis.readUnsignedShort()&0x00FFFF;
  destination_component = (int)dis.readUnsignedByte()&0x00FF;
  destination_system = (int)dis.readUnsignedByte()&0x00FF;
  log_levels = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<40; i++) {
    arguments[i] = (int)dis.readUnsignedByte()&0x00FF;
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+53];
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
  dos.writeInt((int)(id&0x00FFFFFFFF));
  dos.writeInt((int)(event_time_boot_ms&0x00FFFFFFFF));
  dos.writeShort(sequence&0x00FFFF);
  dos.writeByte(destination_component&0x00FF);
  dos.writeByte(destination_system&0x00FF);
  dos.writeByte(log_levels&0x00FF);
  for (int i=0; i<40; i++) {
    dos.writeByte(arguments[i]&0x00FF);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 53);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[63] = crcl;
  buffer[64] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_EVENT : " +   "  id="+id
+  "  event_time_boot_ms="+event_time_boot_ms
+  "  sequence="+sequence
+  "  destination_component="+destination_component
+  "  destination_system="+destination_system
+  "  log_levels="+log_levels
+  "  arguments[0]="+arguments[0]
+  "  arguments[1]="+arguments[1]
+  "  arguments[2]="+arguments[2]
+  "  arguments[3]="+arguments[3]
+  "  arguments[4]="+arguments[4]
+  "  arguments[5]="+arguments[5]
+  "  arguments[6]="+arguments[6]
+  "  arguments[7]="+arguments[7]
+  "  arguments[8]="+arguments[8]
+  "  arguments[9]="+arguments[9]
+  "  arguments[10]="+arguments[10]
+  "  arguments[11]="+arguments[11]
+  "  arguments[12]="+arguments[12]
+  "  arguments[13]="+arguments[13]
+  "  arguments[14]="+arguments[14]
+  "  arguments[15]="+arguments[15]
+  "  arguments[16]="+arguments[16]
+  "  arguments[17]="+arguments[17]
+  "  arguments[18]="+arguments[18]
+  "  arguments[19]="+arguments[19]
+  "  arguments[20]="+arguments[20]
+  "  arguments[21]="+arguments[21]
+  "  arguments[22]="+arguments[22]
+  "  arguments[23]="+arguments[23]
+  "  arguments[24]="+arguments[24]
+  "  arguments[25]="+arguments[25]
+  "  arguments[26]="+arguments[26]
+  "  arguments[27]="+arguments[27]
+  "  arguments[28]="+arguments[28]
+  "  arguments[29]="+arguments[29]
+  "  arguments[30]="+arguments[30]
+  "  arguments[31]="+arguments[31]
+  "  arguments[32]="+arguments[32]
+  "  arguments[33]="+arguments[33]
+  "  arguments[34]="+arguments[34]
+  "  arguments[35]="+arguments[35]
+  "  arguments[36]="+arguments[36]
+  "  arguments[37]="+arguments[37]
+  "  arguments[38]="+arguments[38]
+  "  arguments[39]="+arguments[39]
;}

}

