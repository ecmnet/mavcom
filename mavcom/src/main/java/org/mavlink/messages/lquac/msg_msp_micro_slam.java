/**
 * Generated class : msg_msp_micro_slam
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
 * Class msg_msp_micro_slam
 * MSP MICRO SLAM Data encoded in longs
 **/
public class msg_msp_micro_slam extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_MSP_MICRO_SLAM = 184;
  private static final long serialVersionUID = MAVLINK_MSG_ID_MSP_MICRO_SLAM;
  public msg_msp_micro_slam() {
    this(1,1);
}
  public msg_msp_micro_slam(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_MSP_MICRO_SLAM;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 82;
}

  /**
   * Timestamp
   */
  public long tms;
  /**
   * Planned path X
   */
  public float px;
  /**
   * Planned path Y
   */
  public float py;
  /**
   * Planned path Z
   */
  public float pz;
  /**
   * Planned direction XY
   */
  public float pd;
  /**
   * Planned direction YZ
   */
  public float pp;
  /**
   * Planned speed
   */
  public float pv;
  /**
   * Distance to target
   */
  public float md;
  /**
   * Distance to waypoint
   */
  public float mw;
  /**
   * Min.Distance to obstacle
   */
  public float dm;
  /**
   * Obstacle X
   */
  public float ox;
  /**
   * Obstacle Y
   */
  public float oy;
  /**
   * Obstacle Z
   */
  public float oz;
  /**
   * Corrected LPosX
   */
  public float cx;
  /**
   * Corrected LPosY
   */
  public float cy;
  /**
   * Corrected LPosZ
   */
  public float cz;
  /**
   * Quality
   */
  public float quality;
  /**
   * Rate in Hz
   */
  public float fps;
  /**
   * Counter of waypoints
   */
  public long wpcount;
  /**
   * Offboard Flags
   */
  public int flags;
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  tms = (long)dis.readLong();
  px = (float)dis.readFloat();
  py = (float)dis.readFloat();
  pz = (float)dis.readFloat();
  pd = (float)dis.readFloat();
  pp = (float)dis.readFloat();
  pv = (float)dis.readFloat();
  md = (float)dis.readFloat();
  mw = (float)dis.readFloat();
  dm = (float)dis.readFloat();
  ox = (float)dis.readFloat();
  oy = (float)dis.readFloat();
  oz = (float)dis.readFloat();
  cx = (float)dis.readFloat();
  cy = (float)dis.readFloat();
  cz = (float)dis.readFloat();
  quality = (float)dis.readFloat();
  fps = (float)dis.readFloat();
  wpcount = (int)dis.readInt()&0x00FFFFFFFF;
  flags = (int)dis.readUnsignedShort()&0x00FFFF;
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+82];
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
  dos.writeFloat(px);
  dos.writeFloat(py);
  dos.writeFloat(pz);
  dos.writeFloat(pd);
  dos.writeFloat(pp);
  dos.writeFloat(pv);
  dos.writeFloat(md);
  dos.writeFloat(mw);
  dos.writeFloat(dm);
  dos.writeFloat(ox);
  dos.writeFloat(oy);
  dos.writeFloat(oz);
  dos.writeFloat(cx);
  dos.writeFloat(cy);
  dos.writeFloat(cz);
  dos.writeFloat(quality);
  dos.writeFloat(fps);
  dos.writeInt((int)(wpcount&0x00FFFFFFFF));
  dos.writeShort(flags&0x00FFFF);
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 82);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[92] = crcl;
  buffer[93] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_MSP_MICRO_SLAM : " +   "  tms="+tms
+  "  px="+format((float)px)
+  "  py="+format((float)py)
+  "  pz="+format((float)pz)
+  "  pd="+format((float)pd)
+  "  pp="+format((float)pp)
+  "  pv="+format((float)pv)
+  "  md="+format((float)md)
+  "  mw="+format((float)mw)
+  "  dm="+format((float)dm)
+  "  ox="+format((float)ox)
+  "  oy="+format((float)oy)
+  "  oz="+format((float)oz)
+  "  cx="+format((float)cx)
+  "  cy="+format((float)cy)
+  "  cz="+format((float)cz)
+  "  quality="+format((float)quality)
+  "  fps="+format((float)fps)
+  "  wpcount="+wpcount
+  "  flags="+flags
;}

}

