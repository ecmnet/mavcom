/**
 * Generated class : msg_gimbal_manager_set_attitude
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
 * Class msg_gimbal_manager_set_attitude
 * High level message to control a gimbal's attitude. This message is to be sent to the gimbal manager (e.g. from a ground station). Angles and rates can be set to NaN according to use case.
 **/
public class msg_gimbal_manager_set_attitude extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_GIMBAL_MANAGER_SET_ATTITUDE = 282;
  private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_MANAGER_SET_ATTITUDE;
  public msg_gimbal_manager_set_attitude() {
    this(1,1);
}
  public msg_gimbal_manager_set_attitude(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_GIMBAL_MANAGER_SET_ATTITUDE;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 35;
}

  /**
   * High level gimbal manager flags to use.
   */
  public long flags;
  /**
   * Quaternion components, w, x, y, z (1 0 0 0 is the null-rotation, the frame is depends on whether the flag GIMBAL_MANAGER_FLAGS_YAW_LOCK is set)
   */
  public float[] q = new float[4];
  /**
   * X component of angular velocity, positive is rolling to the right, NaN to be ignored.
   */
  public float angular_velocity_x;
  /**
   * Y component of angular velocity, positive is pitching up, NaN to be ignored.
   */
  public float angular_velocity_y;
  /**
   * Z component of angular velocity, positive is yawing to the right, NaN to be ignored.
   */
  public float angular_velocity_z;
  /**
   * System ID
   */
  public int target_system;
  /**
   * Component ID
   */
  public int target_component;
  /**
   * Component ID of gimbal device to address (or 1-6 for non-MAVLink gimbal), 0 for all gimbal device components. Send command multiple times for more than one gimbal (but not all gimbals).
   */
  public int gimbal_device_id;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  flags = (int)dis.readInt()&0x00FFFFFFFF;
  for (int i=0; i<4; i++) {
    q[i] = (float)dis.readFloat();
  }
  angular_velocity_x = (float)dis.readFloat();
  angular_velocity_y = (float)dis.readFloat();
  angular_velocity_z = (float)dis.readFloat();
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
  gimbal_device_id = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+35];
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
  dos.writeInt((int)(flags&0x00FFFFFFFF));
  for (int i=0; i<4; i++) {
    dos.writeFloat(q[i]);
  }
  dos.writeFloat(angular_velocity_x);
  dos.writeFloat(angular_velocity_y);
  dos.writeFloat(angular_velocity_z);
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.writeByte(gimbal_device_id&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 35);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[45] = crcl;
  buffer[46] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_GIMBAL_MANAGER_SET_ATTITUDE : " +   "  flags="+flags
+  "  q[0]="+format((float)q[0])
+  "  q[1]="+format((float)q[1])
+  "  q[2]="+format((float)q[2])
+  "  q[3]="+format((float)q[3])
+  "  angular_velocity_x="+format((float)angular_velocity_x)
+  "  angular_velocity_y="+format((float)angular_velocity_y)
+  "  angular_velocity_z="+format((float)angular_velocity_z)
+  "  target_system="+target_system
+  "  target_component="+target_component
+  "  gimbal_device_id="+gimbal_device_id
;}
}
