/**
 * Generated class : msg_scaled_pressure
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
 * Class msg_scaled_pressure
 * The pressure readings for the typical setup of one absolute and differential pressure sensor. The units are as specified in each field.
 **/
public class msg_scaled_pressure extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_SCALED_PRESSURE = 29;
  private static final long serialVersionUID = MAVLINK_MSG_ID_SCALED_PRESSURE;
  public msg_scaled_pressure() {
    this(1,1);
}
  public msg_scaled_pressure(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_SCALED_PRESSURE;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 16;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Absolute pressure
   */
  public float press_abs;
  /**
   * Differential pressure 1
   */
  public float press_diff;
  /**
   * Absolute pressure temperature
   */
  public int temperature;
  /**
   * Differential pressure temperature (0, if not available). Report values of 0 (or 1) as 1 cdegC.
   */
  public int temperature_press_diff;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  press_abs = (float)dis.readFloat();
  press_diff = (float)dis.readFloat();
  temperature = (int)dis.readShort();
  temperature_press_diff = (int)dis.readShort();
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+16];
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
  dos.writeFloat(press_abs);
  dos.writeFloat(press_diff);
  dos.writeShort(temperature&0x00FFFF);
  dos.writeShort(temperature_press_diff&0x00FFFF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 16);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[26] = crcl;
  buffer[27] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_SCALED_PRESSURE : " +   "  time_boot_ms="+time_boot_ms
+  "  press_abs="+format((float)press_abs)
+  "  press_diff="+format((float)press_diff)
+  "  temperature="+temperature
+  "  temperature_press_diff="+temperature_press_diff
;}

public int getId() {
 return MAVLINK_MSG_ID_SCALED_PRESSURE;
}

}

