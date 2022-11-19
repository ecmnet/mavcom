/**
 * Generated class : msg_gimbal_device_set_attitude
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
 * Class msg_gimbal_device_set_attitude
 * Low level message to control a gimbal device's attitude. This message is to be sent from the gimbal manager to the gimbal device component. Angles and rates can be set to NaN according to use case.
 **/
public class msg_gimbal_device_set_attitude extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_GIMBAL_DEVICE_SET_ATTITUDE = 284;
  private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_DEVICE_SET_ATTITUDE;
  public msg_gimbal_device_set_attitude() {
    this(1,1);
}
  public msg_gimbal_device_set_attitude(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_GIMBAL_DEVICE_SET_ATTITUDE;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 32;
}

  /**
   * Quaternion components, w, x, y, z (1 0 0 0 is the null-rotation). The frame depends on whether the flag GIMBAL_DEVICE_FLAGS_YAW_LOCK is set. Set fields to NaN to be ignored (only angular velocity should be used).
   */
  public float[] q = new float[4];
  /**
   * X component of angular velocity (positive: rolling to the right). NaN to be ignored.
   */
  public float angular_velocity_x;
  /**
   * Y component of angular velocity (positive: pitching up). NaN to be ignored.
   */
  public float angular_velocity_y;
  /**
   * Z component of angular velocity (positive: yawing to the right). NaN to be ignored.
   */
  public float angular_velocity_z;
  /**
   * Low level gimbal flags.
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
  for (int i=0; i<4; i++) {
    q[i] = (float)dis.readFloat();
  }
  angular_velocity_x = (float)dis.readFloat();
  angular_velocity_y = (float)dis.readFloat();
  angular_velocity_z = (float)dis.readFloat();
  flags = (int)dis.readUnsignedShort()&0x00FFFF;
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+32];
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
  for (int i=0; i<4; i++) {
    dos.writeFloat(q[i]);
  }
  dos.writeFloat(angular_velocity_x);
  dos.writeFloat(angular_velocity_y);
  dos.writeFloat(angular_velocity_z);
  dos.writeShort(flags&0x00FFFF);
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 32);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[42] = crcl;
  buffer[43] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_GIMBAL_DEVICE_SET_ATTITUDE : " +   "  q[0]="+format((float)q[0])
+  "  q[1]="+format((float)q[1])
+  "  q[2]="+format((float)q[2])
+  "  q[3]="+format((float)q[3])
+  "  angular_velocity_x="+format((float)angular_velocity_x)
+  "  angular_velocity_y="+format((float)angular_velocity_y)
+  "  angular_velocity_z="+format((float)angular_velocity_z)
+  "  flags="+flags
+  "  target_system="+target_system
+  "  target_component="+target_component
;}

}

