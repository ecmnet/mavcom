/**
 * Generated class : msg_camera_information
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
 * Class msg_camera_information
 * Information about a camera. Can be requested with a MAV_CMD_REQUEST_MESSAGE command.
 **/
public class msg_camera_information extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_CAMERA_INFORMATION = 259;
  private static final long serialVersionUID = MAVLINK_MSG_ID_CAMERA_INFORMATION;
  public msg_camera_information() {
    this(1,1);
}
  public msg_camera_information(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_CAMERA_INFORMATION;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 237;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Version of the camera firmware, encoded as: (Dev & 0xff) << 24 | (Patch & 0xff) << 16 | (Minor & 0xff) << 8 | (Major & 0xff). Use 0 if not known.
   */
  public long firmware_version;
  /**
   * Focal length. Use NaN if not known.
   */
  public float focal_length;
  /**
   * Image sensor size horizontal. Use NaN if not known.
   */
  public float sensor_size_h;
  /**
   * Image sensor size vertical. Use NaN if not known.
   */
  public float sensor_size_v;
  /**
   * Bitmap of camera capability flags.
   */
  public long flags;
  /**
   * Horizontal image resolution. Use 0 if not known.
   */
  public int resolution_h;
  /**
   * Vertical image resolution. Use 0 if not known.
   */
  public int resolution_v;
  /**
   * Camera definition version (iteration).  Use 0 if not known.
   */
  public int cam_definition_version;
  /**
   * Name of the camera vendor
   */
  public int[] vendor_name = new int[32];
  /**
   * Name of the camera model
   */
  public int[] model_name = new int[32];
  /**
   * Reserved for a lens ID.  Use 0 if not known.
   */
  public int lens_id;
  /**
   * Camera definition URI (if any, otherwise only basic functions will be available). HTTP- (http://) and MAVLink FTP- (mavlinkftp://) formatted URIs are allowed (and both must be supported by any GCS that implements the Camera Protocol). The definition file may be xz compressed, which will be indicated by the file extension .xml.xz (a GCS that implements the protocol must support decompressing the file). The string needs to be zero terminated.  Use a zero-length string if not known.
   */
  public char[] cam_definition_uri = new char[140];
  public void setCam_definition_uri(String tmp) {
    int len = Math.min(tmp.length(), 140);
    for (int i=0; i<len; i++) {
      cam_definition_uri[i] = tmp.charAt(i);
    }
    for (int i=len; i<140; i++) {
      cam_definition_uri[i] = 0;
    }
  }
  public String getCam_definition_uri() {
    String result="";
    for (int i=0; i<140; i++) {
      if (cam_definition_uri[i] != 0) result=result+cam_definition_uri[i]; else break;
    }
    return result;
  }
  /**
   * Gimbal id of a gimbal associated with this camera. This is the component id of the gimbal device, or 1-6 for non mavlink gimbals. Use 0 if no gimbal is associated with the camera.
   */
  public int gimbal_device_id;
  /**
   * Camera id of a non-MAVLink camera attached to an autopilot (1-6).  0 if the component is a MAVLink camera (with its own component id).
   */
  public int camera_device_id;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  firmware_version = (int)dis.readInt()&0x00FFFFFFFF;
  focal_length = (float)dis.readFloat();
  sensor_size_h = (float)dis.readFloat();
  sensor_size_v = (float)dis.readFloat();
  flags = (int)dis.readInt()&0x00FFFFFFFF;
  resolution_h = (int)dis.readUnsignedShort()&0x00FFFF;
  resolution_v = (int)dis.readUnsignedShort()&0x00FFFF;
  cam_definition_version = (int)dis.readUnsignedShort()&0x00FFFF;
  for (int i=0; i<32; i++) {
    vendor_name[i] = (int)dis.readUnsignedByte()&0x00FF;
  }
  for (int i=0; i<32; i++) {
    model_name[i] = (int)dis.readUnsignedByte()&0x00FF;
  }
  lens_id = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<140; i++) {
    cam_definition_uri[i] = (char)dis.readByte();
  }
  gimbal_device_id = (int)dis.readUnsignedByte()&0x00FF;
  camera_device_id = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+237];
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
  dos.writeInt((int)(firmware_version&0x00FFFFFFFF));
  dos.writeFloat(focal_length);
  dos.writeFloat(sensor_size_h);
  dos.writeFloat(sensor_size_v);
  dos.writeInt((int)(flags&0x00FFFFFFFF));
  dos.writeShort(resolution_h&0x00FFFF);
  dos.writeShort(resolution_v&0x00FFFF);
  dos.writeShort(cam_definition_version&0x00FFFF);
  for (int i=0; i<32; i++) {
    dos.writeByte(vendor_name[i]&0x00FF);
  }
  for (int i=0; i<32; i++) {
    dos.writeByte(model_name[i]&0x00FF);
  }
  dos.writeByte(lens_id&0x00FF);
  for (int i=0; i<140; i++) {
    dos.writeByte(cam_definition_uri[i]);
  }
  dos.writeByte(gimbal_device_id&0x00FF);
  dos.writeByte(camera_device_id&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 237);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[247] = crcl;
  buffer[248] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_CAMERA_INFORMATION : " +   "  time_boot_ms="+time_boot_ms
