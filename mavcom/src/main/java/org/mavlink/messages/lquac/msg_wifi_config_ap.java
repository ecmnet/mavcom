/**
 * Generated class : msg_wifi_config_ap
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
 * Class msg_wifi_config_ap
 * Configure WiFi AP SSID, password, and mode. This message is re-emitted as an acknowledgement by the AP. The message may also be explicitly requested using MAV_CMD_REQUEST_MESSAGE
 **/
public class msg_wifi_config_ap extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_WIFI_CONFIG_AP = 299;
  private static final long serialVersionUID = MAVLINK_MSG_ID_WIFI_CONFIG_AP;
  public msg_wifi_config_ap() {
    this(1,1);
}
  public msg_wifi_config_ap(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_WIFI_CONFIG_AP;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 98;
}

  /**
   * Name of Wi-Fi network (SSID). Blank to leave it unchanged when setting. Current SSID when sent back as a response.
   */
  public char[] ssid = new char[32];
  public void setSsid(String tmp) {
    int len = Math.min(tmp.length(), 32);
    for (int i=0; i<len; i++) {
      ssid[i] = tmp.charAt(i);
    }
    for (int i=len; i<32; i++) {
      ssid[i] = 0;
    }
  }
  public String getSsid() {
    String result="";
    for (int i=0; i<32; i++) {
      if (ssid[i] != 0) result=result+ssid[i]; else break;
    }
    return result;
  }
  /**
   * Password. Blank for an open AP. MD5 hash when message is sent back as a response.
   */
  public char[] password = new char[64];
  public void setPassword(String tmp) {
    int len = Math.min(tmp.length(), 64);
    for (int i=0; i<len; i++) {
      password[i] = tmp.charAt(i);
    }
    for (int i=len; i<64; i++) {
      password[i] = 0;
    }
  }
  public String getPassword() {
    String result="";
    for (int i=0; i<64; i++) {
      if (password[i] != 0) result=result+password[i]; else break;
    }
    return result;
  }
  /**
   * WiFi Mode.
   */
  public int mode;
  /**
   * Message acceptance response (sent back to GS).
   */
  public int response;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  for (int i=0; i<32; i++) {
    ssid[i] = (char)dis.readByte();
  }
  for (int i=0; i<64; i++) {
    password[i] = (char)dis.readByte();
  }
  mode = (int)dis.readByte();
  response = (int)dis.readByte();
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
  dos.writeByte(sysId & 0x007F);
  dos.writeByte(componentId & 0x007F);
  dos.writeByte(messageType & 0x00FF);
  dos.writeByte((messageType >> 8) & 0x00FF);
  dos.writeByte((messageType >> 16) & 0x00FF);
  for (int i=0; i<32; i++) {
    dos.writeByte(ssid[i]);
  }
  for (int i=0; i<64; i++) {
    dos.writeByte(password[i]);
  }
  dos.write(mode&0x00FF);
  dos.write(response&0x00FF);
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
return "MAVLINK_MSG_ID_WIFI_CONFIG_AP : " +   "  ssid="+getSsid()
+  "  password="+getPassword()
+  "  mode="+mode
+  "  response="+response
;}

}

