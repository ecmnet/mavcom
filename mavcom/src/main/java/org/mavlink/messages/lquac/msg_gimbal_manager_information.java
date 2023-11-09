/**
 * Generated class : msg_gimbal_manager_information
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
 * Class msg_gimbal_manager_information
 * Information about a high level gimbal manager. This message should be requested by a ground station using MAV_CMD_REQUEST_MESSAGE.
 **/
public class msg_gimbal_manager_information extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_GIMBAL_MANAGER_INFORMATION = 280;
  private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_MANAGER_INFORMATION;
  public msg_gimbal_manager_information() {
    this(1,1);
}
  public msg_gimbal_manager_information(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_GIMBAL_MANAGER_INFORMATION;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 33;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Bitmap of gimbal capability flags.
   */
  public long cap_flags;
  /**
   * Minimum hardware roll angle (positive: rolling to the right, negative: rolling to the left)
   */
  public float roll_min;
  /**
   * Maximum hardware roll angle (positive: rolling to the right, negative: rolling to the left)
   */
  public float roll_max;
  /**
   * Minimum pitch angle (positive: up, negative: down)
   */
  public float pitch_min;
  /**
   * Maximum pitch angle (positive: up, negative: down)
   */
  public float pitch_max;
  /**
   * Minimum yaw angle (positive: to the right, negative: to the left)
   */
  public float yaw_min;
  /**
   * Maximum yaw angle (positive: to the right, negative: to the left)
   */
  public float yaw_max;
  /**
   * Gimbal device ID that this gimbal manager is responsible for. Component ID of gimbal device (or 1-6 for non-MAVLink gimbal).
   */
  public int gimbal_device_id;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  cap_flags = (int)dis.readInt()&0x00FFFFFFFF;
  roll_min = (float)dis.readFloat();
  roll_max = (float)dis.readFloat();
  pitch_min = (float)dis.readFloat();
  pitch_max = (float)dis.readFloat();
  yaw_min = (float)dis.readFloat();
  yaw_max = (float)dis.readFloat();
  gimbal_device_id = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+33];
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
  dos.writeInt((int)(time_boot_ms&0x00FFFFFFFF));
  dos.writeInt((int)(cap_flags&0x00FFFFFFFF));
  dos.writeFloat(roll_min);
  dos.writeFloat(roll_max);
  dos.writeFloat(pitch_min);
  dos.writeFloat(pitch_max);
  dos.writeFloat(yaw_min);
  dos.writeFloat(yaw_max);
  dos.writeByte(gimbal_device_id&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 33);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[43] = crcl;
  buffer[44] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_GIMBAL_MANAGER_INFORMATION : " +   "  time_boot_ms="+time_boot_ms
+  "  cap_flags="+cap_flags
+  "  roll_min="+format((float)roll_min)
+  "  roll_max="+format((float)roll_max)
+  "  pitch_min="+format((float)pitch_min)
+  "  pitch_max="+format((float)pitch_max)
+  "  yaw_min="+format((float)yaw_min)
+  "  yaw_max="+format((float)yaw_max)
+  "  gimbal_device_id="+gimbal_device_id
;}

}

