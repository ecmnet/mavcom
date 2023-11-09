/**
 * Generated class : msg_component_metadata
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
 * Class msg_component_metadata
 * Component metadata message, which may be requested using MAV_CMD_REQUEST_MESSAGE.

        This contains the MAVLink FTP URI and CRC for the component's general metadata file.
        The file must be hosted on the component, and may be xz compressed.
        The file CRC can be used for file caching.

        The general metadata file can be read to get the locations of other metadata files (COMP_METADATA_TYPE) and translations, which may be hosted either on the vehicle or the internet.
        For more information see: https://mavlink.io/en/services/component_information.html.

        Note: Camera components should use CAMERA_INFORMATION instead, and autopilots may use both this message and AUTOPILOT_VERSION.
 **/
public class msg_component_metadata extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_COMPONENT_METADATA = 397;
  private static final long serialVersionUID = MAVLINK_MSG_ID_COMPONENT_METADATA;
  public msg_component_metadata() {
    this(1,1);
}
  public msg_component_metadata(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_COMPONENT_METADATA;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 108;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * CRC32 of the general metadata file.
   */
  public long file_crc;
  /**
   * MAVLink FTP URI for the general metadata file (COMP_METADATA_TYPE_GENERAL), which may be compressed with xz. The file contains general component metadata, and may contain URI links for additional metadata (see COMP_METADATA_TYPE). The information is static from boot, and may be generated at compile time. The string needs to be zero terminated.
   */
  public char[] uri = new char[100];
  public void setUri(String tmp) {
    int len = Math.min(tmp.length(), 100);
    for (int i=0; i<len; i++) {
      uri[i] = tmp.charAt(i);
    }
    for (int i=len; i<100; i++) {
      uri[i] = 0;
    }
  }
  public String getUri() {
    String result="";
    for (int i=0; i<100; i++) {
      if (uri[i] != 0) result=result+uri[i]; else break;
    }
    return result;
  }
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  file_crc = (int)dis.readInt()&0x00FFFFFFFF;
  for (int i=0; i<100; i++) {
    uri[i] = (char)dis.readByte();
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+108];
   LittleEndianDataOutputStream dos = new LittleEndianDataOutputStream(new ByteArrayOutputStream());
  dos.writeByte((byte)0xFD);
  dos.writeByte(payload_length & 0x00FF);
  dos.writeByte(incompat & 0x00FF);
  dos.writeByte(compat & 0x00FF);
  dos.writeByte(packet & 0x00FF);
  dos.writeByte(sysId & 0x007F);
  dos.writeByte(componentId & 0x007F);
  dos.writeByte(messageType & 0x00FF);
  dos.writeByte((messageType >> 8) & 0x00FF);
  dos.writeByte((messageType >> 16) & 0x00FF);
  dos.writeInt((int)(time_boot_ms&0x00FFFFFFFF));
  dos.writeInt((int)(file_crc&0x00FFFFFFFF));
  for (int i=0; i<100; i++) {
    dos.writeByte(uri[i]);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 108);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[118] = crcl;
  buffer[119] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_COMPONENT_METADATA : " +   "  time_boot_ms="+time_boot_ms
+  "  file_crc="+file_crc
+  "  uri="+getUri()
;}

}

