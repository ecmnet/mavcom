/**
 * Generated class : msg_battery_info
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
 * Class msg_battery_info
 * Battery information that is static, or requires infrequent update.
        This message should requested using MAV_CMD_REQUEST_MESSAGE and/or streamed at very low rate.
        BATTERY_STATUS_V2 is used for higher-rate battery status information.
 **/
public class msg_battery_info extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_BATTERY_INFO = 372;
  private static final long serialVersionUID = MAVLINK_MSG_ID_BATTERY_INFO;
  public msg_battery_info() {
    this(1,1);
}
  public msg_battery_info(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_BATTERY_INFO;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 140;
}

  /**
   * Minimum per-cell voltage when discharging. 0: field not provided.
   */
  public float discharge_minimum_voltage;
  /**
   * Minimum per-cell voltage when charging. 0: field not provided.
   */
  public float charging_minimum_voltage;
  /**
   * Minimum per-cell voltage when resting. 0: field not provided.
   */
  public float resting_minimum_voltage;
  /**
   * Maximum per-cell voltage when charged. 0: field not provided.
   */
  public float charging_maximum_voltage;
  /**
   * Maximum pack continuous charge current. 0: field not provided.
   */
  public float charging_maximum_current;
  /**
   * Battery nominal voltage. Used for conversion between Wh and Ah. 0: field not provided.
   */
  public float nominal_voltage;
  /**
   * Maximum pack discharge current. 0: field not provided.
   */
  public float discharge_maximum_current;
  /**
   * Maximum pack discharge burst current. 0: field not provided.
   */
  public float discharge_maximum_burst_current;
  /**
   * Fully charged design capacity. 0: field not provided.
   */
  public float design_capacity;
  /**
   * Predicted battery capacity when fully charged (accounting for battery degradation). NAN: field not provided.
   */
  public float full_charge_capacity;
  /**
   * Lifetime count of the number of charge/discharge cycles (https://en.wikipedia.org/wiki/Charge_cycle). UINT16_MAX: field not provided.
   */
  public int cycle_count;
  /**
   * Battery weight. 0: field not provided.
   */
  public int weight;
  /**
   * Battery ID
   */
  public int id;
  /**
   * Function of the battery.
   */
  public int battery_function;
  /**
   * Type (chemistry) of the battery.
   */
  public int type;
  /**
   * State of Health (SOH) estimate. Typically 100% at the time of manufacture and will decrease over time and use. -1: field not provided.
   */
  public int state_of_health;
  /**
   * Number of battery cells in series. 0: field not provided.
   */
  public int cells_in_series;
  /**
   * Manufacture date (DDMMYYYY) in ASCII characters, 0 terminated. All 0: field not provided.
   */
  public char[] manufacture_date = new char[9];
  public void setManufacture_date(String tmp) {
    int len = Math.min(tmp.length(), 9);
    for (int i=0; i<len; i++) {
      manufacture_date[i] = tmp.charAt(i);
    }
    for (int i=len; i<9; i++) {
      manufacture_date[i] = 0;
    }
  }
  public String getManufacture_date() {
    String result="";
    for (int i=0; i<9; i++) {
      if (manufacture_date[i] != 0) result=result+manufacture_date[i]; else break;
    }
    return result;
  }
  /**
   * Serial number in ASCII characters, 0 terminated. All 0: field not provided.
   */
  public char[] serial_number = new char[32];
  public void setSerial_number(String tmp) {
    int len = Math.min(tmp.length(), 32);
    for (int i=0; i<len; i++) {
      serial_number[i] = tmp.charAt(i);
    }
    for (int i=len; i<32; i++) {
      serial_number[i] = 0;
    }
  }
  public String getSerial_number() {
    String result="";
    for (int i=0; i<32; i++) {
      if (serial_number[i] != 0) result=result+serial_number[i]; else break;
    }
    return result;
  }
  /**
   * Battery device name. Formatted as manufacturer name then product name, separated with an underscore (in ASCII characters), 0 terminated. All 0: field not provided.
   */
  public char[] name = new char[50];
  public void setName(String tmp) {
    int len = Math.min(tmp.length(), 50);
    for (int i=0; i<len; i++) {
      name[i] = tmp.charAt(i);
    }
    for (int i=len; i<50; i++) {
      name[i] = 0;
    }
  }
  public String getName() {
    String result="";
    for (int i=0; i<50; i++) {
      if (name[i] != 0) result=result+name[i]; else break;
    }
    return result;
  }
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  discharge_minimum_voltage = (float)dis.readFloat();
  charging_minimum_voltage = (float)dis.readFloat();
  resting_minimum_voltage = (float)dis.readFloat();
  charging_maximum_voltage = (float)dis.readFloat();
  charging_maximum_current = (float)dis.readFloat();
  nominal_voltage = (float)dis.readFloat();
  discharge_maximum_current = (float)dis.readFloat();
  discharge_maximum_burst_current = (float)dis.readFloat();
  design_capacity = (float)dis.readFloat();
  full_charge_capacity = (float)dis.readFloat();
  cycle_count = (int)dis.readUnsignedShort()&0x00FFFF;
  weight = (int)dis.readUnsignedShort()&0x00FFFF;
  id = (int)dis.readUnsignedByte()&0x00FF;
  battery_function = (int)dis.readUnsignedByte()&0x00FF;
  type = (int)dis.readUnsignedByte()&0x00FF;
  state_of_health = (int)dis.readUnsignedByte()&0x00FF;
  cells_in_series = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<9; i++) {
    manufacture_date[i] = (char)dis.readByte();
  }
  for (int i=0; i<32; i++) {
    serial_number[i] = (char)dis.readByte();
  }
  for (int i=0; i<50; i++) {
    name[i] = (char)dis.readByte();
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+140];
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
  dos.writeFloat(discharge_minimum_voltage);
  dos.writeFloat(charging_minimum_voltage);
  dos.writeFloat(resting_minimum_voltage);
  dos.writeFloat(charging_maximum_voltage);
  dos.writeFloat(charging_maximum_current);
  dos.writeFloat(nominal_voltage);
  dos.writeFloat(discharge_maximum_current);
  dos.writeFloat(discharge_maximum_burst_current);
  dos.writeFloat(design_capacity);
  dos.writeFloat(full_charge_capacity);
  dos.writeShort(cycle_count&0x00FFFF);
  dos.writeShort(weight&0x00FFFF);
  dos.writeByte(id&0x00FF);
  dos.writeByte(battery_function&0x00FF);
  dos.writeByte(type&0x00FF);
  dos.writeByte(state_of_health&0x00FF);
  dos.writeByte(cells_in_series&0x00FF);
  for (int i=0; i<9; i++) {
    dos.writeByte(manufacture_date[i]);
  }
  for (int i=0; i<32; i++) {
    dos.writeByte(serial_number[i]);
  }
  for (int i=0; i<50; i++) {
    dos.writeByte(name[i]);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 140);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[150] = crcl;
  buffer[151] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_BATTERY_INFO : " +   "  discharge_minimum_voltage="+format((float)discharge_minimum_voltage)
+  "  charging_minimum_voltage="+format((float)charging_minimum_voltage)
+  "  resting_minimum_voltage="+format((float)resting_minimum_voltage)
+  "  charging_maximum_voltage="+format((float)charging_maximum_voltage)
+  "  charging_maximum_current="+format((float)charging_maximum_current)
+  "  nominal_voltage="+format((float)nominal_voltage)
+  "  discharge_maximum_current="+format((float)discharge_maximum_current)
+  "  discharge_maximum_burst_current="+format((float)discharge_maximum_burst_current)
+  "  design_capacity="+format((float)design_capacity)
+  "  full_charge_capacity="+format((float)full_charge_capacity)
+  "  cycle_count="+cycle_count
+  "  weight="+weight
+  "  id="+id
+  "  battery_function="+battery_function
+  "  type="+type
+  "  state_of_health="+state_of_health
+  "  cells_in_series="+cells_in_series
+  "  manufacture_date="+getManufacture_date()
+  "  serial_number="+getSerial_number()
+  "  name="+getName()
;}

}

