/**
 * Generated class : msg_efi_status
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
 * Class msg_efi_status
 * EFI status output
 **/
public class msg_efi_status extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_EFI_STATUS = 225;
  private static final long serialVersionUID = MAVLINK_MSG_ID_EFI_STATUS;
  public msg_efi_status() {
    this(1,1);
}
  public msg_efi_status(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_EFI_STATUS;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 73;
}

  /**
   * ECU index
   */
  public float ecu_index;
  /**
   * RPM
   */
  public float rpm;
  /**
   * Fuel consumed
   */
  public float fuel_consumed;
  /**
   * Fuel flow rate
   */
  public float fuel_flow;
  /**
   * Engine load
   */
  public float engine_load;
  /**
   * Throttle position
   */
  public float throttle_position;
  /**
   * Spark dwell time
   */
  public float spark_dwell_time;
  /**
   * Barometric pressure
   */
  public float barometric_pressure;
  /**
   * Intake manifold pressure(
   */
  public float intake_manifold_pressure;
  /**
   * Intake manifold temperature
   */
  public float intake_manifold_temperature;
  /**
   * Cylinder head temperature
   */
  public float cylinder_head_temperature;
  /**
   * Ignition timing (Crank angle degrees)
   */
  public float ignition_timing;
  /**
   * Injection time
   */
  public float injection_time;
  /**
   * Exhaust gas temperature
   */
  public float exhaust_gas_temperature;
  /**
   * Output throttle
   */
  public float throttle_out;
  /**
   * Pressure/temperature compensation
   */
  public float pt_compensation;
  /**
   * EFI health status
   */
  public int health;
  /**
   * Supply voltage to EFI sparking system.  Zero in this value means "unknown", so if the supply voltage really is zero volts use 0.0001 instead.
   */
  public float ignition_voltage;
  /**
   * Fuel pressure. Zero in this value means "unknown", so if the fuel pressure really is zero kPa use 0.0001 instead.
   */
  public float fuel_pressure;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  ecu_index = (float)dis.readFloat();
  rpm = (float)dis.readFloat();
  fuel_consumed = (float)dis.readFloat();
  fuel_flow = (float)dis.readFloat();
  engine_load = (float)dis.readFloat();
  throttle_position = (float)dis.readFloat();
  spark_dwell_time = (float)dis.readFloat();
  barometric_pressure = (float)dis.readFloat();
  intake_manifold_pressure = (float)dis.readFloat();
  intake_manifold_temperature = (float)dis.readFloat();
  cylinder_head_temperature = (float)dis.readFloat();
  ignition_timing = (float)dis.readFloat();
  injection_time = (float)dis.readFloat();
  exhaust_gas_temperature = (float)dis.readFloat();
  throttle_out = (float)dis.readFloat();
  pt_compensation = (float)dis.readFloat();
  health = (int)dis.readUnsignedByte()&0x00FF;
  ignition_voltage = (float)dis.readFloat();
  fuel_pressure = (float)dis.readFloat();
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+73];
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
  dos.writeFloat(ecu_index);
  dos.writeFloat(rpm);
  dos.writeFloat(fuel_consumed);
  dos.writeFloat(fuel_flow);
  dos.writeFloat(engine_load);
  dos.writeFloat(throttle_position);
  dos.writeFloat(spark_dwell_time);
  dos.writeFloat(barometric_pressure);
  dos.writeFloat(intake_manifold_pressure);
  dos.writeFloat(intake_manifold_temperature);
  dos.writeFloat(cylinder_head_temperature);
  dos.writeFloat(ignition_timing);
  dos.writeFloat(injection_time);
  dos.writeFloat(exhaust_gas_temperature);
  dos.writeFloat(throttle_out);
  dos.writeFloat(pt_compensation);
  dos.writeByte(health&0x00FF);
  dos.writeFloat(ignition_voltage);
  dos.writeFloat(fuel_pressure);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 73);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[83] = crcl;
  buffer[84] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_EFI_STATUS : " +   "  ecu_index="+format((float)ecu_index)
+  "  rpm="+format((float)rpm)
+  "  fuel_consumed="+format((float)fuel_consumed)
+  "  fuel_flow="+format((float)fuel_flow)
+  "  engine_load="+format((float)engine_load)
+  "  throttle_position="+format((float)throttle_position)
+  "  spark_dwell_time="+format((float)spark_dwell_time)
+  "  barometric_pressure="+format((float)barometric_pressure)
+  "  intake_manifold_pressure="+format((float)intake_manifold_pressure)
+  "  intake_manifold_temperature="+format((float)intake_manifold_temperature)
+  "  cylinder_head_temperature="+format((float)cylinder_head_temperature)
+  "  ignition_timing="+format((float)ignition_timing)
+  "  injection_time="+format((float)injection_time)
+  "  exhaust_gas_temperature="+format((float)exhaust_gas_temperature)
+  "  throttle_out="+format((float)throttle_out)
+  "  pt_compensation="+format((float)pt_compensation)
+  "  health="+health
+  "  ignition_voltage="+format((float)ignition_voltage)
+  "  fuel_pressure="+format((float)fuel_pressure)
;}

}

