/**
 * Generated class : msg_request_event
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
 * Class msg_request_event Request one or more events to be (re-)sent. If
 * first_sequence==last_sequence, only a single event is requested. Note that
 * first_sequence can be larger than last_sequence (because the sequence number
 * can wrap). Each sequence will trigger an EVENT or EVENT_ERROR response.
 **/
public class msg_request_event extends MAVLinkMessage {
	public static final int MAVLINK_MSG_ID_REQUEST_EVENT = 412;
	private static final long serialVersionUID = MAVLINK_MSG_ID_REQUEST_EVENT;

	public msg_request_event() {
		this(1, 1);
	}

	public msg_request_event(int sysId, int componentId) {
		messageType = MAVLINK_MSG_ID_REQUEST_EVENT;
		this.sysId = sysId;
		this.componentId = componentId;
		payload_length = 6;
	}

	/**
	 * First sequence number of the requested event.
	 */
	public int first_sequence;
	/**
	 * Last sequence number of the requested event.
	 */
	public int last_sequence;
	/**
	 * System ID
	 */
	public int target_system;
	/**
	 * Component ID
	 */
	public int target_component;

	/**
	 * Decode message with raw data
	 */
	public void decode(LittleEndianDataInputStream dis) throws IOException {
		first_sequence = (int) dis.readUnsignedShort() & 0x00FFFF;
		last_sequence = (int) dis.readUnsignedShort() & 0x00FFFF;
		target_system = (int) dis.readUnsignedByte() & 0x00FF;
		target_component = (int) dis.readUnsignedByte() & 0x00FF;
	}

	/**
	 * Encode message with raw data and other informations
	 */
	public byte[] encode() throws IOException {
		byte[] buffer = new byte[12 + 6];
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
		dos.writeShort(first_sequence & 0x00FFFF);
		dos.writeShort(last_sequence & 0x00FFFF);
		dos.writeByte(target_system & 0x00FF);
		dos.writeByte(target_component & 0x00FF);
		dos.flush();
		byte[] tmp = dos.toByteArray();
		for (int b = 0; b < tmp.length; b++)
			buffer[b] = tmp[b];
		int crc = MAVLinkCRC.crc_calculate_encode(buffer, 6);
		crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
		byte crcl = (byte) (crc & 0x00FF);
		byte crch = (byte) ((crc >> 8) & 0x00FF);
		buffer[16] = crcl;
		buffer[17] = crch;
		dos.close();
		return buffer;
	}

	public String toString() {
		return "MAVLINK_MSG_ID_REQUEST_EVENT : " + "  first_sequence=" + first_sequence + "  last_sequence="
				+ last_sequence + "  target_system=" + target_system + "  target_component=" + target_component;
	}

}
