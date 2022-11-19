/**
 * Generated class : msg_autopilot_state_for_gimbal_device
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
 * Class msg_autopilot_state_for_gimbal_device
 * Low level message containing autopilot state relevant for a gimbal device. This message is to be sent from the autopilot to the gimbal device component. The data of this message are for the gimbal device's estimator corrections, in particular horizon compensation, as well as indicates autopilot control intentions, e.g. feed forward angular control in the z-axis.
 **/
public class msg_autopilot_state_for_gimbal_device extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_AUTOPILOT_STATE_FOR_GIMBAL_DEVICE = 286;
  private static final long serialVersionUID = MAVLINK_MSG_ID_AUTOPILOT_STATE_FOR_GIMBAL_DEVICE;
  public msg_autopilot_state_for_gimbal_device() {
    this(1,1);
}
  public msg_autopilot_state_for_gimbal_device(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_AUTOPILOT_STATE_FOR_GIMBAL_DEVICE;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 53;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_us;
  /**
   * Quaternion components of autopilot attitude: w, x, y, z (1 0 0 0 is the null-rotation, Hamilton convention).
   */
  public float[] q = new float[4];
  /**
   * Estimated delay of the attitude data.
   */
  public long q_estimated_delay_us;
  /**
   * X Speed in NED (North, East, Down).
   */
  public float vx;
  /**
   * Y Speed in NED (North, East, Down).
   */
  public float vy;
  /**
   * Z Speed in NED (North, East, Down).
   */
  public float vz;
  /**
   * Estimated delay of the speed data.
   */
  public long v_estimated_delay_us;
  /**
   * Feed forward Z component of angular velocity (positive: yawing to the right). NaN to be ignored. This is to indicate if the autopilot is actively yawing.
   */
  public float feed_forward_angular_velocity_z;
  /**
   * Bitmap indicating which estimator outputs are valid.
   */
  public int estimator_status;
  /**
   * System ID
   */
  public int target_system;
  /**
   * Component ID
   */
  public int target_component;
  /**
   * The landed state. Is set to MAV_LANDED_STATE_UNDEFINED if landed state is unknown.
   */
  public int landed_state;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_us = (long)dis.readLong();
  for (int i=0; i<4; i++) {
    q[i] = (float)dis.readFloat();
  }
  q_estimated_delay_us = (int)dis.readInt()&0x00FFFFFFFF;
  vx = (float)dis.readFloat();
  vy = (float)dis.readFloat();
  vz = (float)dis.readFloat();
  v_estimated_delay_us = (int)dis.readInt()&0x00FFFFFFFF;
  feed_forward_angular_velocity_z = (float)dis.readFloat();
  estimator_status = (int)dis.readUnsignedShort()&0x00FFFF;
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
  landed_state = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+53];
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
  dos.writeLong(time_boot_us);
  for (int i=0; i<4; i++) {
    dos.writeFloat(q[i]);
  }
  dos.writeInt((int)(q_estimated_delay_us&0x00FFFFFFFF));
  dos.writeFloat(vx);
  dos.writeFloat(vy);
  dos.writeFloat(vz);
  dos.writeInt((int)(v_estimated_delay_us&0x00FFFFFFFF));
  dos.writeFloat(feed_forward_angular_velocity_z);
  dos.writeShort(estimator_status&0x00FFFF);
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.writeByte(landed_state&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 53);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[63] = crcl;
  buffer[64] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_AUTOPILOT_STATE_FOR_GIMBAL_DEVICE : " +   "  time_boot_us="+time_boot_us
+  "  q[0]="+format((float)q[0])
+  "  q[1]="+format((float)q[1])
+  "  q[2]="+format((float)q[2])
+  "  q[3]="+format((float)q[3])
+  "  q_estimated_delay_us="+q_estimated_delay_us
+  "  vx="+format((float)vx)
+  "  vy="+format((float)vy)
+  "  vz="+format((float)vz)
+  "  v_estimated_delay_us="+v_estimated_delay_us
+  "  feed_forward_angular_velocity_z="+format((float)feed_forward_angular_velocity_z)
+  "  estimator_status="+estimator_status
+  "  target_system="+target_system
+  "  target_component="+target_component
+  "  landed_state="+landed_state
;}

}

