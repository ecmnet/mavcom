/**
 * Generated class : msg_camera_thermal_range
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
 * Class msg_camera_thermal_range
 * Camera absolute thermal range. This can be streamed when the associated VIDEO_STREAM_STATUS `flag` field bit VIDEO_STREAM_STATUS_FLAGS_THERMAL_RANGE_ENABLED is set, but a GCS may choose to only request it for the current active stream. Use MAV_CMD_SET_MESSAGE_INTERVAL to define message interval (param3 indicates the stream id of the current camera, or 0 for all streams, param4 indicates the target camera_device_id for autopilot-attached cameras or 0 for MAVLink cameras).
 **/
public class msg_camera_thermal_range extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_CAMERA_THERMAL_RANGE = 277;
  private static final long serialVersionUID = MAVLINK_MSG_ID_CAMERA_THERMAL_RANGE;
  public msg_camera_thermal_range() {
    this(1,1);
}
  public msg_camera_thermal_range(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_CAMERA_THERMAL_RANGE;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 30;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Temperature max.
   */
  public float max;
  /**
   * Temperature max point x value (normalized 0..1, 0 is left, 1 is right), NAN if unknown.
   */
  public float max_point_x;
  /**
   * Temperature max point y value (normalized 0..1, 0 is top, 1 is bottom), NAN if unknown.
   */
  public float max_point_y;
  /**
   * Temperature min.
   */
  public float min;
  /**
   * Temperature min point x value (normalized 0..1, 0 is left, 1 is right), NAN if unknown.
   */
  public float min_point_x;
  /**
   * Temperature min point y value (normalized 0..1, 0 is top, 1 is bottom), NAN if unknown.
   */
  public float min_point_y;
  /**
   * Video Stream ID (1 for first, 2 for second, etc.)
   */
  public int stream_id;
  /**
   * Camera id of a non-MAVLink camera attached to an autopilot (1-6).  0 if the component is a MAVLink camera (with its own component id).
   */
  public int camera_device_id;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  max = (float)dis.readFloat();
  max_point_x = (float)dis.readFloat();
  max_point_y = (float)dis.readFloat();
  min = (float)dis.readFloat();
  min_point_x = (float)dis.readFloat();
  min_point_y = (float)dis.readFloat();
  stream_id = (int)dis.readUnsignedByte()&0x00FF;
  camera_device_id = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+30];
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
  dos.writeFloat(max);
  dos.writeFloat(max_point_x);
  dos.writeFloat(max_point_y);
  dos.writeFloat(min);
  dos.writeFloat(min_point_x);
  dos.writeFloat(min_point_y);
  dos.writeByte(stream_id&0x00FF);
  dos.writeByte(camera_device_id&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 30);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[40] = crcl;
  buffer[41] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_CAMERA_THERMAL_RANGE : " +   "  time_boot_ms="+time_boot_ms
+  "  max="+format((float)max)
+  "  max_point_x="+format((float)max_point_x)
+  "  max_point_y="+format((float)max_point_y)
+  "  min="+format((float)min)
+  "  min_point_x="+format((float)min_point_x)
+  "  min_point_y="+format((float)min_point_y)
+  "  stream_id="+stream_id
+  "  camera_device_id="+camera_device_id
;}

}

