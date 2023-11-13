/**
 * Generated class : msg_gimbal_manager_status
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
 * Class msg_gimbal_manager_status
 * Current status about a high level gimbal manager. This message should be broadcast at a low regular rate (e.g. 5Hz).
 **/
public class msg_gimbal_manager_status extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_GIMBAL_MANAGER_STATUS = 281;
  private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_MANAGER_STATUS;
  public msg_gimbal_manager_status() {
    this(1,1);
}
  public msg_gimbal_manager_status(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_GIMBAL_MANAGER_STATUS;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 13;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * High level gimbal manager flags currently applied.
   */
  public long flags;
  /**
   * Gimbal device ID that this gimbal manager is responsible for. Component ID of gimbal device (or 1-6 for non-MAVLink gimbal).
   */
  public int gimbal_device_id;
  /**
   * System ID of MAVLink component with primary control, 0 for none.
   */
  public int primary_control_sysid;
  /**
   * Component ID of MAVLink component with primary control, 0 for none.
   */
  public int primary_control_compid;
  /**
   * System ID of MAVLink component with secondary control, 0 for none.
   */
  public int secondary_control_sysid;
  /**
   * Component ID of MAVLink component with secondary control, 0 for none.
   */
  public int secondary_control_compid;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  flags = (int)dis.readInt()&0x00FFFFFFFF;
  gimbal_device_id = (int)dis.readUnsignedByte()&0x00FF;
  primary_control_sysid = (int)dis.readUnsignedByte()&0x00FF;
  primary_control_compid = (int)dis.readUnsignedByte()&0x00FF;
  secondary_control_sysid = (int)dis.readUnsignedByte()&0x00FF;
  secondary_control_compid = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+13];
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
  dos.writeInt((int)(time_boot_ms&0x00FFFFFFFF));
  dos.writeInt((int)(flags&0x00FFFFFFFF));
  dos.writeByte(gimbal_device_id&0x00FF);
  dos.writeByte(primary_control_sysid&0x00FF);
  dos.writeByte(primary_control_compid&0x00FF);
  dos.writeByte(secondary_control_sysid&0x00FF);
  dos.writeByte(secondary_control_compid&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 13);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[23] = crcl;
  buffer[24] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_GIMBAL_MANAGER_STATUS : " +   "  time_boot_ms="+time_boot_ms
+  "  flags="+flags
+  "  gimbal_device_id="+gimbal_device_id
+  "  primary_control_sysid="+primary_control_sysid
+  "  primary_control_compid="+primary_control_compid
+  "  secondary_control_sysid="+secondary_control_sysid
+  "  secondary_control_compid="+secondary_control_compid
;}

}

