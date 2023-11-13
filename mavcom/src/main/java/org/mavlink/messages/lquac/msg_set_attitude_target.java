/**
 * Generated class : msg_set_attitude_target
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
 * Class msg_set_attitude_target
 * Sets a desired vehicle attitude. Used by an external controller to command the vehicle (manual controller or other system).
 **/
public class msg_set_attitude_target extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_SET_ATTITUDE_TARGET = 82;
  private static final long serialVersionUID = MAVLINK_MSG_ID_SET_ATTITUDE_TARGET;
  public msg_set_attitude_target() {
    this(1,1);
}
  public msg_set_attitude_target(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_SET_ATTITUDE_TARGET;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 51;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Attitude quaternion (w, x, y, z order, zero-rotation is 1, 0, 0, 0) from MAV_FRAME_LOCAL_NED to MAV_FRAME_BODY_FRD
   */
  public float[] q = new float[4];
  /**
   * Body roll rate
   */
  public float body_roll_rate;
  /**
   * Body pitch rate
   */
  public float body_pitch_rate;
  /**
   * Body yaw rate
   */
  public float body_yaw_rate;
  /**
   * Collective thrust, normalized to 0 .. 1 (-1 .. 1 for vehicles capable of reverse trust)
   */
  public float thrust;
  /**
   * System ID
   */
  public int target_system;
  /**
   * Component ID
   */
  public int target_component;
  /**
   * Bitmap to indicate which dimensions should be ignored by the vehicle.
   */
  public int type_mask;
  /**
   * 3D thrust setpoint in the body NED frame, normalized to -1 .. 1
   */
  public float[] thrust_body = new float[3];
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  for (int i=0; i<4; i++) {
    q[i] = (float)dis.readFloat();
  }
  body_roll_rate = (float)dis.readFloat();
  body_pitch_rate = (float)dis.readFloat();
  body_yaw_rate = (float)dis.readFloat();
  thrust = (float)dis.readFloat();
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
  type_mask = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<3; i++) {
    thrust_body[i] = (float)dis.readFloat();
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+51];
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
  dos.writeFloat(body_roll_rate);
  dos.writeFloat(body_pitch_rate);
  dos.writeFloat(body_yaw_rate);
  dos.writeFloat(thrust);
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.writeByte(type_mask&0x00FF);
  for (int i=0; i<3; i++) {
    dos.writeFloat(thrust_body[i]);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 51);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[61] = crcl;
  buffer[62] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_SET_ATTITUDE_TARGET : " +   "  time_boot_ms="+time_boot_ms
+  "  q[0]="+format((float)q[0])
+  "  q[1]="+format((float)q[1])
+  "  q[2]="+format((float)q[2])
+  "  q[3]="+format((float)q[3])
+  "  body_roll_rate="+format((float)body_roll_rate)
+  "  body_pitch_rate="+format((float)body_pitch_rate)
+  "  body_yaw_rate="+format((float)body_yaw_rate)
+  "  thrust="+format((float)thrust)
+  "  target_system="+target_system
+  "  target_component="+target_component
+  "  type_mask="+type_mask
+  "  thrust_body[0]="+format((float)thrust_body[0])
+  "  thrust_body[1]="+format((float)thrust_body[1])
+  "  thrust_body[2]="+format((float)thrust_body[2])
;}

}

