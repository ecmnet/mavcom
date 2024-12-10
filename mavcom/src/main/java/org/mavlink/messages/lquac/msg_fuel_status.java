/**
 * Generated class : msg_fuel_status
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
 * Class msg_fuel_status
 * Fuel status.
        This message provides "generic" fuel level information for display in a GCS and for triggering failsafes in an autopilot.
        The fuel type and associated units for fields in this message are defined in the enum MAV_FUEL_TYPE.

        The reported `consumed_fuel` and `remaining_fuel` must only be supplied if measured: they must not be inferred from the `maximum_fuel` and the other value.
        A recipient can assume that if these fields are supplied they are accurate.
        If not provided, the recipient can infer `remaining_fuel` from `maximum_fuel` and `consumed_fuel` on the assumption that the fuel was initially at its maximum (this is what battery monitors assume).
        Note however that this is an assumption, and the UI should prompt the user appropriately (i.e. notify user that they should fill the tank before boot).

        This kind of information may also be sent in fuel-specific messages such as BATTERY_STATUS_V2.
        If both messages are sent for the same fuel system, the ids and corresponding information must match.

        This should be streamed (nominally at 0.1 Hz).
 **/
public class msg_fuel_status extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_FUEL_STATUS = 371;
  private static final long serialVersionUID = MAVLINK_MSG_ID_FUEL_STATUS;
  public msg_fuel_status() {
    this(1,1);
}
  public msg_fuel_status(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_FUEL_STATUS;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 26;
}

  /**
   * Capacity when full. Must be provided.
   */
  public float maximum_fuel;
  /**
   * Consumed fuel (measured). This value should not be inferred: if not measured set to NaN. NaN: field not provided.
   */
  public float consumed_fuel;
  /**
   * Remaining fuel until empty (measured). The value should not be inferred: if not measured set to NaN. NaN: field not provided.
   */
  public float remaining_fuel;
  /**
   * Positive value when emptying/using, and negative if filling/replacing. NaN: field not provided.
   */
  public float flow_rate;
  /**
   * Fuel temperature. NaN: field not provided.
   */
  public float temperature;
  /**
   * Fuel type. Defines units for fuel capacity and consumption fields above.
   */
  public long fuel_type;
  /**
   * Fuel ID. Must match ID of other messages for same fuel system, such as BATTERY_STATUS_V2.
   */
  public int id;
  /**
   * Percentage of remaining fuel, relative to full. Values: [0-100], UINT8_MAX: field not provided.
   */
  public int percent_remaining;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  maximum_fuel = (float)dis.readFloat();
  consumed_fuel = (float)dis.readFloat();
  remaining_fuel = (float)dis.readFloat();
  flow_rate = (float)dis.readFloat();
  temperature = (float)dis.readFloat();
  fuel_type = (int)dis.readInt()&0x00FFFFFFFF;
  id = (int)dis.readUnsignedByte()&0x00FF;
  percent_remaining = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+26];
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
  dos.writeFloat(maximum_fuel);
  dos.writeFloat(consumed_fuel);
  dos.writeFloat(remaining_fuel);
  dos.writeFloat(flow_rate);
  dos.writeFloat(temperature);
  dos.writeInt((int)(fuel_type&0x00FFFFFFFF));
  dos.writeByte(id&0x00FF);
  dos.writeByte(percent_remaining&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 26);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[36] = crcl;
  buffer[37] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_FUEL_STATUS : " +   "  maximum_fuel="+format((float)maximum_fuel)
+  "  consumed_fuel="+format((float)consumed_fuel)
+  "  remaining_fuel="+format((float)remaining_fuel)
+  "  flow_rate="+format((float)flow_rate)
+  "  temperature="+format((float)temperature)
+  "  fuel_type="+fuel_type
+  "  id="+id
+  "  percent_remaining="+percent_remaining
;}

}

