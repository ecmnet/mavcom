/**
 * Generated class : msg_component_information
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
 * Class msg_component_information Information about a component. For camera
 * components instead use CAMERA_INFORMATION, and for autopilots additionally
 * use AUTOPILOT_VERSION. Components including GCSes should consider supporting
 * requests of this message via MAV_CMD_REQUEST_MESSAGE.
 **/
public class msg_component_information extends MAVLinkMessage {
	public static final int MAVLINK_MSG_ID_COMPONENT_INFORMATION = 395;
	private static final long serialVersionUID = MAVLINK_MSG_ID_COMPONENT_INFORMATION;

	public msg_component_information() {
		this(1, 1);
	}

	public msg_component_information(int sysId, int componentId) {
		messageType = MAVLINK_MSG_ID_COMPONENT_INFORMATION;
		this.sysId = sysId;
		this.componentId = componentId;
		payload_length = 212;
	}

	/**
	 * Timestamp (time since system boot).
	 */
	public long time_boot_ms;
	/**
	 * CRC32 of the TYPE_GENERAL file (can be used by a GCS for file caching).
	 */
	public long general_metadata_file_crc;
	/**
	 * CRC32 of the TYPE_PERIPHERALS file (can be used by a GCS for file caching).
	 */
	public long peripherals_metadata_file_crc;
	/**
	 * Component definition URI for TYPE_GENERAL. This must be a MAVLink FTP URI and
	 * the file might be compressed with xz.
	 */
	public char[] general_metadata_uri = new char[100];

	public void setGeneral_metadata_uri(String tmp) {
		int len = Math.min(tmp.length(), 100);
		for (int i = 0; i < len; i++) {
			general_metadata_uri[i] = tmp.charAt(i);
		}
		for (int i = len; i < 100; i++) {
			general_metadata_uri[i] = 0;
		}
	}

	public String getGeneral_metadata_uri() {
		String result = "";
		for (int i = 0; i < 100; i++) {
			if (general_metadata_uri[i] != 0)
				result = result + general_metadata_uri[i];
			else
				break;
		}
		return result;
	}

	/**
	 * (Optional) Component definition URI for TYPE_PERIPHERALS. This must be a
	 * MAVLink FTP URI and the file might be compressed with xz.
	 */
	public char[] peripherals_metadata_uri = new char[100];

	public void setPeripherals_metadata_uri(String tmp) {
		int len = Math.min(tmp.length(), 100);
		for (int i = 0; i < len; i++) {
			peripherals_metadata_uri[i] = tmp.charAt(i);
		}
		for (int i = len; i < 100; i++) {
			peripherals_metadata_uri[i] = 0;
		}
	}

	public String getPeripherals_metadata_uri() {
		String result = "";
		for (int i = 0; i < 100; i++) {
			if (peripherals_metadata_uri[i] != 0)
				result = result + peripherals_metadata_uri[i];
			else
				break;
		}
		return result;
	}

	/**
	 * Decode message with raw data
	 */
	public void decode(LittleEndianDataInputStream dis) throws IOException {
		time_boot_ms = (int) dis.readInt() & 0x00FFFFFFFF;
		general_metadata_file_crc = (int) dis.readInt() & 0x00FFFFFFFF;
		peripherals_metadata_file_crc = (int) dis.readInt() & 0x00FFFFFFFF;
		for (int i = 0; i < 100; i++) {
			general_metadata_uri[i] = (char) dis.readByte();
		}
		for (int i = 0; i < 100; i++) {
			peripherals_metadata_uri[i] = (char) dis.readByte();
		}
	}

	/**
	 * Encode message with raw data and other informations
	 */
	public byte[] encode() throws IOException {
		byte[] buffer = new byte[12 + 212];
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
		dos.writeInt((int) (time_boot_ms & 0x00FFFFFFFF));
		dos.writeInt((int) (general_metadata_file_crc & 0x00FFFFFFFF));
		dos.writeInt((int) (peripherals_metadata_file_crc & 0x00FFFFFFFF));
		for (int i = 0; i < 100; i++) {
			dos.writeByte(general_metadata_uri[i]);
		}
		for (int i = 0; i < 100; i++) {
			dos.writeByte(peripherals_metadata_uri[i]);
		}
		dos.flush();
		byte[] tmp = dos.toByteArray();
		for (int b = 0; b < tmp.length; b++)
			buffer[b] = tmp[b];
		int crc = MAVLinkCRC.crc_calculate_encode(buffer, 212);
		crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
		byte crcl = (byte) (crc & 0x00FF);
		byte crch = (byte) ((crc >> 8) & 0x00FF);
		buffer[222] = crcl;
		buffer[223] = crch;
		dos.close();
		return buffer;
	}

	public String toString() {
		return "MAVLINK_MSG_ID_COMPONENT_INFORMATION : " + "  time_boot_ms=" + time_boot_ms
				+ "  general_metadata_file_crc=" + general_metadata_file_crc + "  peripherals_metadata_file_crc="
				+ peripherals_metadata_file_crc + "  general_metadata_uri=" + getGeneral_metadata_uri()
				+ "  peripherals_metadata_uri=" + getPeripherals_metadata_uri();
	}

}
