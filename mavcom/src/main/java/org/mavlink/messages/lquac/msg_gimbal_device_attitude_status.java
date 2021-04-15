/**
 * Generated class : msg_gimbal_device_attitude_status
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
 * Class msg_gimbal_device_attitude_status
 * Message reporting the status of a gimbal device. This message should be broadcasted by a gimbal device component. The angles encoded in the quaternion are in the global frame (roll: positive is rolling to the right, pitch: positive is pitching up, yaw is turn to the right). This message should be broadcast at a low regular rate (e.g. 10Hz).
 **/
public class msg_gimbal_device_attitude_status extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS = 285;
  private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS;
  public msg_gimbal_device_attitude_status() {
    this(1,1);
}
  public msg_gimbal_device_attitude_status(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 40;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Quaternion components, w, x, y, z (1 0 0 0 is the null-rotation, the frame is depends on whether the flag GIMBAL_DEVICE_FLAGS_YAW_LOCK is set)
   */
  public float[] q = new float[4];
  /**
   * X component of angular velocity (NaN if unknown)
   */
  public float angular_velocity_x;
  /**
   * Y component of angular velocity (NaN if unknown)
   */
  public float angular_velocity_y;
  /**
   * Z component of angular velocity (NaN if unknown)
   */
  public float angular_velocity_z;
  /**
   * Failure flags (0 for no failure)
   */
  public long failure_flags;
  /**
   * Current gimbal flags set.
   */
  public int flags;
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
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  for (int i=0; i<4; i++) {
    q[i] = (float)dis.readFloat();
  }
  angular_velocity_x = (float)dis.readFloat();
  angular_velocity_y = (float)dis.readFloat();
  angular_velocity_z = (float)dis.readFloat();
  failure_flags = (int)dis.readInt()&0x00FFFFFFFF;
  flags = (int)dis.readUnsignedShort()&0x00FFFF;
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+40];
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
  for (int i=0; i<4; i++) {
    dos.writeFloat(q[i]);
  }
  dos.writeFloat(angular_velocity_x);
  dos.writeFloat(angular_velocity_y);
  dos.writeFloat(angular_velocity_z);
  dos.writeInt((int)(failure_flags&0x00FFFFFFFF));
  dos.writeShort(flags&0x00FFFF);
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 40);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[50] = crcl;
  buffer[51] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS : " +   "  time_boot_ms="+time_boot_ms
+  "  q[0]="+format((float)q[0])
+  "  q[1]="+format((float)q[1])
+  "  q[2]="+format((float)q[2])
+  "  q[3]="+format((float)q[3])
+  "  angular_velocity_x="+format((float)angular_velocity_x)
+  "  angular_velocity_y="+format((float)angular_velocity_y)
+  "  angular_velocity_z="+format((float)angular_velocity_z)
+  "  failure_flags="+failure_flags
+  "  flags="+flags
+  "  target_system="+target_system
+  "  target_component="+target_component
;}

}

