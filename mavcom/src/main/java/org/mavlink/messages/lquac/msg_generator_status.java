/**
 * Generated class : msg_generator_status
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
 * Class msg_generator_status
 * Telemetry of power generation system. Alternator or mechanical generator.
 **/
public class msg_generator_status extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_GENERATOR_STATUS = 373;
  private static final long serialVersionUID = MAVLINK_MSG_ID_GENERATOR_STATUS;
  public msg_generator_status() {
    this(1,1);
}
  public msg_generator_status(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_GENERATOR_STATUS;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 42;
}

  /**
   * Status flags.
   */
  public long status;
  /**
   * Current into/out of battery. Positive for out. Negative for in. NaN: field not provided.
   */
  public float battery_current;
  /**
   * Current going to the UAV. If battery current not available this is the DC current from the generator. Positive for out. Negative for in. NaN: field not provided
   */
  public float load_current;
  /**
   * The power being generated. NaN: field not provided
   */
  public float power_generated;
  /**
   * Voltage of the bus seen at the generator, or battery bus if battery bus is controlled by generator and at a different voltage to main bus.
   */
  public float bus_voltage;
  /**
   * The target battery current. Positive for out. Negative for in. NaN: field not provided
   */
  public float bat_current_setpoint;
  /**
   * Seconds this generator has run since it was rebooted. UINT32_MAX: field not provided.
   */
  public long runtime;
  /**
   * Seconds until this generator requires maintenance.  A negative value indicates maintenance is past-due. INT32_MAX: field not provided.
   */
  public long time_until_maintenance;
  /**
   * Speed of electrical generator or alternator. UINT16_MAX: field not provided.
   */
  public int generator_speed;
  /**
   * The temperature of the rectifier or power converter. INT16_MAX: field not provided.
   */
  public int rectifier_temperature;
  /**
   * The temperature of the mechanical motor, fuel cell core or generator. INT16_MAX: field not provided.
   */
  public int generator_temperature;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  status = (long)dis.readLong();
  battery_current = (float)dis.readFloat();
  load_current = (float)dis.readFloat();
  power_generated = (float)dis.readFloat();
  bus_voltage = (float)dis.readFloat();
  bat_current_setpoint = (float)dis.readFloat();
  runtime = (int)dis.readInt()&0x00FFFFFFFF;
  time_until_maintenance = (int)dis.readInt();
  generator_speed = (int)dis.readUnsignedShort()&0x00FFFF;
  rectifier_temperature = (int)dis.readShort();
  generator_temperature = (int)dis.readShort();
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+42];
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
  dos.writeLong(status);
  dos.writeFloat(battery_current);
  dos.writeFloat(load_current);
  dos.writeFloat(power_generated);
  dos.writeFloat(bus_voltage);
  dos.writeFloat(bat_current_setpoint);
  dos.writeInt((int)(runtime&0x00FFFFFFFF));
  dos.writeInt((int)(time_until_maintenance&0x00FFFFFFFF));
  dos.writeShort(generator_speed&0x00FFFF);
  dos.writeShort(rectifier_temperature&0x00FFFF);
  dos.writeShort(generator_temperature&0x00FFFF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 42);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[52] = crcl;
  buffer[53] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_GENERATOR_STATUS : " +   "  status="+status
+  "  battery_current="+format((float)battery_current)
+  "  load_current="+format((float)load_current)
+  "  power_generated="+format((float)power_generated)
+  "  bus_voltage="+format((float)bus_voltage)
+  "  bat_current_setpoint="+format((float)bat_current_setpoint)
+  "  runtime="+runtime
+  "  time_until_maintenance="+time_until_maintenance
+  "  generator_speed="+generator_speed
+  "  rectifier_temperature="+rectifier_temperature
+  "  generator_temperature="+generator_temperature
;}
}
