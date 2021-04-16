/**
 * Generated class : msg_cellular_config
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
 * Class msg_cellular_config
 * Configure cellular modems. This message is re-emitted as an acknowledgement by the modem. The message may also be explicitly requested using MAV_CMD_REQUEST_MESSAGE.
 **/
public class msg_cellular_config extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_CELLULAR_CONFIG = 336;
  private static final long serialVersionUID = MAVLINK_MSG_ID_CELLULAR_CONFIG;
  public msg_cellular_config() {
    this(1,1);
}
  public msg_cellular_config(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_CELLULAR_CONFIG;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 84;
}

  /**
   * Enable/disable LTE. 0: setting unchanged, 1: disabled, 2: enabled. Current setting when sent back as a response.
   */
  public int enable_lte;
  /**
   * Enable/disable PIN on the SIM card. 0: setting unchanged, 1: disabled, 2: enabled. Current setting when sent back as a response.
   */
  public int enable_pin;
  /**
   * PIN sent to the SIM card. Blank when PIN is disabled. Empty when message is sent back as a response.
   */
  public char[] pin = new char[16];
  public void setPin(String tmp) {
    int len = Math.min(tmp.length(), 16);
    for (int i=0; i<len; i++) {
      pin[i] = tmp.charAt(i);
    }
    for (int i=len; i<16; i++) {
      pin[i] = 0;
    }
  }
  public String getPin() {
    String result="";
    for (int i=0; i<16; i++) {
      if (pin[i] != 0) result=result+pin[i]; else break;
    }
    return result;
  }
  /**
   * New PIN when changing the PIN. Blank to leave it unchanged. Empty when message is sent back as a response.
   */
  public char[] new_pin = new char[16];
  public void setNew_pin(String tmp) {
    int len = Math.min(tmp.length(), 16);
    for (int i=0; i<len; i++) {
      new_pin[i] = tmp.charAt(i);
    }
    for (int i=len; i<16; i++) {
      new_pin[i] = 0;
    }
  }
  public String getNew_pin() {
    String result="";
    for (int i=0; i<16; i++) {
      if (new_pin[i] != 0) result=result+new_pin[i]; else break;
    }
    return result;
  }
  /**
   * Name of the cellular APN. Blank to leave it unchanged. Current APN when sent back as a response.
   */
  public char[] apn = new char[32];
  public void setApn(String tmp) {
    int len = Math.min(tmp.length(), 32);
    for (int i=0; i<len; i++) {
      apn[i] = tmp.charAt(i);
    }
    for (int i=len; i<32; i++) {
      apn[i] = 0;
    }
  }
  public String getApn() {
    String result="";
    for (int i=0; i<32; i++) {
      if (apn[i] != 0) result=result+apn[i]; else break;
    }
    return result;
  }
  /**
   * Required PUK code in case the user failed to authenticate 3 times with the PIN. Empty when message is sent back as a response.
   */
  public char[] puk = new char[16];
  public void setPuk(String tmp) {
    int len = Math.min(tmp.length(), 16);
    for (int i=0; i<len; i++) {
      puk[i] = tmp.charAt(i);
    }
    for (int i=len; i<16; i++) {
      puk[i] = 0;
    }
  }
  public String getPuk() {
    String result="";
    for (int i=0; i<16; i++) {
      if (puk[i] != 0) result=result+puk[i]; else break;
    }
    return result;
  }
  /**
   * Enable/disable roaming. 0: setting unchanged, 1: disabled, 2: enabled. Current setting when sent back as a response.
   */
  public int roaming;
  /**
   * Message acceptance response (sent back to GS).
   */
  public int response;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  enable_lte = (int)dis.readUnsignedByte()&0x00FF;
  enable_pin = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<16; i++) {
    pin[i] = (char)dis.readByte();
  }
  for (int i=0; i<16; i++) {
    new_pin[i] = (char)dis.readByte();
  }
  for (int i=0; i<32; i++) {
    apn[i] = (char)dis.readByte();
  }
  for (int i=0; i<16; i++) {
    puk[i] = (char)dis.readByte();
  }
  roaming = (int)dis.readUnsignedByte()&0x00FF;
  response = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+84];
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
  dos.writeByte(enable_lte&0x00FF);
  dos.writeByte(enable_pin&0x00FF);
  for (int i=0; i<16; i++) {
    dos.writeByte(pin[i]);
  }
  for (int i=0; i<16; i++) {
    dos.writeByte(new_pin[i]);
  }
  for (int i=0; i<32; i++) {
    dos.writeByte(apn[i]);
  }
  for (int i=0; i<16; i++) {
    dos.writeByte(puk[i]);
  }
  dos.writeByte(roaming&0x00FF);
  dos.writeByte(response&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 84);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[94] = crcl;
  buffer[95] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_CELLULAR_CONFIG : " +   "  enable_lte="+enable_lte
+  "  enable_pin="+enable_pin
+  "  pin="+getPin()
+  "  new_pin="+getNew_pin()
+  "  apn="+getApn()
+  "  puk="+getPuk()
+  "  roaming="+roaming
+  "  response="+response
;}

public int getId() {
 return MAVLINK_MSG_ID_CELLULAR_CONFIG;
}

}

