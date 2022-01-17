/**
 * Generated class : msg_current_event_sequence
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
 * Class msg_current_event_sequence Regular broadcast for the current latest
 * event sequence number for a component. This is used to check for dropped
 * events.
 **/
public class msg_current_event_sequence extends MAVLinkMessage {
	public static final int MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE = 411;
	private static final long serialVersionUID = MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE;

	public msg_current_event_sequence() {
		this(1, 1);
	}

	public msg_current_event_sequence(int sysId, int componentId) {
		messageType = MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE;
		this.sysId = sysId;
		this.componentId = componentId;
		payload_length = 3;
	}

	/**
	 * Sequence number.
	 */
	public int sequence;
	/**
	 * Flag bitset.
	 */
	public int flags;

	/**
	 * Decode message with raw data
	 */
	public void decode(LittleEndianDataInputStream dis) throws IOException {
		sequence = (int) dis.readUnsignedShort() & 0x00FFFF;
		flags = (int) dis.readUnsignedByte() & 0x00FF;
	}

	/**
	 * Encode message with raw data and other informations
	 */
	public byte[] encode() throws IOException {
		byte[] buffer = new byte[12 + 3];
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
		dos.writeShort(sequence & 0x00FFFF);
		dos.writeByte(flags & 0x00FF);
		dos.flush();
		byte[] tmp = dos.toByteArray();
		for (int b = 0; b < tmp.length; b++)
			buffer[b] = tmp[b];
		int crc = MAVLinkCRC.crc_calculate_encode(buffer, 3);
		crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
		byte crcl = (byte) (crc & 0x00FF);
		byte crch = (byte) ((crc >> 8) & 0x00FF);
		buffer[13] = crcl;
		buffer[14] = crch;
		dos.close();
		return buffer;
	}

	public String toString() {
		return "MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE : " + "  sequence=" + sequence + "  flags=" + flags;
	}

}
