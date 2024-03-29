/**
 * Generated class : msg_msp_micro_grid
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
 * Class msg_msp_micro_grid
 * MSP MICRO GRID Data encoded in longs
 **/
public class msg_msp_micro_grid extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_MSP_MICRO_GRID = 183;
  private static final long serialVersionUID = MAVLINK_MSG_ID_MSP_MICRO_GRID;
  public msg_msp_micro_grid() {
    this(1,1);
}
  public msg_msp_micro_grid(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_MSP_MICRO_GRID;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 233;
}

  /**
   * Encoded Grid data long
   */
  public long[] data = new long[25];
  /**
   * Timestamp
   */
  public long tms;
  /**
   * CenterX
   */
  public float cx;
  /**
   * CenterY
   */
  public float cy;
  /**
   * CenterZ
   */
  public float cz;
  /**
   * Resolution in m
   */
  public float resolution;
  /**
   * Extension in m per direction
   */
  public float extension;
  /**
   * BlockCount
   */
  public long count;
  /**
   * Grid Status
   */
  public int status;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  for (int i=0; i<25; i++) {
    data[i] = (long)dis.readLong();
  }
  tms = (long)dis.readLong();
  cx = (float)dis.readFloat();
  cy = (float)dis.readFloat();
  cz = (float)dis.readFloat();
  resolution = (float)dis.readFloat();
  extension = (float)dis.readFloat();
  count = (int)dis.readInt()&0x00FFFFFFFF;
  status = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+233];
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
  for (int i=0; i<25; i++) {
    dos.writeLong(data[i]);
  }
  dos.writeLong(tms);
  dos.writeFloat(cx);
  dos.writeFloat(cy);
  dos.writeFloat(cz);
  dos.writeFloat(resolution);
  dos.writeFloat(extension);
  dos.writeInt((int)(count&0x00FFFFFFFF));
  dos.writeByte(status&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 233);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[243] = crcl;
  buffer[244] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_MSP_MICRO_GRID : " +   "  data[0]="+data[0]
+  "  data[1]="+data[1]
+  "  data[2]="+data[2]
+  "  data[3]="+data[3]
+  "  data[4]="+data[4]
+  "  data[5]="+data[5]
+  "  data[6]="+data[6]
+  "  data[7]="+data[7]
+  "  data[8]="+data[8]
+  "  data[9]="+data[9]
+  "  data[10]="+data[10]
+  "  data[11]="+data[11]
+  "  data[12]="+data[12]
+  "  data[13]="+data[13]
+  "  data[14]="+data[14]
+  "  data[15]="+data[15]
+  "  data[16]="+data[16]
+  "  data[17]="+data[17]
+  "  data[18]="+data[18]
+  "  data[19]="+data[19]
+  "  data[20]="+data[20]
+  "  data[21]="+data[21]
+  "  data[22]="+data[22]
+  "  data[23]="+data[23]
+  "  data[24]="+data[24]
+  "  tms="+tms
+  "  cx="+format((float)cx)
+  "  cy="+format((float)cy)
+  "  cz="+format((float)cz)
+  "  resolution="+format((float)resolution)
+  "  extension="+format((float)extension)
+  "  count="+count
+  "  status="+status
;}

}

