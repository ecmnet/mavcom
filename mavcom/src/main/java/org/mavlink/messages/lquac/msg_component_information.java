/**
 * Generated class : msg_component_information
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
    payload_length = 156;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * The type of metadata being requested.
   */
  public long metadata_type;
  /**
   * Unique uid for this metadata which a gcs can use for created cached metadata and understanding whether it's cache it up to date or whether it needs to download new data.
   */
  public long metadata_uid;
  /**
   * Unique uid for the translation file associated with the metadata.
   */
  public long translation_uid;
  /**
   * Component definition URI. If prefix mavlinkftp:// file is downloaded from vehicle over mavlink ftp protocol. If prefix http[s]:// file is downloaded over internet. Files are json format. They can end in .gz to indicate file is in gzip format.
   */
  public char[] metadata_uri = new char[70];
  public void setMetadata_uri(String tmp) {
    int len = Math.min(tmp.length(), 70);
    for (int i=0; i<len; i++) {
      metadata_uri[i] = tmp.charAt(i);
    }
    for (int i=len; i<70; i++) {
      metadata_uri[i] = 0;
    }
  }
  public String getMetadata_uri() {
    String result="";
    for (int i=0; i<70; i++) {
      if (metadata_uri[i] != 0) result=result+metadata_uri[i]; else break;
    }
    return result;
  }
  /**
   * The translations for strings within the metadata file. If null the either do not exist or may be included in the metadata file itself. The translations specified here supercede any which may be in the metadata file itself. The uri format is the same as component_metadata_uri . Files are in Json Translation spec format. Empty string indicates no tranlsation file.
   */
  public char[] translation_uri = new char[70];
  public void setTranslation_uri(String tmp) {
    int len = Math.min(tmp.length(), 70);
    for (int i=0; i<len; i++) {
      translation_uri[i] = tmp.charAt(i);
    }
    for (int i=len; i<70; i++) {
      translation_uri[i] = 0;
    }
  }
  public String getTranslation_uri() {
    String result="";
    for (int i=0; i<70; i++) {
      if (translation_uri[i] != 0) result=result+translation_uri[i]; else break;
    }
    return result;
  }
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  metadata_type = (int)dis.readInt()&0x00FFFFFFFF;
  metadata_uid = (int)dis.readInt()&0x00FFFFFFFF;
  translation_uid = (int)dis.readInt()&0x00FFFFFFFF;
  for (int i=0; i<70; i++) {
    metadata_uri[i] = (char)dis.readByte();
  }
  for (int i=0; i<70; i++) {
    translation_uri[i] = (char)dis.readByte();
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+156];
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
  dos.writeInt((int)(metadata_type&0x00FFFFFFFF));
  dos.writeInt((int)(metadata_uid&0x00FFFFFFFF));
  dos.writeInt((int)(translation_uid&0x00FFFFFFFF));
  for (int i=0; i<70; i++) {
    dos.writeByte(metadata_uri[i]);
  }
  for (int i=0; i<70; i++) {
    dos.writeByte(translation_uri[i]);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 156);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[166] = crcl;
  buffer[167] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_COMPONENT_INFORMATION : " +   "  time_boot_ms="+time_boot_ms
+  "  metadata_type="+metadata_type
+  "  metadata_uid="+metadata_uid
+  "  translation_uid="+translation_uid
+  "  metadata_uri="+getMetadata_uri()
+  "  translation_uri="+getTranslation_uri()
;}
}
