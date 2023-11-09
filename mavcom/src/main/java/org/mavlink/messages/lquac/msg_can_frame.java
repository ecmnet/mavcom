/**
 * Generated class : msg_can_frame
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
 * Class msg_can_frame
 * A forwarded CAN frame as requested by MAV_CMD_CAN_FORWARD.
 **/
public class msg_can_frame extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_CAN_FRAME = 386;
  private static final long serialVersionUID = MAVLINK_MSG_ID_CAN_FRAME;
  public msg_can_frame() {
    this(1,1);
}
  public msg_can_frame(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_CAN_FRAME;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 16;
}

  /**
   * Frame ID
   */
  public long id;
  /**
   * System ID.
   */
  public int target_system;
  /**
   * Component ID.
   */
  public int target_component;
  /**
   * Bus number
   */
  public int bus;
  /**
   * Frame length
   */
  public int len;
  /**
   * Frame data
   */
  public int[] data = new int[8];
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  id = (int)dis.readInt()&0x00FFFFFFFF;
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
  bus = (int)dis.readUnsignedByte()&0x00FF;
  len = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<8; i++) {
    data[i] = (int)dis.readUnsignedByte()&0x00FF;
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+16];
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
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.writeByte(bus&0x00FF);
  dos.writeByte(len&0x00FF);
  for (int i=0; i<8; i++) {
    dos.writeByte(data[i]&0x00FF);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 16);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[26] = crcl;
  buffer[27] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_CAN_FRAME : " +   "  id="+id
+  "  target_system="+target_system
+  "  target_component="+target_component
+  "  bus="+bus
+  "  len="+len
+  "  data="+data
;}

}

