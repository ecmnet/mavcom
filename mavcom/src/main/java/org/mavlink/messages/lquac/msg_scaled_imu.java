/**
 * Generated class : msg_scaled_imu
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
 * Class msg_scaled_imu
 * The RAW IMU readings for the usual 9DOF sensor setup. This message should contain the scaled values to the described units
 **/
public class msg_scaled_imu extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_SCALED_IMU = 26;
  private static final long serialVersionUID = MAVLINK_MSG_ID_SCALED_IMU;
  public msg_scaled_imu() {
    this(1,1);
}
  public msg_scaled_imu(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_SCALED_IMU;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 24;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * X acceleration
   */
  public int xacc;
  /**
   * Y acceleration
   */
  public int yacc;
  /**
   * Z acceleration
   */
  public int zacc;
  /**
   * Angular speed around X axis
   */
  public int xgyro;
  /**
   * Angular speed around Y axis
   */
  public int ygyro;
  /**
   * Angular speed around Z axis
   */
  public int zgyro;
  /**
   * X Magnetic field
   */
  public int xmag;
  /**
   * Y Magnetic field
   */
  public int ymag;
  /**
   * Z Magnetic field
   */
  public int zmag;
  /**
   * Temperature, 0: IMU does not provide temperature values. If the IMU is at 0C it must send 1 (0.01C).
   */
  public int temperature;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  xacc = (int)dis.readShort();
  yacc = (int)dis.readShort();
  zacc = (int)dis.readShort();
  xgyro = (int)dis.readShort();
  ygyro = (int)dis.readShort();
  zgyro = (int)dis.readShort();
  xmag = (int)dis.readShort();
  ymag = (int)dis.readShort();
  zmag = (int)dis.readShort();
  temperature = (int)dis.readShort();
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+24];
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
  dos.writeShort(xacc&0x00FFFF);
  dos.writeShort(yacc&0x00FFFF);
  dos.writeShort(zacc&0x00FFFF);
  dos.writeShort(xgyro&0x00FFFF);
  dos.writeShort(ygyro&0x00FFFF);
  dos.writeShort(zgyro&0x00FFFF);
  dos.writeShort(xmag&0x00FFFF);
  dos.writeShort(ymag&0x00FFFF);
  dos.writeShort(zmag&0x00FFFF);
  dos.writeShort(temperature&0x00FFFF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 24);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[34] = crcl;
  buffer[35] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_SCALED_IMU : " +   "  time_boot_ms="+time_boot_ms
+  "  xacc="+xacc
+  "  yacc="+yacc
+  "  zacc="+zacc
+  "  xgyro="+xgyro
+  "  ygyro="+ygyro
+  "  zgyro="+zgyro
+  "  xmag="+xmag
+  "  ymag="+ymag
+  "  zmag="+zmag
+  "  temperature="+temperature
;}

}

