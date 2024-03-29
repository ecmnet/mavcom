/**
 * Generated class : msg_msp_vision
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
 * Class msg_msp_vision
 * MSP Vision
 **/
public class msg_msp_vision extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_MSP_VISION = 182;
  private static final long serialVersionUID = MAVLINK_MSG_ID_MSP_VISION;
  public msg_msp_vision() {
    this(1,1);
}
  public msg_msp_vision(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_MSP_VISION;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 85;
}

  /**
   * Timestamp
   */
  public long tms;
  /**
   * X Position
   */
  public float x;
  /**
   * Y Position
   */
  public float y;
  /**
   * Z Position
   */
  public float z;
  /**
   * X Velocity
   */
  public float vx;
  /**
   * Y Velocity
   */
  public float vy;
  /**
   * Z Velocity
   */
  public float vz;
  /**
   * X Acceleration
   */
  public float ax;
  /**
   * Y Acceleration
   */
  public float ay;
  /**
   * Z Acceleration
   */
  public float az;
  /**
   * Heading
   */
  public float h;
  /**
   * Pitch
   */
  public float p;
  /**
   * Roll
   */
  public float r;
  /**
   * X PrecisionOffset
   */
  public float px;
  /**
   * Y PrecisionOffset
   */
  public float py;
  /**
   * Z PrecisionOffset
   */
  public float pz;
  /**
   * Precision orientation
   */
  public float pw;
  /**
   * FPS of mocap system
   */
  public float fps;
  /**
   * Info flags
   */
  public long flags;
  /**
   * Error counter
   */
  public long errors;
  /**
   * Quality of estimation
   */
  public int quality;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  tms = (long)dis.readLong();
  x = (float)dis.readFloat();
  y = (float)dis.readFloat();
  z = (float)dis.readFloat();
  vx = (float)dis.readFloat();
  vy = (float)dis.readFloat();
  vz = (float)dis.readFloat();
  ax = (float)dis.readFloat();
  ay = (float)dis.readFloat();
  az = (float)dis.readFloat();
  h = (float)dis.readFloat();
  p = (float)dis.readFloat();
  r = (float)dis.readFloat();
  px = (float)dis.readFloat();
  py = (float)dis.readFloat();
  pz = (float)dis.readFloat();
  pw = (float)dis.readFloat();
  fps = (float)dis.readFloat();
  flags = (int)dis.readInt()&0x00FFFFFFFF;
  errors = (int)dis.readInt()&0x00FFFFFFFF;
  quality = (int)dis.readUnsignedByte()&0x00FF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+85];
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
  dos.writeLong(tms);
  dos.writeFloat(x);
  dos.writeFloat(y);
  dos.writeFloat(z);
  dos.writeFloat(vx);
  dos.writeFloat(vy);
  dos.writeFloat(vz);
  dos.writeFloat(ax);
  dos.writeFloat(ay);
  dos.writeFloat(az);
  dos.writeFloat(h);
  dos.writeFloat(p);
  dos.writeFloat(r);
  dos.writeFloat(px);
  dos.writeFloat(py);
  dos.writeFloat(pz);
  dos.writeFloat(pw);
  dos.writeFloat(fps);
  dos.writeInt((int)(flags&0x00FFFFFFFF));
  dos.writeInt((int)(errors&0x00FFFFFFFF));
  dos.writeByte(quality&0x00FF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 85);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[95] = crcl;
  buffer[96] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_MSP_VISION : " +   "  tms="+tms
+  "  x="+format((float)x)
+  "  y="+format((float)y)
+  "  z="+format((float)z)
+  "  vx="+format((float)vx)
+  "  vy="+format((float)vy)
+  "  vz="+format((float)vz)
+  "  ax="+format((float)ax)
+  "  ay="+format((float)ay)
+  "  az="+format((float)az)
+  "  h="+format((float)h)
+  "  p="+format((float)p)
+  "  r="+format((float)r)
+  "  px="+format((float)px)
+  "  py="+format((float)py)
+  "  pz="+format((float)pz)
+  "  pw="+format((float)pw)
+  "  fps="+format((float)fps)
+  "  flags="+flags
+  "  errors="+errors
+  "  quality="+quality
;}

}

