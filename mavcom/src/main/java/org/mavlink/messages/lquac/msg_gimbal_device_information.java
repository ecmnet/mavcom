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
 * Information about a low level gimbal. This message should be requested by the gimbal manager or a ground station using MAV_CMD_REQUEST_MESSAGE. The maximum angles and rates are the limits by hardware. However, the limits by software used are likely different/smaller and dependent on mode/settings/etc..
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
    payload_length = 144;
}

  /**
   * UID of gimbal hardware (0 if unknown).
   */
  public long uid;
  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Version of the gimbal firmware, encoded as: (Dev & 0xff) << 24 | (Patch & 0xff) << 16 | (Minor & 0xff) << 8 | (Major & 0xff).
   */
  public long firmware_version;
  /**
   * Version of the gimbal hardware, encoded as: (Dev & 0xff) << 24 | (Patch & 0xff) << 16 | (Minor & 0xff) << 8 | (Major & 0xff).
   */
  public long hardware_version;
  /**
   * Minimum hardware roll angle (positive: rolling to the right, negative: rolling to the left)
   */
  public float roll_min;
  /**
   * Maximum hardware roll angle (positive: rolling to the right, negative: rolling to the left)
   */
  public float roll_max;
  /**
   * Minimum hardware pitch angle (positive: up, negative: down)
   */
  public float pitch_min;
  /**
   * Maximum hardware pitch angle (positive: up, negative: down)
   */
  public float pitch_max;
  /**
   * Minimum hardware yaw angle (positive: to the right, negative: to the left)
   */
  public float yaw_min;
  /**
   * Maximum hardware yaw angle (positive: to the right, negative: to the left)
   */
  public float yaw_max;
  /**
   * Bitmap of gimbal capability flags.
   */
  public int cap_flags;
  /**
   * Bitmap for use for gimbal-specific capability flags.
   */
  public int custom_cap_flags;
  /**
   * Name of the gimbal vendor.
   */
  public char[] vendor_name = new char[32];
  public void setVendor_name(String tmp) {
    int len = Math.min(tmp.length(), 32);
    for (int i=0; i<len; i++) {
      vendor_name[i] = tmp.charAt(i);
    }
    for (int i=len; i<32; i++) {
      vendor_name[i] = 0;
    }
  }
  public String getVendor_name() {
    String result="";
    for (int i=0; i<32; i++) {
      if (vendor_name[i] != 0) result=result+vendor_name[i]; else break;
    }
    return result;
  }
  /**
   * Name of the gimbal model.
   */
  public char[] model_name = new char[32];
  public void setModel_name(String tmp) {
    int len = Math.min(tmp.length(), 32);
    for (int i=0; i<len; i++) {
      model_name[i] = tmp.charAt(i);
    }
    for (int i=len; i<32; i++) {
      model_name[i] = 0;
    }
  }
  public String getModel_name() {
    String result="";
    for (int i=0; i<32; i++) {
      if (model_name[i] != 0) result=result+model_name[i]; else break;
    }
    return result;
  }
  /**
   * Custom name of the gimbal given to it by the user.
   */
  public char[] custom_name = new char[32];
  public void setCustom_name(String tmp) {
    int len = Math.min(tmp.length(), 32);
    for (int i=0; i<len; i++) {
      custom_name[i] = tmp.charAt(i);
    }
    for (int i=len; i<32; i++) {
      custom_name[i] = 0;
    }
  }
  public String getCustom_name() {
    String result="";
    for (int i=0; i<32; i++) {
      if (custom_name[i] != 0) result=result+custom_name[i]; else break;
    }
    return result;
  }
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  uid = (long)dis.readLong();
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  firmware_version = (int)dis.readInt()&0x00FFFFFFFF;
  hardware_version = (int)dis.readInt()&0x00FFFFFFFF;
  roll_min = (float)dis.readFloat();
  roll_max = (float)dis.readFloat();
  pitch_min = (float)dis.readFloat();
  pitch_max = (float)dis.readFloat();
  yaw_min = (float)dis.readFloat();
  yaw_max = (float)dis.readFloat();
  cap_flags = (int)dis.readUnsignedShort()&0x00FFFF;
  custom_cap_flags = (int)dis.readUnsignedShort()&0x00FFFF;
  for (int i=0; i<32; i++) {
    vendor_name[i] = (char)dis.readByte();
  }
  for (int i=0; i<32; i++) {
    model_name[i] = (char)dis.readByte();
  }
  for (int i=0; i<32; i++) {
    custom_name[i] = (char)dis.readByte();
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+144];
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
  dos.writeLong(uid);
  dos.writeInt((int)(time_boot_ms&0x00FFFFFFFF));
  dos.writeInt((int)(firmware_version&0x00FFFFFFFF));
  dos.writeInt((int)(hardware_version&0x00FFFFFFFF));
  dos.writeFloat(roll_min);
  dos.writeFloat(roll_max);
  dos.writeFloat(pitch_min);
  dos.writeFloat(pitch_max);
  dos.writeFloat(yaw_min);
  dos.writeFloat(yaw_max);
  dos.writeShort(cap_flags&0x00FFFF);
  dos.writeShort(custom_cap_flags&0x00FFFF);
  for (int i=0; i<32; i++) {
    dos.writeByte(vendor_name[i]);
  }
  for (int i=0; i<32; i++) {
    dos.writeByte(model_name[i]);
  }
  for (int i=0; i<32; i++) {
    dos.writeByte(custom_name[i]);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 144);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[154] = crcl;
  buffer[155] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION : " +   "  uid="+uid
+  "  time_boot_ms="+time_boot_ms
+  "  firmware_version="+firmware_version
+  "  hardware_version="+hardware_version
+  "  roll_min="+format((float)roll_min)
+  "  roll_max="+format((float)roll_max)
+  "  pitch_min="+format((float)pitch_min)
+  "  pitch_max="+format((float)pitch_max)
+  "  yaw_min="+format((float)yaw_min)
+  "  yaw_max="+format((float)yaw_max)
+  "  cap_flags="+cap_flags
+  "  custom_cap_flags="+custom_cap_flags
+  "  vendor_name="+getVendor_name()
+  "  model_name="+getModel_name()
+  "  custom_name="+getCustom_name()
;}

public int getId() {
 return MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION;
}

}

