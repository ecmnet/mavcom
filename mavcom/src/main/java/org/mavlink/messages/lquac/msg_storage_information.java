/**
 * Generated class : msg_storage_information
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
 * Class msg_storage_information
 * Information about a storage medium. This message is sent in response to a request with MAV_CMD_REQUEST_MESSAGE and whenever the status of the storage changes (STORAGE_STATUS). Use MAV_CMD_REQUEST_MESSAGE.param2 to indicate the index/id of requested storage: 0 for all, 1 for first, 2 for second, etc.
 **/
public class msg_storage_information extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_STORAGE_INFORMATION = 261;
  private static final long serialVersionUID = MAVLINK_MSG_ID_STORAGE_INFORMATION;
  public msg_storage_information() {
    this(1,1);
}
  public msg_storage_information(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_STORAGE_INFORMATION;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 61;
}

  /**
   * Timestamp (time since system boot).
   */
  public long time_boot_ms;
  /**
   * Total capacity. If storage is not ready (STORAGE_STATUS_READY) value will be ignored.
   */
  public float total_capacity;
  /**
   * Used capacity. If storage is not ready (STORAGE_STATUS_READY) value will be ignored.
   */
  public float used_capacity;
  /**
   * Available storage capacity. If storage is not ready (STORAGE_STATUS_READY) value will be ignored.
   */
  public float available_capacity;
  /**
   * Read speed.
   */
  public float read_speed;
  /**
   * Write speed.
   */
  public float write_speed;
  /**
   * Storage ID (1 for first, 2 for second, etc.)
   */
  public int storage_id;
  /**
   * Number of storage devices
   */
  public int storage_count;
  /**
   * Status of storage
   */
  public int status;
  /**
   * Type of storage
   */
  public int type;
  /**
   * Textual storage name to be used in UI (microSD 1, Internal Memory, etc.) This is a NULL terminated string. If it is exactly 32 characters long, add a terminating NULL. If this string is empty, the generic type is shown to the user.
   */
  public char[] name = new char[32];
  public void setName(String tmp) {
    int len = Math.min(tmp.length(), 32);
    for (int i=0; i<len; i++) {
      name[i] = tmp.charAt(i);
    }
    for (int i=len; i<32; i++) {
      name[i] = 0;
    }
  }
  public String getName() {
    String result="";
    for (int i=0; i<32; i++) {
      if (name[i] != 0) result=result+name[i]; else break;
    }
    return result;
  }
  /**
   * Flags indicating whether this instance is preferred storage for photos, videos, etc.
        Note: Implementations should initially set the flags on the system-default storage id used for saving media (if possible/supported).
        This setting can then be overridden using `MAV_CMD_SET_STORAGE_USAGE`.
        If the media usage flags are not set, a GCS may assume storage ID 1 is the default storage for all media types.
   */
  public int storage_usage;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_boot_ms = (int)dis.readInt()&0x00FFFFFFFF;
  total_capacity = (float)dis.readFloat();
  used_capacity = (float)dis.readFloat();
  available_capacity = (float)dis.readFloat();
  read_speed = (float)dis.readFloat();
  write_speed = (float)dis.readFloat();
  storage_id = (int)dis.readUnsignedByte()&0x00FF;
  storage_count = (int)dis.readUnsignedByte()&0x00FF;
  status = (int)dis.readUnsignedByte()&0x00FF;
  type = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<32; i++) {
    name[i] = (char)dis.readByte();
  }
  storage_usage = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+61];
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
  dos.writeFloat(total_capacity);
  dos.writeFloat(used_capacity);
  dos.writeFloat(available_capacity);
  dos.writeFloat(read_speed);
  dos.writeFloat(write_speed);
  dos.writeByte(storage_id&0x00FF);
  dos.writeByte(storage_count&0x00FF);
  dos.writeByte(status&0x00FF);
  dos.writeByte(type&0x00FF);
  for (int i=0; i<32; i++) {
    dos.writeByte(name[i]);
  }
  dos.writeByte(storage_usage&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 61);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[71] = crcl;
  buffer[72] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_STORAGE_INFORMATION : " +   "  time_boot_ms="+time_boot_ms
+  "  total_capacity="+format((float)total_capacity)
+  "  used_capacity="+format((float)used_capacity)
+  "  available_capacity="+format((float)available_capacity)
+  "  read_speed="+format((float)read_speed)
+  "  write_speed="+format((float)write_speed)
+  "  storage_id="+storage_id
+  "  storage_count="+storage_count
+  "  status="+status
+  "  type="+type
+  "  name="+getName()
+  "  storage_usage="+storage_usage
;}

}

