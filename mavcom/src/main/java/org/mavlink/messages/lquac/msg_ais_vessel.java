/**
 * Generated class : msg_ais_vessel
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
 * Class msg_ais_vessel
 * The location and information of an AIS vessel
 **/
public class msg_ais_vessel extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_AIS_VESSEL = 301;
  private static final long serialVersionUID = MAVLINK_MSG_ID_AIS_VESSEL;
  public msg_ais_vessel() {
    this(1,1);
}
  public msg_ais_vessel(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_AIS_VESSEL;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 58;
}

  /**
   * Mobile Marine Service Identifier, 9 decimal digits
   */
  public long MMSI;
  /**
   * Latitude
   */
  public long lat;
  /**
   * Longitude
   */
  public long lon;
  /**
   * Course over ground
   */
  public int COG;
  /**
   * True heading
   */
  public int heading;
  /**
   * Speed over ground
   */
  public int velocity;
  /**
   * Distance from lat/lon location to bow
   */
  public int dimension_bow;
  /**
   * Distance from lat/lon location to stern
   */
  public int dimension_stern;
  /**
   * Time since last communication in seconds
   */
  public int tslc;
  /**
   * Bitmask to indicate various statuses including valid data fields
   */
  public int flags;
  /**
   * Turn rate
   */
  public int turn_rate;
  /**
   * Navigational status
   */
  public int navigational_status;
  /**
   * Type of vessels
   */
  public int type;
  /**
   * Distance from lat/lon location to port side
   */
  public int dimension_port;
  /**
   * Distance from lat/lon location to starboard side
   */
  public int dimension_starboard;
  /**
   * The vessel callsign
   */
  public char[] callsign = new char[7];
  public void setCallsign(String tmp) {
    int len = Math.min(tmp.length(), 7);
    for (int i=0; i<len; i++) {
      callsign[i] = tmp.charAt(i);
    }
    for (int i=len; i<7; i++) {
      callsign[i] = 0;
    }
  }
  public String getCallsign() {
    String result="";
    for (int i=0; i<7; i++) {
      if (callsign[i] != 0) result=result+callsign[i]; else break;
    }
    return result;
  }
  /**
   * The vessel name
   */
  public char[] name = new char[20];
  public void setName(String tmp) {
    int len = Math.min(tmp.length(), 20);
    for (int i=0; i<len; i++) {
      name[i] = tmp.charAt(i);
    }
    for (int i=len; i<20; i++) {
      name[i] = 0;
    }
  }
  public String getName() {
    String result="";
    for (int i=0; i<20; i++) {
      if (name[i] != 0) result=result+name[i]; else break;
    }
    return result;
  }
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  MMSI = (int)dis.readInt()&0x00FFFFFFFF;
  lat = (int)dis.readInt();
  lon = (int)dis.readInt();
  COG = (int)dis.readUnsignedShort()&0x00FFFF;
  heading = (int)dis.readUnsignedShort()&0x00FFFF;
  velocity = (int)dis.readUnsignedShort()&0x00FFFF;
  dimension_bow = (int)dis.readUnsignedShort()&0x00FFFF;
  dimension_stern = (int)dis.readUnsignedShort()&0x00FFFF;
  tslc = (int)dis.readUnsignedShort()&0x00FFFF;
  flags = (int)dis.readUnsignedShort()&0x00FFFF;
  turn_rate = (int)dis.readByte();
  navigational_status = (int)dis.readUnsignedByte()&0x00FF;
  type = (int)dis.readUnsignedByte()&0x00FF;
  dimension_port = (int)dis.readUnsignedByte()&0x00FF;
  dimension_starboard = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<7; i++) {
    callsign[i] = (char)dis.readByte();
  }
  for (int i=0; i<20; i++) {
    name[i] = (char)dis.readByte();
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+58];
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
  dos.writeInt((int)(MMSI&0x00FFFFFFFF));
  dos.writeInt((int)(lat&0x00FFFFFFFF));
  dos.writeInt((int)(lon&0x00FFFFFFFF));
  dos.writeShort(COG&0x00FFFF);
  dos.writeShort(heading&0x00FFFF);
  dos.writeShort(velocity&0x00FFFF);
  dos.writeShort(dimension_bow&0x00FFFF);
  dos.writeShort(dimension_stern&0x00FFFF);
  dos.writeShort(tslc&0x00FFFF);
  dos.writeShort(flags&0x00FFFF);
  dos.write(turn_rate&0x00FF);
  dos.writeByte(navigational_status&0x00FF);
  dos.writeByte(type&0x00FF);
  dos.writeByte(dimension_port&0x00FF);
  dos.writeByte(dimension_starboard&0x00FF);
  for (int i=0; i<7; i++) {
    dos.writeByte(callsign[i]);
  }
  for (int i=0; i<20; i++) {
    dos.writeByte(name[i]);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 58);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[68] = crcl;
  buffer[69] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_AIS_VESSEL : " +   "  MMSI="+MMSI
+  "  lat="+lat
+  "  lon="+lon
+  "  COG="+COG
+  "  heading="+heading
+  "  velocity="+velocity
+  "  dimension_bow="+dimension_bow
+  "  dimension_stern="+dimension_stern
+  "  tslc="+tslc
+  "  flags="+flags
+  "  turn_rate="+turn_rate
+  "  navigational_status="+navigational_status
+  "  type="+type
+  "  dimension_port="+dimension_port
+  "  dimension_starboard="+dimension_starboard
+  "  callsign="+getCallsign()
+  "  name="+getName()
;}

public int getId() {
 return MAVLINK_MSG_ID_AIS_VESSEL;
}

}

