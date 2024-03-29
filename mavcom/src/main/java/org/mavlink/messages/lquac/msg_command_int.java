/**
 * Generated class : msg_command_int
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
 * Class msg_command_int
 * Send a command with up to seven parameters to the MAV, where params 5 and 6 are integers and the other values are floats. This is preferred over COMMAND_LONG as it allows the MAV_FRAME to be specified for interpreting positional information, such as altitude. COMMAND_INT is also preferred when sending latitude and longitude data in params 5 and 6, as it allows for greater precision. Param 5 and 6 encode positional data as scaled integers, where the scaling depends on the actual command value. NaN or INT32_MAX may be used in float/integer params (respectively) to indicate optional/default values (e.g. to use the component's current latitude, yaw rather than a specific value). The command microservice is documented at https://mavlink.io/en/services/command.html
 **/
public class msg_command_int extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_COMMAND_INT = 75;
  private static final long serialVersionUID = MAVLINK_MSG_ID_COMMAND_INT;
  public msg_command_int() {
    this(1,1);
}
  public msg_command_int(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_COMMAND_INT;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 35;
}

  /**
   * PARAM1, see MAV_CMD enum
   */
  public float param1;
  /**
   * PARAM2, see MAV_CMD enum
   */
  public float param2;
  /**
   * PARAM3, see MAV_CMD enum
   */
  public float param3;
  /**
   * PARAM4, see MAV_CMD enum
   */
  public float param4;
  /**
   * PARAM5 / local: x position in meters * 1e4, global: latitude in degrees * 10^7
   */
  public long x;
  /**
   * PARAM6 / local: y position in meters * 1e4, global: longitude in degrees * 10^7
   */
  public long y;
  /**
   * PARAM7 / z position: global: altitude in meters (relative or absolute, depending on frame).
   */
  public float z;
  /**
   * The scheduled action for the mission item.
   */
  public int command;
  /**
   * System ID
   */
  public int target_system;
  /**
   * Component ID
   */
  public int target_component;
  /**
   * The coordinate system of the COMMAND.
   */
  public int frame;
  /**
   * Not used.
   */
  public int current;
  /**
   * Not used (set 0).
   */
  public int autocontinue;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  param1 = (float)dis.readFloat();
  param2 = (float)dis.readFloat();
  param3 = (float)dis.readFloat();
  param4 = (float)dis.readFloat();
  x = (int)dis.readInt();
  y = (int)dis.readInt();
  z = (float)dis.readFloat();
  command = (int)dis.readUnsignedShort()&0x00FFFF;
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
  frame = (int)dis.readUnsignedByte()&0x00FF;
  current = (int)dis.readUnsignedByte()&0x00FF;
  autocontinue = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+35];
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
  dos.writeFloat(param1);
  dos.writeFloat(param2);
  dos.writeFloat(param3);
  dos.writeFloat(param4);
  dos.writeInt((int)(x&0x00FFFFFFFF));
  dos.writeInt((int)(y&0x00FFFFFFFF));
  dos.writeFloat(z);
  dos.writeShort(command&0x00FFFF);
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.writeByte(frame&0x00FF);
  dos.writeByte(current&0x00FF);
  dos.writeByte(autocontinue&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 35);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[45] = crcl;
  buffer[46] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_COMMAND_INT : " +   "  param1="+format((float)param1)
+  "  param2="+format((float)param2)
+  "  param3="+format((float)param3)
+  "  param4="+format((float)param4)
+  "  x="+x
+  "  y="+y
+  "  z="+format((float)z)
+  "  command="+command
+  "  target_system="+target_system
+  "  target_component="+target_component
+  "  frame="+frame
+  "  current="+current
+  "  autocontinue="+autocontinue
;}

}

