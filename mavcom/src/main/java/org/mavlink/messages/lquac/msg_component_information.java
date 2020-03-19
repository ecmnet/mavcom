/**
 * Generated class : msg_component_information
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
 * Class msg_component_information
 * Information about a component. For camera components instead use CAMERA_INFORMATION, and for autopilots use AUTOPILOT_VERSION. Components including GCSes should consider supporting requests of this message via MAV_CMD_REQUEST_MESSAGE.
 **/
public class msg_component_information extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_COMPONENT_INFORMATION = 395;
  private static final long serialVersionUID = MAVLINK_MSG_ID_COMPONENT_INFORMATION;
  public msg_component_information() {
    this(1,1);
}
  public msg_component_information(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_COMPONENT_INFORMATION;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 222;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Version of the component firmware (v << 24 & 0xff = Dev, v << 16 & 0xff = Patch, v << 8 & 0xff = Minor, v & 0xff = Major)
   */
  public long firmware_version;
  /**
   * Version of the component hardware (v << 24 & 0xff = Dev, v << 16 & 0xff = Patch, v << 8 & 0xff = Minor, v & 0xff = Major)
   */
  public long hardware_version;
  /**
   * Bitmap of component capability flags.
   */
  public long capability_flags;
  /**
   * Component definition version (iteration)
   */
  public int component_definition_version;
  /**
   * Name of the component vendor
   */
  public int[] vendor_name = new int[32];
  /**
   * Name of the component model
   */
  public int[] model_name = new int[32];
  /**
   * Component definition URI (if any, otherwise only basic functions will be available). The XML format is not yet specified and work in progress.
   */
  public char[] component_definition_uri = new char[140];
  public void setComponent_definition_uri(String tmp) {
    int len = Math.min(tmp.length(), 140);
    for (int i=0; i<len; i++) {
      component_definition_uri[i] = tmp.charAt(i);
    }
    for (int i=len; i<140; i++) {
      component_definition_uri[i] = 0;
    }
  }
  public String getComponent_definition_uri() {
    String result="";
    for (int i=0; i<140; i++) {
      if (component_definition_uri[i] != 0) result=result+component_definition_uri[i]; else break;
    }
    return result;
  }
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  firmware_version = (int)dis.readInt()&0x00FFFFFFFF;
  hardware_version = (int)dis.readInt()&0x00FFFFFFFF;
  capability_flags = (int)dis.readInt()&0x00FFFFFFFF;
  component_definition_version = (int)dis.readUnsignedShort()&0x00FFFF;
  for (int i=0; i<32; i++) {
    vendor_name[i] = (int)dis.readUnsignedByte()&0x00FF;
  }
  for (int i=0; i<32; i++) {
    model_name[i] = (int)dis.readUnsignedByte()&0x00FF;
  }
  for (int i=0; i<140; i++) {
    component_definition_uri[i] = (char)dis.readByte();
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+222];
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
  dos.writeInt((int)(hardware_version&0x00FFFFFFFF));
  dos.writeInt((int)(capability_flags&0x00FFFFFFFF));
  dos.writeShort(component_definition_version&0x00FFFF);
  for (int i=0; i<32; i++) {
    dos.writeByte(vendor_name[i]&0x00FF);
  }
  for (int i=0; i<32; i++) {
    dos.writeByte(model_name[i]&0x00FF);
  }
  for (int i=0; i<140; i++) {
    dos.writeByte(component_definition_uri[i]);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 222);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[232] = crcl;
  buffer[233] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_COMPONENT_INFORMATION : " +   "  time_boot_ms="+time_boot_ms
+  "  firmware_version="+firmware_version
+  "  hardware_version="+hardware_version
+  "  capability_flags="+capability_flags
+  "  component_definition_version="+component_definition_version
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
+  "  component_definition_uri="+getComponent_definition_uri()
;}
}