+  "  firmware_version="+firmware_version
+  "  focal_length="+format((float)focal_length)
+  "  sensor_size_h="+format((float)sensor_size_h)
+  "  sensor_size_v="+format((float)sensor_size_v)
+  "  flags="+flags
+  "  resolution_h="+resolution_h
+  "  resolution_v="+resolution_v
+  "  cam_definition_version="+cam_definition_version
+  "  vendor_name[0]="+vendor_name[0]
+  "  vendor_name[1]="+vendor_name[1]
+  "  vendor_name[2]="+vendor_name[2]
+  "  vendor_name[3]="+vendor_name[3]
+  "  vendor_name[4]="+vendor_name[4]
+  "  vendor_name[5]="+vendor_name[5]
+  "  vendor_name[6]="+vendor_name[6]
+  "  vendor_name[7]="+vendor_name[7]
+  "  vendor_name[8]="+vendor_name[8]
+  "  vendor_name[9]="+vendor_name[9]
+  "  vendor_name[10]="+vendor_name[10]
+  "  vendor_name[11]="+vendor_name[11]
+  "  vendor_name[12]="+vendor_name[12]
+  "  vendor_name[13]="+vendor_name[13]
+  "  vendor_name[14]="+vendor_name[14]
+  "  vendor_name[15]="+vendor_name[15]
+  "  vendor_name[16]="+vendor_name[16]
+  "  vendor_name[17]="+vendor_name[17]
+  "  vendor_name[18]="+vendor_name[18]
+  "  vendor_name[19]="+vendor_name[19]
+  "  vendor_name[20]="+vendor_name[20]
+  "  vendor_name[21]="+vendor_name[21]
+  "  vendor_name[22]="+vendor_name[22]
+  "  vendor_name[23]="+vendor_name[23]
+  "  vendor_name[24]="+vendor_name[24]
+  "  vendor_name[25]="+vendor_name[25]
+  "  vendor_name[26]="+vendor_name[26]
+  "  vendor_name[27]="+vendor_name[27]
+  "  vendor_name[28]="+vendor_name[28]
+  "  vendor_name[29]="+vendor_name[29]
+  "  vendor_name[30]="+vendor_name[30]
+  "  vendor_name[31]="+vendor_name[31]
+  "  model_name[0]="+model_name[0]
+  "  model_name[1]="+model_name[1]
+  "  model_name[2]="+model_name[2]
+  "  model_name[3]="+model_name[3]
+  "  model_name[4]="+model_name[4]
+  "  model_name[5]="+model_name[5]
+  "  model_name[6]="+model_name[6]
+  "  model_name[7]="+model_name[7]
+  "  model_name[8]="+model_name[8]
+  "  model_name[9]="+model_name[9]
+  "  model_name[10]="+model_name[10]
+  "  model_name[11]="+model_name[11]
+  "  model_name[12]="+model_name[12]
+  "  model_name[13]="+model_name[13]
+  "  model_name[14]="+model_name[14]
+  "  model_name[15]="+model_name[15]
+  "  model_name[16]="+model_name[16]
+  "  model_name[17]="+model_name[17]
+  "  model_name[18]="+model_name[18]
+  "  model_name[19]="+model_name[19]
+  "  model_name[20]="+model_name[20]
+  "  model_name[21]="+model_name[21]
+  "  model_name[22]="+model_name[22]
+  "  model_name[23]="+model_name[23]
+  "  model_name[24]="+model_name[24]
+  "  model_name[25]="+model_name[25]
+  "  model_name[26]="+model_name[26]
+  "  model_name[27]="+model_name[27]
+  "  model_name[28]="+model_name[28]
+  "  model_name[29]="+model_name[29]
+  "  model_name[30]="+model_name[30]
+  "  model_name[31]="+model_name[31]
+  "  lens_id="+lens_id
+  "  cam_definition_uri="+getCam_definition_uri()
+  "  gimbal_device_id="+gimbal_device_id
+  "  camera_device_id="+camera_device_id
;}

}

