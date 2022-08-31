/**
 * Generated class : msg_mission_current
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
 * Class msg_mission_current
 * Message that announces the sequence number of the current target mission item (that the system will fly towards/execute when the mission is running).
        This message should be streamed all the time (nominally at 1Hz).
        This message should be emitted following a call to MAV_CMD_DO_SET_MISSION_CURRENT or SET_MISSION_CURRENT.
 **/
public class msg_mission_current extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_MISSION_CURRENT = 42;
  private static final long serialVersionUID = MAVLINK_MSG_ID_MISSION_CURRENT;
  public msg_mission_current() {
    this(1,1);
}
  public msg_mission_current(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_MISSION_CURRENT;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 6;
}

  /**
   * Sequence
   */
  public int seq;
  /**
   * Total number of mission items. 0: Not supported, UINT16_MAX if no mission is present on the vehicle.
   */
  public int total;
  /**
   * Mission state machine state. MISSION_STATE_UNKNOWN if state reporting not supported.
   */
  public int mission_state;
  /**
   * Vehicle is in a mode that can execute mission items or suspended. 0: Unknown, 1: In mission mode, 2: Suspended (not in mission mode).
   */
  public int mission_mode;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  seq = (int)dis.readUnsignedShort()&0x00FFFF;
  total = (int)dis.readUnsignedShort()&0x00FFFF;
  mission_state = (int)dis.readUnsignedByte()&0x00FF;
  mission_mode = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+6];
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
  dos.writeShort(seq&0x00FFFF);
  dos.writeShort(total&0x00FFFF);
  dos.writeByte(mission_state&0x00FF);
  dos.writeByte(mission_mode&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 6);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[16] = crcl;
  buffer[17] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_MISSION_CURRENT : " +   "  seq="+seq
+  "  total="+total
+  "  mission_state="+mission_state
+  "  mission_mode="+mission_mode
;}

}

