/**
 * Generated class : msg_gimbal_manager_set_tiltpan
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
 * Class msg_gimbal_manager_set_tiltpan
 * High level message to control a gimbal's tilt and pan angles. This message is to be sent to the gimbal manager (e.g. from a ground station). Angles and rates can be set to NaN according to use case.
 **/
public class msg_gimbal_manager_set_tiltpan extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_GIMBAL_MANAGER_SET_TILTPAN = 287;
  private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_MANAGER_SET_TILTPAN;
  public msg_gimbal_manager_set_tiltpan() {
    this(1,1);
}
  public msg_gimbal_manager_set_tiltpan(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_GIMBAL_MANAGER_SET_TILTPAN;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 23;
}

  /**
   * High level gimbal manager flags to use.
   */
  public long flags;
  /**
   * Tilt/pitch angle (positive: up, negative: down, NaN to be ignored).
   */
  public float tilt;
  /**
   * Pan/yaw angle (positive: to the right, negative: to the left, NaN to be ignored).
   */
  public float pan;
  /**
   * Tilt/pitch angular rate (positive: up, negative: down, NaN to be ignored).
   */
  public float tilt_rate;
  /**
   * Pan/yaw angular rate (positive: to the right, negative: to the left, NaN to be ignored).
   */
  public float pan_rate;
  /**
   * System ID
   */
  public int target_system;
  /**
   * Component ID
   */
  public int target_component;
  /**
   * Component ID of gimbal device to address (or 1-6 for non-MAVLink gimbal), 0 for all gimbal device components. (Send command multiple times for more than one but not all gimbals.)
   */
  public int gimbal_device_id;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  flags = (int)dis.readInt()&0x00FFFFFFFF;
  tilt = (float)dis.readFloat();
  pan = (float)dis.readFloat();
  tilt_rate = (float)dis.readFloat();
  pan_rate = (float)dis.readFloat();
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
  gimbal_device_id = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+23];
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
  dos.writeFloat(tilt);
  dos.writeFloat(pan);
  dos.writeFloat(tilt_rate);
  dos.writeFloat(pan_rate);
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.writeByte(gimbal_device_id&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 23);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[33] = crcl;
  buffer[34] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_GIMBAL_MANAGER_SET_TILTPAN : " +   "  flags="+flags
+  "  tilt="+format((float)tilt)
+  "  pan="+format((float)pan)
+  "  tilt_rate="+format((float)tilt_rate)
+  "  pan_rate="+format((float)pan_rate)
+  "  target_system="+target_system
+  "  target_component="+target_component
+  "  gimbal_device_id="+gimbal_device_id
;}
}
