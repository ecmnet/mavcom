/**
 * Generated class : msg_camera_fov_status
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
 * Class msg_camera_fov_status
 * Information about the field of view of a camera. Can be requested with a MAV_CMD_REQUEST_MESSAGE command.
 **/
public class msg_camera_fov_status extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_CAMERA_FOV_STATUS = 271;
  private static final long serialVersionUID = MAVLINK_MSG_ID_CAMERA_FOV_STATUS;
  public msg_camera_fov_status() {
    this(1,1);
}
  public msg_camera_fov_status(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_CAMERA_FOV_STATUS;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 52;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Latitude of camera (INT32_MAX if unknown).
   */
  public long lat_camera;
  /**
   * Longitude of camera (INT32_MAX if unknown).
   */
  public long lon_camera;
  /**
   * Altitude (MSL) of camera (INT32_MAX if unknown).
   */
  public long alt_camera;
  /**
   * Latitude of center of image (INT32_MAX if unknown, INT32_MIN if at infinity, not intersecting with horizon).
   */
  public long lat_image;
  /**
   * Longitude of center of image (INT32_MAX if unknown, INT32_MIN if at infinity, not intersecting with horizon).
   */
  public long lon_image;
  /**
   * Altitude (MSL) of center of image (INT32_MAX if unknown, INT32_MIN if at infinity, not intersecting with horizon).
   */
  public long alt_image;
  /**
   * Quaternion of camera orientation (w, x, y, z order, zero-rotation is 1, 0, 0, 0)
   */
  public float[] q = new float[4];
  /**
   * Horizontal field of view (NaN if unknown).
   */
  public float hfov;
  /**
   * Vertical field of view (NaN if unknown).
   */
  public float vfov;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  lat_camera = (int)dis.readInt();
  lon_camera = (int)dis.readInt();
  alt_camera = (int)dis.readInt();
  lat_image = (int)dis.readInt();
  lon_image = (int)dis.readInt();
  alt_image = (int)dis.readInt();
  for (int i=0; i<4; i++) {
    q[i] = (float)dis.readFloat();
  }
  hfov = (float)dis.readFloat();
  vfov = (float)dis.readFloat();
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+52];
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
  dos.writeInt((int)(lat_camera&0x00FFFFFFFF));
  dos.writeInt((int)(lon_camera&0x00FFFFFFFF));
  dos.writeInt((int)(alt_camera&0x00FFFFFFFF));
  dos.writeInt((int)(lat_image&0x00FFFFFFFF));
  dos.writeInt((int)(lon_image&0x00FFFFFFFF));
  dos.writeInt((int)(alt_image&0x00FFFFFFFF));
  for (int i=0; i<4; i++) {
    dos.writeFloat(q[i]);
  }
  dos.writeFloat(hfov);
  dos.writeFloat(vfov);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 52);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[62] = crcl;
  buffer[63] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_CAMERA_FOV_STATUS : " +   "  time_boot_ms="+time_boot_ms
+  "  lat_camera="+lat_camera
+  "  lon_camera="+lon_camera
+  "  alt_camera="+alt_camera
+  "  lat_image="+lat_image
+  "  lon_image="+lon_image
+  "  alt_image="+alt_image
+  "  q[0]="+format((float)q[0])
+  "  q[1]="+format((float)q[1])
+  "  q[2]="+format((float)q[2])
+  "  q[3]="+format((float)q[3])
+  "  hfov="+format((float)hfov)
+  "  vfov="+format((float)vfov)
;}
}
