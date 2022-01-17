/**
 * Generated class : msg_param_start_transaction
 * DO NOT MODIFY!
 **/
package org.mavlink.messages.lquac;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.mavlink.IMAVLinkCRC;
import org.mavlink.MAVLinkCRC;
import org.mavlink.io.LittleEndianDataInputStream;
import org.mavlink.io.LittleEndianDataOutputStream;
import org.mavlink.messages.MAVLinkMessage;

/**
 * Class msg_param_start_transaction Request to start a new parameter
 * transaction. Multiple kinds of transport layers can be used to exchange
 * parameters in the transaction (param, param_ext and mavftp). The response
 * (ack) will contain the same message but with a response attached to it.
 **/
public class msg_param_start_transaction extends MAVLinkMessage {
	public static final int MAVLINK_MSG_ID_PARAM_START_TRANSACTION = 328;
	private static final long serialVersionUID = MAVLINK_MSG_ID_PARAM_START_TRANSACTION;

	public msg_param_start_transaction() {
		this(1, 1);
	}

	public msg_param_start_transaction(int sysId, int componentId) {
		messageType = MAVLINK_MSG_ID_PARAM_START_TRANSACTION;
		this.sysId = sysId;
		this.componentId = componentId;
		payload_length = 4;
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
	 * Possible transport layers to set and get parameters via mavlink during a
	 * parameter transaction.
	 */
	public int param_transport;
	/**
	 * Message acceptance response (sent back to GS).
	 */
	public int response;

	/**
	 * Decode message with raw data
	 */
	public void decode(LittleEndianDataInputStream dis) throws IOException {
		target_system = (int) dis.readUnsignedByte() & 0x00FF;
		target_component = (int) dis.readUnsignedByte() & 0x00FF;
		param_transport = (int) dis.readUnsignedByte() & 0x00FF;
		response = (int) dis.readUnsignedByte() & 0x00FF;
	}

	/**
	 * Encode message with raw data and other informations
	 */
	public byte[] encode() throws IOException {
		byte[] buffer = new byte[12 + 4];
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
		dos.writeByte(target_system & 0x00FF);
		dos.writeByte(target_component & 0x00FF);
		dos.writeByte(param_transport & 0x00FF);
		dos.writeByte(response & 0x00FF);
		dos.flush();
		byte[] tmp = dos.toByteArray();
		for (int b = 0; b < tmp.length; b++)
			buffer[b] = tmp[b];
		int crc = MAVLinkCRC.crc_calculate_encode(buffer, 4);
		crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
		byte crcl = (byte) (crc & 0x00FF);
		byte crch = (byte) ((crc >> 8) & 0x00FF);
		buffer[14] = crcl;
		buffer[15] = crch;
		dos.close();
		return buffer;
	}

	public String toString() {
		return "MAVLINK_MSG_ID_PARAM_START_TRANSACTION : " + "  target_system=" + target_system + "  target_component="
				+ target_component + "  param_transport=" + param_transport + "  response=" + response;
	}
}
