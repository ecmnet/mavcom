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
 * EFI Status Output
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
    payload_length = 53;
}

  /**
   * ECU Index
   */
  public float ecu_index;
  /**
   * RPM
   */
  public float rpm;
  /**
   * Fuel Consumed (grams)
   */
  public float fuel_consumed;
  /**
   * Fuel Flow Rate (g/min)
   */
  public float fuel_flow;
  /**
   * Engine Load (%)
   */
  public float engine_load;
  /**
   * Throttle Position (%)
   */
  public float throttle_position;
  /**
   * Spark Dwell Time (ms)
   */
  public float spark_dwell_time;
  /**
   * Barometric Pressure (kPa)
   */
  public float barometric_pressure;
  /**
   * Intake Manifold Pressure (kPa)(
   */
  public float intake_manifold_pressure;
  /**
   * Intake Manifold Temperature (degC)
   */
  public float intake_manifold_temperature;
  /**
   * cylinder_head_temperature (degC)
   */
  public float cylinder_head_temperature;
  /**
   * Ignition timing for cylinder i (Crank Angle degrees)
   */
  public float ignition_timing;
  /**
   * Injection time for injector i (ms)
   */
  public float injection_time;
  /**
   * EFI Health status
   */
  public int health;
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
  health = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+53];
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
  dos.writeByte(health&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 53);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[63] = crcl;
  buffer[64] = crch;
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
+  "  health="+health
;}
}
