/**
 * Generated class : msg_mission_set_current
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
 * Class msg_mission_set_current
 * Set the mission item with sequence number seq as the current item and emit MISSION_CURRENT (whether or not the mission number changed).
        If a mission is currently being executed, the system will continue to this new mission item on the shortest path, skipping any intermediate mission items.
        Note that mission jump repeat counters are not reset (see MAV_CMD_DO_JUMP param2).

        This message may trigger a mission state-machine change on some systems: for example from MISSION_STATE_NOT_STARTED or MISSION_STATE_PAUSED to MISSION_STATE_ACTIVE.
        If the system is in mission mode, on those systems this command might therefore start, restart or resume the mission.
        If the system is not in mission mode this message must not trigger a switch to mission mode.
 **/
public class msg_mission_set_current extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_MISSION_SET_CURRENT = 41;
  private static final long serialVersionUID = MAVLINK_MSG_ID_MISSION_SET_CURRENT;
  public msg_mission_set_current() {
    this(1,1);
}
  public msg_mission_set_current(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_MISSION_SET_CURRENT;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 4;
}

  /**
   * Sequence
   */
  public int seq;
  /**
   * System ID
   */
  public int target_system;
  /**
   * Component ID
   */
  public int target_component;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  seq = (int)dis.readUnsignedShort()&0x00FFFF;
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+4];
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
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 4);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[14] = crcl;
  buffer[15] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_MISSION_SET_CURRENT : " +   "  seq="+seq
+  "  target_system="+target_system
+  "  target_component="+target_component
;}

}

