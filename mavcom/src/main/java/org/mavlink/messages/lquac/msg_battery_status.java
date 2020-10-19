/**
 * Generated class : msg_battery_status
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
 * Class msg_battery_status
 * Battery information. Updates GCS with flight controller battery status. Smart batteries also use this message, but may additionally send SMART_BATTERY_INFO.
 **/
public class msg_battery_status extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_BATTERY_STATUS = 147;
  private static final long serialVersionUID = MAVLINK_MSG_ID_BATTERY_STATUS;
  public msg_battery_status() {
    this(1,1);
}
  public msg_battery_status(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_BATTERY_STATUS;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 54;
}

  /**
   * Consumed charge, -1: autopilot does not provide consumption estimate
   */
  public long current_consumed;
  /**
   * Consumed energy, -1: autopilot does not provide energy consumption estimate
   */
  public long energy_consumed;
  /**
   * Temperature of the battery. INT16_MAX for unknown temperature.
   */
  public int temperature;
  /**
   * Battery voltage of cells 1 to 10 (see voltages_ext for cells 11-14). Cells in this field above the valid cell count for this battery should have the UINT16_MAX value. If individual cell voltages are unknown or not measured for this battery, then the overall battery voltage should be filled in cell 0, with all others set to UINT16_MAX. If the voltage of the battery is greater than (UINT16_MAX - 1), then cell 0 should be set to (UINT16_MAX - 1), and cell 1 to the remaining voltage. This can be extended to multiple cells if the total voltage is greater than 2 * (UINT16_MAX - 1).
   */
  public int[] voltages = new int[10];
  /**
   * Battery current, -1: autopilot does not measure the current
   */
  public int current_battery;
  /**
   * Battery ID
   */
  public int id;
  /**
   * Function of the battery
   */
  public int battery_function;
  /**
   * Type (chemistry) of the battery
   */
  public int type;
  /**
   * Remaining battery energy. Values: [0-100], -1: autopilot does not estimate the remaining battery.
   */
  public int battery_remaining;
  /**
   * Remaining battery time, 0: autopilot does not provide remaining battery time estimate
   */
  public long time_remaining;
  /**
   * Fault/health indications. These should be set when charge_state is MAV_BATTERY_CHARGE_STATE_FAILED or MAV_BATTERY_CHARGE_STATE_UNHEALTHY (if not, fault reporting is not supported).
   */
  public long fault_bitmask;
  /**
   * Battery voltages for cells 11 to 14. Cells above the valid cell count for this battery should have a value of 0, where zero indicates not supported (note, this is different than for the voltages field and allows empty byte truncation). If the measured value is 0 then 1 should be sent instead.
   */
  public int[] voltages_ext = new int[4];
  /**
   * State for extent of discharge, provided by autopilot for warning or external reactions
   */
  public int charge_state;
  /**
   * Battery mode. Default (0) is that battery mode reporting is not supported or battery is in normal-use mode.
   */
  public int mode;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  current_consumed = (int)dis.readInt();
  energy_consumed = (int)dis.readInt();
  temperature = (int)dis.readShort();
  for (int i=0; i<10; i++) {
    voltages[i] = (int)dis.readUnsignedShort()&0x00FFFF;
  }
  current_battery = (int)dis.readShort();
  id = (int)dis.readUnsignedByte()&0x00FF;
  battery_function = (int)dis.readUnsignedByte()&0x00FF;
  type = (int)dis.readUnsignedByte()&0x00FF;
  battery_remaining = (int)dis.readByte();
  time_remaining = (int)dis.readInt();
  fault_bitmask = (int)dis.readInt()&0x00FFFFFFFF;
  for (int i=0; i<4; i++) {
    voltages_ext[i] = (int)dis.readUnsignedShort()&0x00FFFF;
  }
  charge_state = (int)dis.readUnsignedByte()&0x00FF;
  mode = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+54];
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
  dos.writeInt((int)(current_consumed&0x00FFFFFFFF));
  dos.writeInt((int)(energy_consumed&0x00FFFFFFFF));
  dos.writeShort(temperature&0x00FFFF);
  for (int i=0; i<10; i++) {
    dos.writeShort(voltages[i]&0x00FFFF);
  }
  dos.writeShort(current_battery&0x00FFFF);
  dos.writeByte(id&0x00FF);
  dos.writeByte(battery_function&0x00FF);
  dos.writeByte(type&0x00FF);
  dos.write(battery_remaining&0x00FF);
  dos.writeInt((int)(time_remaining&0x00FFFFFFFF));
  dos.writeInt((int)(fault_bitmask&0x00FFFFFFFF));
  for (int i=0; i<4; i++) {
    dos.writeShort(voltages_ext[i]&0x00FFFF);
  }
  dos.writeByte(charge_state&0x00FF);
  dos.writeByte(mode&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 54);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[64] = crcl;
  buffer[65] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_BATTERY_STATUS : " +   "  current_consumed="+current_consumed
+  "  energy_consumed="+energy_consumed
+  "  temperature="+temperature
+  "  voltages[0]="+voltages[0]
+  "  voltages[1]="+voltages[1]
+  "  voltages[2]="+voltages[2]
+  "  voltages[3]="+voltages[3]
+  "  voltages[4]="+voltages[4]
+  "  voltages[5]="+voltages[5]
+  "  voltages[6]="+voltages[6]
+  "  voltages[7]="+voltages[7]
+  "  voltages[8]="+voltages[8]
+  "  voltages[9]="+voltages[9]
+  "  current_battery="+current_battery
+  "  id="+id
+  "  battery_function="+battery_function
+  "  type="+type
+  "  battery_remaining="+battery_remaining
+  "  time_remaining="+time_remaining
+  "  fault_bitmask="+fault_bitmask
+  "  voltages_ext[0]="+voltages_ext[0]
+  "  voltages_ext[1]="+voltages_ext[1]
+  "  voltages_ext[2]="+voltages_ext[2]
+  "  voltages_ext[3]="+voltages_ext[3]
+  "  charge_state="+charge_state
+  "  mode="+mode
;}
}
