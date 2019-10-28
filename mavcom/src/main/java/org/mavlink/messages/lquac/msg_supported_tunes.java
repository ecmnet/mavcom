/**
 * Generated class : msg_supported_tunes
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
 * Class msg_supported_tunes
 * Tune formats supported by vehicle. This should be emitted as response to MAV_CMD_REQUEST_MESSAGE.
 **/
public class msg_supported_tunes extends MAVLinkMessage {
  public static final int MAVLINK_MSG_ID_SUPPORTED_TUNES = 401;
  private static final long serialVersionUID = MAVLINK_MSG_ID_SUPPORTED_TUNES;
  public msg_supported_tunes() {
    this(1,1);
}
  public msg_supported_tunes(int sysId, int componentId) {
    messageType = MAVLINK_MSG_ID_SUPPORTED_TUNES;
    this.sysId = sysId;
    this.componentId = componentId;
    payload_length = 202;
}

  /**
   * System ID
   */
  public int target_system;
  /**
   * Component ID
   */
  public int target_component;
  /**
   * Array of supported tune formats
   */
  public int[] format = new int[200];
/**
 * Decode message with raw data
 */
public void decode(LittleEndianDataInputStream dis) throws IOException {
  target_system = (int)dis.readUnsignedByte()&0x00FF;
  target_component = (int)dis.readUnsignedByte()&0x00FF;
  for (int i=0; i<200; i++) {
    format[i] = (int)dis.readUnsignedByte()&0x00FF;
  }
}
/**
 * Encode message with raw data and other informations
 */
public byte[] encode() throws IOException {
  byte[] buffer = new byte[12+202];
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
  dos.writeByte(target_system&0x00FF);
  dos.writeByte(target_component&0x00FF);
  for (int i=0; i<200; i++) {
    dos.writeByte(format[i]&0x00FF);
  }
  dos.flush();
  byte[] tmp = dos.toByteArray();
  for (int b=0; b<tmp.length; b++) buffer[b]=tmp[b];
  int crc = MAVLinkCRC.crc_calculate_encode(buffer, 202);
  crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
  byte crcl = (byte) (crc & 0x00FF);
  byte crch = (byte) ((crc >> 8) & 0x00FF);
  buffer[212] = crcl;
  buffer[213] = crch;
  dos.close();
  return buffer;
}
public String toString() {
return "MAVLINK_MSG_ID_SUPPORTED_TUNES : " +   "  target_system="+target_system
+  "  target_component="+target_component
+  "  format[0]="+format[0]
+  "  format[1]="+format[1]
+  "  format[2]="+format[2]
+  "  format[3]="+format[3]
+  "  format[4]="+format[4]
+  "  format[5]="+format[5]
+  "  format[6]="+format[6]
+  "  format[7]="+format[7]
+  "  format[8]="+format[8]
+  "  format[9]="+format[9]
+  "  format[10]="+format[10]
+  "  format[11]="+format[11]
+  "  format[12]="+format[12]
+  "  format[13]="+format[13]
+  "  format[14]="+format[14]
+  "  format[15]="+format[15]
+  "  format[16]="+format[16]
+  "  format[17]="+format[17]
+  "  format[18]="+format[18]
+  "  format[19]="+format[19]
+  "  format[20]="+format[20]
+  "  format[21]="+format[21]
+  "  format[22]="+format[22]
+  "  format[23]="+format[23]
+  "  format[24]="+format[24]
+  "  format[25]="+format[25]
+  "  format[26]="+format[26]
+  "  format[27]="+format[27]
+  "  format[28]="+format[28]
+  "  format[29]="+format[29]
+  "  format[30]="+format[30]
+  "  format[31]="+format[31]
+  "  format[32]="+format[32]
+  "  format[33]="+format[33]
+  "  format[34]="+format[34]
+  "  format[35]="+format[35]
+  "  format[36]="+format[36]
+  "  format[37]="+format[37]
+  "  format[38]="+format[38]
+  "  format[39]="+format[39]
+  "  format[40]="+format[40]
+  "  format[41]="+format[41]
+  "  format[42]="+format[42]
+  "  format[43]="+format[43]
+  "  format[44]="+format[44]
+  "  format[45]="+format[45]
+  "  format[46]="+format[46]
+  "  format[47]="+format[47]
+  "  format[48]="+format[48]
+  "  format[49]="+format[49]
+  "  format[50]="+format[50]
+  "  format[51]="+format[51]
+  "  format[52]="+format[52]
+  "  format[53]="+format[53]
+  "  format[54]="+format[54]
+  "  format[55]="+format[55]
+  "  format[56]="+format[56]
+  "  format[57]="+format[57]
+  "  format[58]="+format[58]
+  "  format[59]="+format[59]
+  "  format[60]="+format[60]
+  "  format[61]="+format[61]
+  "  format[62]="+format[62]
+  "  format[63]="+format[63]
+  "  format[64]="+format[64]
+  "  format[65]="+format[65]
+  "  format[66]="+format[66]
+  "  format[67]="+format[67]
+  "  format[68]="+format[68]
+  "  format[69]="+format[69]
+  "  format[70]="+format[70]
+  "  format[71]="+format[71]
+  "  format[72]="+format[72]
+  "  format[73]="+format[73]
+  "  format[74]="+format[74]
+  "  format[75]="+format[75]
+  "  format[76]="+format[76]
+  "  format[77]="+format[77]
+  "  format[78]="+format[78]
+  "  format[79]="+format[79]
+  "  format[80]="+format[80]
+  "  format[81]="+format[81]
+  "  format[82]="+format[82]
+  "  format[83]="+format[83]
+  "  format[84]="+format[84]
+  "  format[85]="+format[85]
+  "  format[86]="+format[86]
+  "  format[87]="+format[87]
+  "  format[88]="+format[88]
+  "  format[89]="+format[89]
+  "  format[90]="+format[90]
+  "  format[91]="+format[91]
+  "  format[92]="+format[92]
+  "  format[93]="+format[93]
+  "  format[94]="+format[94]
+  "  format[95]="+format[95]
+  "  format[96]="+format[96]
+  "  format[97]="+format[97]
+  "  format[98]="+format[98]
+  "  format[99]="+format[99]
+  "  format[100]="+format[100]
+  "  format[101]="+format[101]
+  "  format[102]="+format[102]
+  "  format[103]="+format[103]
+  "  format[104]="+format[104]
+  "  format[105]="+format[105]
+  "  format[106]="+format[106]
+  "  format[107]="+format[107]
+  "  format[108]="+format[108]
+  "  format[109]="+format[109]
+  "  format[110]="+format[110]
+  "  format[111]="+format[111]
+  "  format[112]="+format[112]
+  "  format[113]="+format[113]
+  "  format[114]="+format[114]
+  "  format[115]="+format[115]
+  "  format[116]="+format[116]
+  "  format[117]="+format[117]
+  "  format[118]="+format[118]
+  "  format[119]="+format[119]
+  "  format[120]="+format[120]
+  "  format[121]="+format[121]
+  "  format[122]="+format[122]
+  "  format[123]="+format[123]
+  "  format[124]="+format[124]
+  "  format[125]="+format[125]
+  "  format[126]="+format[126]
+  "  format[127]="+format[127]
+  "  format[128]="+format[128]
+  "  format[129]="+format[129]
+  "  format[130]="+format[130]
+  "  format[131]="+format[131]
+  "  format[132]="+format[132]
+  "  format[133]="+format[133]
+  "  format[134]="+format[134]
+  "  format[135]="+format[135]
+  "  format[136]="+format[136]
+  "  format[137]="+format[137]
+  "  format[138]="+format[138]
+  "  format[139]="+format[139]
+  "  format[140]="+format[140]
+  "  format[141]="+format[141]
+  "  format[142]="+format[142]
+  "  format[143]="+format[143]
+  "  format[144]="+format[144]
+  "  format[145]="+format[145]
+  "  format[146]="+format[146]
+  "  format[147]="+format[147]
+  "  format[148]="+format[148]
+  "  format[149]="+format[149]
+  "  format[150]="+format[150]
+  "  format[151]="+format[151]
+  "  format[152]="+format[152]
+  "  format[153]="+format[153]
+  "  format[154]="+format[154]
+  "  format[155]="+format[155]
+  "  format[156]="+format[156]
+  "  format[157]="+format[157]
+  "  format[158]="+format[158]
+  "  format[159]="+format[159]
+  "  format[160]="+format[160]
+  "  format[161]="+format[161]
+  "  format[162]="+format[162]
+  "  format[163]="+format[163]
+  "  format[164]="+format[164]
+  "  format[165]="+format[165]
+  "  format[166]="+format[166]
+  "  format[167]="+format[167]
+  "  format[168]="+format[168]
+  "  format[169]="+format[169]
+  "  format[170]="+format[170]
+  "  format[171]="+format[171]
+  "  format[172]="+format[172]
+  "  format[173]="+format[173]
+  "  format[174]="+format[174]
+  "  format[175]="+format[175]
+  "  format[176]="+format[176]
+  "  format[177]="+format[177]
+  "  format[178]="+format[178]
+  "  format[179]="+format[179]
+  "  format[180]="+format[180]
+  "  format[181]="+format[181]
+  "  format[182]="+format[182]
+  "  format[183]="+format[183]
+  "  format[184]="+format[184]
+  "  format[185]="+format[185]
+  "  format[186]="+format[186]
+  "  format[187]="+format[187]
+  "  format[188]="+format[188]
+  "  format[189]="+format[189]
+  "  format[190]="+format[190]
+  "  format[191]="+format[191]
+  "  format[192]="+format[192]
+  "  format[193]="+format[193]
+  "  format[194]="+format[194]
+  "  format[195]="+format[195]
+  "  format[196]="+format[196]
+  "  format[197]="+format[197]
+  "  format[198]="+format[198]
+  "  format[199]="+format[199]
;}
}
