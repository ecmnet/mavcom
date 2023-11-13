/**
 * Generated class : msg_esc_status
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
 * Class msg_esc_status
 * ESC information for higher rate streaming. Recommended streaming rate is ~10 Hz. Information that changes more slowly is sent in ESC_INFO. It should typically only be streamed on high-bandwidth links (i.e. to a companion computer).
 **/
public class msg_esc_status extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_ESC_STATUS = 291;
  private static final long serialVersionUID = MAVLINK_MSG_ID_ESC_STATUS;
  public msg_esc_status() {
    this(1,1);
}
  public msg_esc_status(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_ESC_STATUS;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 57;
}

  /**
   * Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude the number.
   */
  public long time_usec;
  /**
   * Reported motor RPM from each ESC (negative for reverse rotation).
   */
  public long[] rpm = new long[4];
  /**
   * Voltage measured from each ESC.
   */
  public float[] voltage = new float[4];
  /**
   * Current measured from each ESC.
   */
  public float[] current = new float[4];
  /**
   * Index of the first ESC in this message. minValue = 0, maxValue = 60, increment = 4.
   */
  public int index;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  time_usec = (long)dis.readLong();
  for (int i=0; i<4; i++) {
    rpm[i] = (int)dis.readInt();
  }
  for (int i=0; i<4; i++) {
    voltage[i] = (float)dis.readFloat();
  }
  for (int i=0; i<4; i++) {
    current[i] = (float)dis.readFloat();
  }
  index = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+57];
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
  dos.writeLong(time_usec);
  for (int i=0; i<4; i++) {
    dos.writeInt((int)(rpm[i]&0x00FFFFFFFF));
  }
  for (int i=0; i<4; i++) {
    dos.writeFloat(voltage[i]);
  }
  for (int i=0; i<4; i++) {
    dos.writeFloat(current[i]);
  }
  dos.writeByte(index&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 57);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[67] = crcl;
  buffer[68] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_ESC_STATUS : " +   "  time_usec="+time_usec
+  "  rpm[0]="+rpm[0]
+  "  rpm[1]="+rpm[1]
+  "  rpm[2]="+rpm[2]
+  "  rpm[3]="+rpm[3]
+  "  voltage[0]="+format((float)voltage[0])
+  "  voltage[1]="+format((float)voltage[1])
+  "  voltage[2]="+format((float)voltage[2])
+  "  voltage[3]="+format((float)voltage[3])
+  "  current[0]="+format((float)current[0])
+  "  current[1]="+format((float)current[1])
+  "  current[2]="+format((float)current[2])
+  "  current[3]="+format((float)current[3])
+  "  index="+index
;}

}

