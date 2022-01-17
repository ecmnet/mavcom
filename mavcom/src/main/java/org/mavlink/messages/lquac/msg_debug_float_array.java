/**
 * Generated class : msg_debug_float_array
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
 * Class msg_debug_float_array Large debug/prototyping array. The message uses
 * the maximum available payload for data. The array_id and name fields are used
 * to discriminate between messages in code and in user interfaces
 * (respectively). Do not use in production code.
 **/
public class msg_debug_float_array extends MAVLinkMessage {
	public static final int MAVLINK_MSG_ID_DEBUG_FLOAT_ARRAY = 350;
	private static final long serialVersionUID = MAVLINK_MSG_ID_DEBUG_FLOAT_ARRAY;

	public msg_debug_float_array() {
		this(1, 1);
	}

	public msg_debug_float_array(int sysId, int componentId) {
		messageType = MAVLINK_MSG_ID_DEBUG_FLOAT_ARRAY;
		this.sysId = sysId;
		this.componentId = componentId;
		payload_length = 252;
	}

	/**
	 * Timestamp (UNIX Epoch time or time since system boot). The receiving end can
	 * infer timestamp format (since 1.1.1970 or since system boot) by checking for
	 * the magnitude of the number.
	 */
	public long time_usec;
	/**
	 * Unique ID used to discriminate between arrays
	 */
	public int array_id;
	/**
	 * Name, for human-friendly display in a Ground Control Station
	 */
	public char[] name = new char[10];

	public void setName(String tmp) {
		int len = Math.min(tmp.length(), 10);
		for (int i = 0; i < len; i++) {
			name[i] = tmp.charAt(i);
		}
		for (int i = len; i < 10; i++) {
			name[i] = 0;
		}
	}

	public String getName() {
		String result = "";
		for (int i = 0; i < 10; i++) {
			if (name[i] != 0)
				result = result + name[i];
			else
				break;
		}
		return result;
	}

	/**
	 * data
	 */
	public float[] data = new float[58];

	/**
	 * Decode message with raw data
	 */
	public void decode(LittleEndianDataInputStream dis) throws IOException {
		time_usec = (long) dis.readLong();
		array_id = (int) dis.readUnsignedShort() & 0x00FFFF;
		for (int i = 0; i < 10; i++) {
			name[i] = (char) dis.readByte();
		}
		for (int i = 0; i < 58; i++) {
			data[i] = (float) dis.readFloat();
		}
	}

	/**
	 * Encode message with raw data and other informations
	 */
	public byte[] encode() throws IOException {
		byte[] buffer = new byte[12 + 252];
		LittleEndianDataOutputStream dos = new LittleEndianDataOutputStream(new ByteArrayOutputStream());
		dos.writeByte((byte) 0xFD);
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
		dos.writeShort(array_id & 0x00FFFF);
		for (int i = 0; i < 10; i++) {
			dos.writeByte(name[i]);
		}
		for (int i = 0; i < 58; i++) {
			dos.writeFloat(data[i]);
		}
		dos.flush();
		byte[] tmp = dos.toByteArray();
		for (int b = 0; b < tmp.length; b++)
			buffer[b] = tmp[b];
		int crc = MAVLinkCRC.crc_calculate_encode(buffer, 252);
		crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
		byte crcl = (byte) (crc & 0x00FF);
		byte crch = (byte) ((crc >> 8) & 0x00FF);
		buffer[262] = crcl;
		buffer[263] = crch;
		dos.close();
		return buffer;
	}

	public String toString() {
		return "MAVLINK_MSG_ID_DEBUG_FLOAT_ARRAY : " + "  time_usec=" + time_usec + "  array_id=" + array_id + "  name="
				+ getName() + "  data[0]=" + format((float) data[0]) + "  data[1]=" + format((float) data[1])
				+ "  data[2]=" + format((float) data[2]) + "  data[3]=" + format((float) data[3]) + "  data[4]="
				+ format((float) data[4]) + "  data[5]=" + format((float) data[5]) + "  data[6]="
				+ format((float) data[6]) + "  data[7]=" + format((float) data[7]) + "  data[8]="
				+ format((float) data[8]) + "  data[9]=" + format((float) data[9]) + "  data[10]="
				+ format((float) data[10]) + "  data[11]=" + format((float) data[11]) + "  data[12]="
				+ format((float) data[12]) + "  data[13]=" + format((float) data[13]) + "  data[14]="
				+ format((float) data[14]) + "  data[15]=" + format((float) data[15]) + "  data[16]="
				+ format((float) data[16]) + "  data[17]=" + format((float) data[17]) + "  data[18]="
				+ format((float) data[18]) + "  data[19]=" + format((float) data[19]) + "  data[20]="
				+ format((float) data[20]) + "  data[21]=" + format((float) data[21]) + "  data[22]="
				+ format((float) data[22]) + "  data[23]=" + format((float) data[23]) + "  data[24]="
				+ format((float) data[24]) + "  data[25]=" + format((float) data[25]) + "  data[26]="
				+ format((float) data[26]) + "  data[27]=" + format((float) data[27]) + "  data[28]="
				+ format((float) data[28]) + "  data[29]=" + format((float) data[29]) + "  data[30]="
				+ format((float) data[30]) + "  data[31]=" + format((float) data[31]) + "  data[32]="
				+ format((float) data[32]) + "  data[33]=" + format((float) data[33]) + "  data[34]="
				+ format((float) data[34]) + "  data[35]=" + format((float) data[35]) + "  data[36]="
				+ format((float) data[36]) + "  data[37]=" + format((float) data[37]) + "  data[38]="
				+ format((float) data[38]) + "  data[39]=" + format((float) data[39]) + "  data[40]="
				+ format((float) data[40]) + "  data[41]=" + format((float) data[41]) + "  data[42]="
				+ format((float) data[42]) + "  data[43]=" + format((float) data[43]) + "  data[44]="
				+ format((float) data[44]) + "  data[45]=" + format((float) data[45]) + "  data[46]="
				+ format((float) data[46]) + "  data[47]=" + format((float) data[47]) + "  data[48]="
				+ format((float) data[48]) + "  data[49]=" + format((float) data[49]) + "  data[50]="
				+ format((float) data[50]) + "  data[51]=" + format((float) data[51]) + "  data[52]="
				+ format((float) data[52]) + "  data[53]=" + format((float) data[53]) + "  data[54]="
				+ format((float) data[54]) + "  data[55]=" + format((float) data[55]) + "  data[56]="
				+ format((float) data[56]) + "  data[57]=" + format((float) data[57]);
	}

}
