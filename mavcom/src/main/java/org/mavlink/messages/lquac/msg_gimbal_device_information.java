/**
 * Generated class : msg_gimbal_device_information
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
 * Class msg_gimbal_device_information
 * Information about a low level gimbal. This message should be requested by the gimbal manager or a ground station using MAV_CMD_REQUEST_MESSAGE.
 **/
public class msg_gimbal_device_information extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION = 283;
  private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION;
  public msg_gimbal_device_information() {
    this(1,1);
}
  public msg_gimbal_device_information(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 98;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Version of the gimbal firmware (v << 24 & 0xff = Dev, v << 16 & 0xff = Patch, v << 8 & 0xff = Minor, v & 0xff = Major)
   */
  public long firmware_version;
  /**
   * Maximum tilt/pitch angle (positive: up, negative: down)
   */
  public float tilt_max;
  /**
   * Minimum tilt/pitch angle (positive: up, negative: down)
   */
  public float tilt_min;
  /**
   * Maximum tilt/pitch angular rate (positive: up, negative: down)
   */
  public float tilt_rate_max;
  /**
   * Maximum pan/yaw angle (positive: to the right, negative: to the left)
   */
  public float pan_max;
  /**
   * Minimum pan/yaw angle (positive: to the right, negative: to the left)
   */
  public float pan_min;
  /**
   * Minimum pan/yaw angular rate (positive: to the right, negative: to the left)
   */
  public float pan_rate_max;
  /**
   * Bitmap of gimbal capability flags.
   */
  public int cap_flags;
  /**
   * Name of the gimbal vendor
   */
  public int[] vendor_name = new int[32];
  /**
   * Name of the gimbal model
   */
  public int[] model_name = new int[32];
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  firmware_version = (int)dis.readInt()&0x00FFFFFFFF;
  tilt_max = (float)dis.readFloat();
  tilt_min = (float)dis.readFloat();
  tilt_rate_max = (float)dis.readFloat();
  pan_max = (float)dis.readFloat();
  pan_min = (float)dis.readFloat();
  pan_rate_max = (float)dis.readFloat();
  cap_flags = (int)dis.readUnsignedShort()&0x00FFFF;
  for (int i=0; i<32; i++) {
    vendor_name[i] = (int)dis.readUnsignedByte()&0x00FF;
  }
  for (int i=0; i<32; i++) {
    model_name[i] = (int)dis.readUnsignedByte()&0x00FF;
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+98];
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
  dos.writeFloat(tilt_max);
  dos.writeFloat(tilt_min);
  dos.writeFloat(tilt_rate_max);
  dos.writeFloat(pan_max);
  dos.writeFloat(pan_min);
  dos.writeFloat(pan_rate_max);
  dos.writeShort(cap_flags&0x00FFFF);
  for (int i=0; i<32; i++) {
    dos.writeByte(vendor_name[i]&0x00FF);
  }
  for (int i=0; i<32; i++) {
    dos.writeByte(model_name[i]&0x00FF);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 98);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[108] = crcl;
  buffer[109] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION : " +   "  time_boot_ms="+time_boot_ms
+  "  firmware_version="+firmware_version
+  "  tilt_max="+tilt_max
+  "  tilt_min="+tilt_min
+  "  tilt_rate_max="+tilt_rate_max
+  "  pan_max="+pan_max
+  "  pan_min="+pan_min
+  "  pan_rate_max="+pan_rate_max
+  "  cap_flags="+cap_flags
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
;}
}
