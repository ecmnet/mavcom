/**
 * Generated class : msg_illuminator_status
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
 * Class msg_illuminator_status
 * Illuminator status
 **/
public class msg_illuminator_status extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_ILLUMINATOR_STATUS = 440;
  private static final long serialVersionUID = MAVLINK_MSG_ID_ILLUMINATOR_STATUS;
  public msg_illuminator_status() {
    this(1,1);
}
  public msg_illuminator_status(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_ILLUMINATOR_STATUS;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 35;
}

  /**
   * Time since the start-up of the illuminator in ms
   */
  public long uptime_ms;
  /**
   * Errors
   */
  public long error_status;
  /**
   * Illuminator brightness
   */
  public float brightness;
  /**
   * Illuminator strobing period in seconds
   */
  public float strobe_period;
  /**
   * Illuminator strobing duty cycle
   */
  public float strobe_duty_cycle;
  /**
   * Temperature in Celsius
   */
  public float temp_c;
  /**
   * Minimum strobing period in seconds
   */
  public float min_strobe_period;
  /**
   * Maximum strobing period in seconds
   */
  public float max_strobe_period;
  /**
   * 0: Illuminators OFF, 1: Illuminators ON
   */
  public int enable;
  /**
   * Supported illuminator modes
   */
  public int mode_bitmask;
  /**
   * Illuminator mode
   */
  public int mode;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  uptime_ms = (int)dis.readInt()&0x00FFFFFFFF;
  error_status = (int)dis.readInt()&0x00FFFFFFFF;
  brightness = (float)dis.readFloat();
  strobe_period = (float)dis.readFloat();
  strobe_duty_cycle = (float)dis.readFloat();
  temp_c = (float)dis.readFloat();
  min_strobe_period = (float)dis.readFloat();
  max_strobe_period = (float)dis.readFloat();
  enable = (int)dis.readUnsignedByte()&0x00FF;
  mode_bitmask = (int)dis.readUnsignedByte()&0x00FF;
  mode = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+35];
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
  dos.writeInt((int)(uptime_ms&0x00FFFFFFFF));
  dos.writeInt((int)(error_status&0x00FFFFFFFF));
  dos.writeFloat(brightness);
  dos.writeFloat(strobe_period);
  dos.writeFloat(strobe_duty_cycle);
  dos.writeFloat(temp_c);
  dos.writeFloat(min_strobe_period);
  dos.writeFloat(max_strobe_period);
  dos.writeByte(enable&0x00FF);
  dos.writeByte(mode_bitmask&0x00FF);
  dos.writeByte(mode&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 35);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[45] = crcl;
  buffer[46] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_ILLUMINATOR_STATUS : " +   "  uptime_ms="+uptime_ms
+  "  error_status="+error_status
+  "  brightness="+format((float)brightness)
+  "  strobe_period="+format((float)strobe_period)
+  "  strobe_duty_cycle="+format((float)strobe_duty_cycle)
+  "  temp_c="+format((float)temp_c)
+  "  min_strobe_period="+format((float)min_strobe_period)
+  "  max_strobe_period="+format((float)max_strobe_period)
+  "  enable="+enable
+  "  mode_bitmask="+mode_bitmask
+  "  mode="+mode
;}

}

