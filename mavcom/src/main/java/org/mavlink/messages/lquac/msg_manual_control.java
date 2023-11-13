/**
 * Generated class : msg_manual_control
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
 * Class msg_manual_control
 * This message provides an API for manually controlling the vehicle using standard joystick axes nomenclature, along with a joystick-like input device. Unused axes can be disabled and buttons states are transmitted as individual on/off bits of a bitmask
 **/
public class msg_manual_control extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_MANUAL_CONTROL = 69;
  private static final long serialVersionUID = MAVLINK_MSG_ID_MANUAL_CONTROL;
  public msg_manual_control() {
    this(1,1);
}
  public msg_manual_control(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_MANUAL_CONTROL;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 30;
}

  /**
   * X-axis, normalized to the range [-1000,1000]. A value of INT16_MAX indicates that this axis is invalid. Generally corresponds to forward(1000)-backward(-1000) movement on a joystick and the pitch of a vehicle.
   */
  public int x;
  /**
   * Y-axis, normalized to the range [-1000,1000]. A value of INT16_MAX indicates that this axis is invalid. Generally corresponds to left(-1000)-right(1000) movement on a joystick and the roll of a vehicle.
   */
  public int y;
  /**
   * Z-axis, normalized to the range [-1000,1000]. A value of INT16_MAX indicates that this axis is invalid. Generally corresponds to a separate slider movement with maximum being 1000 and minimum being -1000 on a joystick and the thrust of a vehicle. Positive values are positive thrust, negative values are negative thrust.
   */
  public int z;
  /**
   * R-axis, normalized to the range [-1000,1000]. A value of INT16_MAX indicates that this axis is invalid. Generally corresponds to a twisting of the joystick, with counter-clockwise being 1000 and clockwise being -1000, and the yaw of a vehicle.
   */
  public int r;
  /**
   * A bitfield corresponding to the joystick buttons' 0-15 current state, 1 for pressed, 0 for released. The lowest bit corresponds to Button 1.
   */
  public int buttons;
  /**
   * The system to be controlled.
   */
  public int target;
  /**
   * A bitfield corresponding to the joystick buttons' 16-31 current state, 1 for pressed, 0 for released. The lowest bit corresponds to Button 16.
   */
  public int buttons2;
  /**
   * Pitch-only-axis, normalized to the range [-1000,1000]. Generally corresponds to pitch on vehicles with additional degrees of freedom. Valid if bit 0 of enabled_extensions field is set. Set to 0 if invalid.
   */
  public int s;
  /**
   * Roll-only-axis, normalized to the range [-1000,1000]. Generally corresponds to roll on vehicles with additional degrees of freedom. Valid if bit 1 of enabled_extensions field is set. Set to 0 if invalid.
   */
  public int t;
  /**
   * Aux continuous input field 1. Normalized in the range [-1000,1000]. Purpose defined by recipient. Valid data if bit 2 of enabled_extensions field is set. 0 if bit 2 is unset.
   */
  public int aux1;
  /**
   * Aux continuous input field 2. Normalized in the range [-1000,1000]. Purpose defined by recipient. Valid data if bit 3 of enabled_extensions field is set. 0 if bit 3 is unset.
   */
  public int aux2;
  /**
   * Aux continuous input field 3. Normalized in the range [-1000,1000]. Purpose defined by recipient. Valid data if bit 4 of enabled_extensions field is set. 0 if bit 4 is unset.
   */
  public int aux3;
  /**
   * Aux continuous input field 4. Normalized in the range [-1000,1000]. Purpose defined by recipient. Valid data if bit 5 of enabled_extensions field is set. 0 if bit 5 is unset.
   */
  public int aux4;
  /**
   * Aux continuous input field 5. Normalized in the range [-1000,1000]. Purpose defined by recipient. Valid data if bit 6 of enabled_extensions field is set. 0 if bit 6 is unset.
   */
  public int aux5;
  /**
   * Aux continuous input field 6. Normalized in the range [-1000,1000]. Purpose defined by recipient. Valid data if bit 7 of enabled_extensions field is set. 0 if bit 7 is unset.
   */
  public int aux6;
  /**
   * Set bits to 1 to indicate which of the following extension fields contain valid data: bit 0: pitch, bit 1: roll, bit 2: aux1, bit 3: aux2, bit 4: aux3, bit 5: aux4, bit 6: aux5, bit 7: aux6
   */
  public int enabled_extensions;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  x = (int)dis.readShort();
  y = (int)dis.readShort();
  z = (int)dis.readShort();
  r = (int)dis.readShort();
  buttons = (int)dis.readUnsignedShort()&0x00FFFF;
  target = (int)dis.readUnsignedByte()&0x00FF;
  buttons2 = (int)dis.readUnsignedShort()&0x00FFFF;
  s = (int)dis.readShort();
  t = (int)dis.readShort();
  aux1 = (int)dis.readShort();
  aux2 = (int)dis.readShort();
  aux3 = (int)dis.readShort();
  aux4 = (int)dis.readShort();
  aux5 = (int)dis.readShort();
  aux6 = (int)dis.readShort();
  enabled_extensions = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+30];
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
  dos.writeShort(x&0x00FFFF);
  dos.writeShort(y&0x00FFFF);
  dos.writeShort(z&0x00FFFF);
  dos.writeShort(r&0x00FFFF);
  dos.writeShort(buttons&0x00FFFF);
  dos.writeByte(target&0x00FF);
  dos.writeShort(buttons2&0x00FFFF);
  dos.writeShort(s&0x00FFFF);
  dos.writeShort(t&0x00FFFF);
  dos.writeShort(aux1&0x00FFFF);
  dos.writeShort(aux2&0x00FFFF);
  dos.writeShort(aux3&0x00FFFF);
  dos.writeShort(aux4&0x00FFFF);
  dos.writeShort(aux5&0x00FFFF);
  dos.writeShort(aux6&0x00FFFF);
  dos.writeByte(enabled_extensions&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 30);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[40] = crcl;
  buffer[41] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_MANUAL_CONTROL : " +   "  x="+x
+  "  y="+y
+  "  z="+z
+  "  r="+r
+  "  buttons="+buttons
+  "  target="+target
+  "  buttons2="+buttons2
+  "  s="+s
+  "  t="+t
+  "  aux1="+aux1
+  "  aux2="+aux2
+  "  aux3="+aux3
+  "  aux4="+aux4
+  "  aux5="+aux5
+  "  aux6="+aux6
+  "  enabled_extensions="+enabled_extensions
;}

}

