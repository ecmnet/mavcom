/**
 * Generated class : msg_component_information_basic
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
 * Class msg_component_information_basic
 * Basic component information data. Should be requested using MAV_CMD_REQUEST_MESSAGE on startup, or when required.
 **/
public class msg_component_information_basic extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_COMPONENT_INFORMATION_BASIC = 396;
  private static final long serialVersionUID = MAVLINK_MSG_ID_COMPONENT_INFORMATION_BASIC;
  public msg_component_information_basic() {
    this(1,1);
}
  public msg_component_information_basic(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_COMPONENT_INFORMATION_BASIC;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 160;
}

  /**
   * Component capability flags
   */
  public long capabilities;
  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Date of manufacture as a UNIX Epoch time (since 1.1.1970) in seconds.
   */
  public long time_manufacture_s;
  /**
   * Name of the component vendor. Needs to be zero terminated. The field is optional and can be empty/all zeros.
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
   * Name of the component model. Needs to be zero terminated. The field is optional and can be empty/all zeros.
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
   * Software version. The recommended format is SEMVER: 'major.minor.patch'  (any format may be used). The field must be zero terminated if it has a value. The field is optional and can be empty/all zeros.
   */
  public char[] software_version = new char[24];
  public void setSoftware_version(String tmp) {
    int len = Math.min(tmp.length(), 24);
    for (int i=0; i<len; i++) {
      software_version[i] = tmp.charAt(i);
    }
    for (int i=len; i<24; i++) {
      software_version[i] = 0;
    }
  }
  public String getSoftware_version() {
    String result="";
    for (int i=0; i<24; i++) {
      if (software_version[i] != 0) result=result+software_version[i]; else break;
    }
    return result;
  }
  /**
   * Hardware version. The recommended format is SEMVER: 'major.minor.patch'  (any format may be used). The field must be zero terminated if it has a value. The field is optional and can be empty/all zeros.
   */
  public char[] hardware_version = new char[24];
  public void setHardware_version(String tmp) {
    int len = Math.min(tmp.length(), 24);
    for (int i=0; i<len; i++) {
      hardware_version[i] = tmp.charAt(i);
    }
    for (int i=len; i<24; i++) {
      hardware_version[i] = 0;
    }
  }
  public String getHardware_version() {
    String result="";
    for (int i=0; i<24; i++) {
      if (hardware_version[i] != 0) result=result+hardware_version[i]; else break;
    }
    return result;
  }
  /**
   * Hardware serial number. The field must be zero terminated if it has a value. The field is optional and can be empty/all zeros.
   */
  public char[] serial_number = new char[32];
  public void setSerial_number(String tmp) {
    int len = Math.min(tmp.length(), 32);
    for (int i=0; i<len; i++) {
      serial_number[i] = tmp.charAt(i);
    }
    for (int i=len; i<32; i++) {
      serial_number[i] = 0;
    }
  }
  public String getSerial_number() {
    String result="";
    for (int i=0; i<32; i++) {
      if (serial_number[i] != 0) result=result+serial_number[i]; else break;
    }
    return result;
  }
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  capabilities = (long)dis.readLong();
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  time_manufacture_s = (int)dis.readInt()&0x00FFFFFFFF;
  for (int i=0; i<32; i++) {
    vendor_name[i] = (char)dis.readByte();
  }
  for (int i=0; i<32; i++) {
    model_name[i] = (char)dis.readByte();
  }
  for (int i=0; i<24; i++) {
    software_version[i] = (char)dis.readByte();
  }
  for (int i=0; i<24; i++) {
    hardware_version[i] = (char)dis.readByte();
  }
  for (int i=0; i<32; i++) {
    serial_number[i] = (char)dis.readByte();
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+160];
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
  dos.writeLong(capabilities);
  dos.writeInt((int)(time_boot_ms&0x00FFFFFFFF));
  dos.writeInt((int)(time_manufacture_s&0x00FFFFFFFF));
  for (int i=0; i<32; i++) {
    dos.writeByte(vendor_name[i]);
  }
  for (int i=0; i<32; i++) {
    dos.writeByte(model_name[i]);
  }
  for (int i=0; i<24; i++) {
    dos.writeByte(software_version[i]);
  }
  for (int i=0; i<24; i++) {
    dos.writeByte(hardware_version[i]);
  }
  for (int i=0; i<32; i++) {
    dos.writeByte(serial_number[i]);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 160);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[170] = crcl;
  buffer[171] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_COMPONENT_INFORMATION_BASIC : " +   "  capabilities="+capabilities
+  "  time_boot_ms="+time_boot_ms
+  "  time_manufacture_s="+time_manufacture_s
+  "  vendor_name="+getVendor_name()
+  "  model_name="+getModel_name()
+  "  software_version="+getSoftware_version()
+  "  hardware_version="+getHardware_version()
+  "  serial_number="+getSerial_number()
;}

}

