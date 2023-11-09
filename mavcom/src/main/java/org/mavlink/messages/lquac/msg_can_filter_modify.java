/**
 * Generated class : msg_can_filter_modify
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
 * Class msg_can_filter_modify
 * Modify the filter of what CAN messages to forward over the mavlink. This can be used to make CAN forwarding work well on low bandwidth links. The filtering is applied on bits 8 to 24 of the CAN id (2nd and 3rd bytes) which corresponds to the DroneCAN message ID for DroneCAN. Filters with more than 16 IDs can be constructed by sending multiple CAN_FILTER_MODIFY messages.
 **/
public class msg_can_filter_modify extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_CAN_FILTER_MODIFY = 388;
  private static final long serialVersionUID = MAVLINK_MSG_ID_CAN_FILTER_MODIFY;
  public msg_can_filter_modify() {
    this(1,1);
}
  public msg_can_filter_modify(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_CAN_FILTER_MODIFY;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 37;
}

  /**
   * filter IDs, length num_ids
   */
  public int[] ids = new int[16];
  /**
   * System ID.
   */
  public int target_system;
  /**
   * Component ID.
   */
  public int target_component;
  /**
   * bus number
   */
  public int bus;
  /**
   * what operation to perform on the filter list. See CAN_FILTER_OP enum.
   */
  public int operation;
  /**
   * number of IDs in filter list
   */
  public int num_ids;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  for (int i=0; i<16; i++) {
    ids[i] = (int)dis.readUnsignedShort()&0x00FFFF;
  }
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
  bus = (int)dis.readUnsignedByte()&0x00FF;
  operation = (int)dis.readUnsignedByte()&0x00FF;
  num_ids = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+37];
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
  for (int i=0; i<16; i++) {
    dos.writeShort(ids[i]&0x00FFFF);
  }
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.writeByte(bus&0x00FF);
  dos.writeByte(operation&0x00FF);
  dos.writeByte(num_ids&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 37);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[47] = crcl;
  buffer[48] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_CAN_FILTER_MODIFY : " +   "  ids="+ids
+  "  target_system="+target_system
+  "  target_component="+target_component
+  "  bus="+bus
+  "  operation="+operation
+  "  num_ids="+num_ids
;}

}

