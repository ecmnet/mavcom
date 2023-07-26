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
 * Message reporting the status of a gimbal device.
	  This message should be broadcast by a gimbal device component at a low regular rate (e.g. 5 Hz).
	  For the angles encoded in the quaternion and the angular velocities holds:
	  If the flag GIMBAL_DEVICE_FLAGS_YAW_IN_VEHICLE_FRAME is set, then they are relative to the vehicle heading (vehicle frame).
	  If the flag GIMBAL_DEVICE_FLAGS_YAW_IN_EARTH_FRAME is set, then they are relative to absolute North (earth frame).
	  If neither of these flags are set, then (for backwards compatibility) it holds:
	  If the flag GIMBAL_DEVICE_FLAGS_YAW_LOCK is set, then they are relative to absolute North (earth frame),
	  else they are relative to the vehicle heading (vehicle frame).
	  Other conditions of the flags are not allowed.
	  The quaternion and angular velocities in the other frame can be calculated from delta_yaw and delta_yaw_velocity as
	  q_earth = q_delta_yaw * q_vehicle and w_earth = w_delta_yaw_velocity + w_vehicle (if not NaN).
	  If neither the GIMBAL_DEVICE_FLAGS_YAW_IN_VEHICLE_FRAME nor the GIMBAL_DEVICE_FLAGS_YAW_IN_EARTH_FRAME flag is set,
	  then (for backwards compatibility) the data in the delta_yaw and delta_yaw_velocity fields are to be ignored.
	  New implementations should always set either GIMBAL_DEVICE_FLAGS_YAW_IN_VEHICLE_FRAME or GIMBAL_DEVICE_FLAGS_YAW_IN_EARTH_FRAME,
	  and always should set delta_yaw and delta_yaw_velocity either to the proper value or NaN.
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
    payload_length = 49;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Quaternion components, w, x, y, z (1 0 0 0 is the null-rotation). The frame is described in the message description.
   */
  public float[] q = new float[4];
  /**
   * X component of angular velocity (positive: rolling to the right). The frame is described in the message description. NaN if unknown.
   */
  public float angular_velocity_x;
  /**
   * Y component of angular velocity (positive: pitching up). The frame is described in the message description. NaN if unknown.
   */
  public float angular_velocity_y;
  /**
   * Z component of angular velocity (positive: yawing to the right). The frame is described in the message description. NaN if unknown.
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
   * Yaw angle relating the quaternions in earth and body frames (see message description). NaN if unknown.
   */
  public float delta_yaw;
  /**
   * Yaw angular velocity relating the angular velocities in earth and body frames (see message description). NaN if unknown.
   */
  public float delta_yaw_velocity;
  /**
   * This field is to be used if the gimbal manager and the gimbal device are the same component and hence have the same component ID. This field is then set a number between 1-6. If the component ID is separate, this field is not required and must be set to 0.
   */
  public int gimbal_device_id;
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
  delta_yaw = (float)dis.readFloat();
  delta_yaw_velocity = (float)dis.readFloat();
  gimbal_device_id = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+49];
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
  dos.writeFloat(delta_yaw);
  dos.writeFloat(delta_yaw_velocity);
  dos.writeByte(gimbal_device_id&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 49);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[59] = crcl;
  buffer[60] = crch;
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
+  "  delta_yaw="+format((float)delta_yaw)
+  "  delta_yaw_velocity="+format((float)delta_yaw_velocity)
+  "  gimbal_device_id="+gimbal_device_id
;}

}

