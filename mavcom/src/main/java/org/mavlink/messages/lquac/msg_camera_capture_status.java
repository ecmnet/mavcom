/**
 * Generated class : msg_camera_capture_status
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
 * Class msg_camera_capture_status
 * Information about the status of a capture. Can be requested with a MAV_CMD_REQUEST_MESSAGE command.
 **/
public class msg_camera_capture_status extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_CAMERA_CAPTURE_STATUS = 262;
  private static final long serialVersionUID = MAVLINK_MSG_ID_CAMERA_CAPTURE_STATUS;
  public msg_camera_capture_status() {
    this(1,1);
}
  public msg_camera_capture_status(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_CAMERA_CAPTURE_STATUS;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 23;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Image capture interval
   */
  public float image_interval;
  /**
   * Elapsed time since recording started (0: Not supported/available). A GCS should compute recording time and use non-zero values of this field to correct any discrepancy.
   */
  public long recording_time_ms;
  /**
   * Available storage capacity.
   */
  public float available_capacity;
  /**
   * Current status of image capturing (0: idle, 1: capture in progress, 2: interval set but idle, 3: interval set and capture in progress)
   */
  public int image_status;
  /**
   * Current status of video capturing (0: idle, 1: capture in progress)
   */
  public int video_status;
  /**
   * Total number of images captured ('forever', or until reset using MAV_CMD_STORAGE_FORMAT).
   */
  public long image_count;
  /**
   * Camera id of a non-MAVLink camera attached to an autopilot (1-6).  0 if the component is a MAVLink camera (with its own component id).
   */
  public int camera_device_id;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  image_interval = (float)dis.readFloat();
  recording_time_ms = (int)dis.readInt()&0x00FFFFFFFF;
  available_capacity = (float)dis.readFloat();
  image_status = (int)dis.readUnsignedByte()&0x00FF;
  video_status = (int)dis.readUnsignedByte()&0x00FF;
  image_count = (int)dis.readInt();
  camera_device_id = (int)dis.readUnsignedByte()&0x00FF;
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
  dos.writeInt((int)(time_boot_ms&0x00FFFFFFFF));
  dos.writeFloat(image_interval);
  dos.writeInt((int)(recording_time_ms&0x00FFFFFFFF));
  dos.writeFloat(available_capacity);
  dos.writeByte(image_status&0x00FF);
  dos.writeByte(video_status&0x00FF);
  dos.writeInt((int)(image_count&0x00FFFFFFFF));
  dos.writeByte(camera_device_id&0x00FF);
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
return "MAVLINK_MSG_ID_CAMERA_CAPTURE_STATUS : " +   "  time_boot_ms="+time_boot_ms
+  "  image_interval="+format((float)image_interval)
+  "  recording_time_ms="+recording_time_ms
+  "  available_capacity="+format((float)available_capacity)
+  "  image_status="+image_status
+  "  video_status="+video_status
+  "  image_count="+image_count
+  "  camera_device_id="+camera_device_id
;}

}

