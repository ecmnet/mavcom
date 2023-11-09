/**
 * Generated class : msg_timesync
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
 * Class msg_timesync
 * Time synchronization message.
        The message is used for both timesync requests and responses.
        The request is sent with `ts1=syncing component timestamp` and `tc1=0`, and may be broadcast or targeted to a specific system/component.
        The response is sent with `ts1=syncing component timestamp` (mirror back unchanged), and `tc1=responding component timestamp`, with the `target_system` and `target_component` set to ids of the original request.
        Systems can determine if they are receiving a request or response based on the value of `tc`.
        If the response has `target_system==target_component==0` the remote system has not been updated to use the component IDs and cannot reliably timesync; the requestor may report an error.
        Timestamps are UNIX Epoch time or time since system boot in nanoseconds (the timestamp format can be inferred by checking for the magnitude of the number; generally it doesn't matter as only the offset is used).
        The message sequence is repeated numerous times with results being filtered/averaged to estimate the offset.
 **/
public class msg_timesync extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_TIMESYNC = 111;
  private static final long serialVersionUID = MAVLINK_MSG_ID_TIMESYNC;
  public msg_timesync() {
    this(1,1);
}
  public msg_timesync(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_TIMESYNC;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 18;
}

  /**
   * Time sync timestamp 1. Syncing: 0. Responding: Timestamp of responding component.
   */
  public long tc1;
  /**
   * Time sync timestamp 2. Timestamp of syncing component (mirrored in response).
   */
  public long ts1;
  /**
   * Target system id. Request: 0 (broadcast) or id of specific system. Response must contain system id of the requesting component.
   */
  public int target_system;
  /**
   * Target component id. Request: 0 (broadcast) or id of specific component. Response must contain component id of the requesting component.
   */
  public int target_component;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  tc1 = (long)dis.readLong();
  ts1 = (long)dis.readLong();
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+18];
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
  dos.writeLong(tc1);
  dos.writeLong(ts1);
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 18);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[28] = crcl;
  buffer[29] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_TIMESYNC : " +   "  tc1="+tc1
+  "  ts1="+ts1
+  "  target_system="+target_system
+  "  target_component="+target_component
;}

}

