/**
 * Generated class : msg_winch_status
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
 * Class msg_winch_status
 * Winch status.
 **/
public class msg_winch_status extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_WINCH_STATUS = 9005;
  private static final long serialVersionUID = MAVLINK_MSG_ID_WINCH_STATUS;
  public msg_winch_status() {
    this(1,1);
}
  public msg_winch_status(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_WINCH_STATUS;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 34;
}

  /**
   * Timestamp (synced to UNIX time or since system boot).
   */
  public long time_usec;
  /**
   * Length of line released. NaN if unknown
   */
  public float line_length;
  /**
   * Speed line is being released or retracted. Positive values if being released, negative values if being retracted, NaN if unknown
   */
  public float speed;
  /**
   * Tension on the line. NaN if unknown
   */
  public float tension;
  /**
   * Voltage of the battery supplying the winch. NaN if unknown
   */
  public float voltage;
  /**
   * Current draw from the winch. NaN if unknown
   */
  public float current;
  /**
   * Status flags
   */
  public long status;
  /**
   * Temperature of the motor. INT16_MAX if unknown
   */
  public int temperature;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_usec = (long)dis.readLong();
  line_length = (float)dis.readFloat();
  speed = (float)dis.readFloat();
  tension = (float)dis.readFloat();
  voltage = (float)dis.readFloat();
  current = (float)dis.readFloat();
  status = (int)dis.readInt()&0x00FFFFFFFF;
  temperature = (int)dis.readShort();
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+34];
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
  dos.writeLong(time_usec);
  dos.writeFloat(line_length);
  dos.writeFloat(speed);
  dos.writeFloat(tension);
  dos.writeFloat(voltage);
  dos.writeFloat(current);
  dos.writeInt((int)(status&0x00FFFFFFFF));
  dos.writeShort(temperature&0x00FFFF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 34);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[44] = crcl;
  buffer[45] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_WINCH_STATUS : " +   "  time_usec="+time_usec
+  "  line_length="+format((float)line_length)
+  "  speed="+format((float)speed)
+  "  tension="+format((float)tension)
+  "  voltage="+format((float)voltage)
+  "  current="+format((float)current)
+  "  status="+status
+  "  temperature="+temperature
;}
}
