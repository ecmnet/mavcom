/**
 * Generated class : msg_param_commit_transaction
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
 * Class msg_param_commit_transaction Request to end the current parameter
 * transaction. This message will have effect only if a transaction was
 * previously opened using the PARAM_START_TRANSACTION message. The response
 * will contain the same message but with a response attached to it. The
 * response can either be a success/failure or and in progress in case the
 * receiving side takes some time to apply the parameters.
 **/
public class msg_param_commit_transaction extends MAVLinkMessage {
	public static final int MAVLINK_MSG_ID_PARAM_COMMIT_TRANSACTION = 329;
	private static final long serialVersionUID = MAVLINK_MSG_ID_PARAM_COMMIT_TRANSACTION;

	public msg_param_commit_transaction() {
		this(1, 1);
	}

	public msg_param_commit_transaction(int sysId, int componentId) {
		messageType = MAVLINK_MSG_ID_PARAM_COMMIT_TRANSACTION;
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
	 * Commit or cancel an ongoing transaction.
	 */
	public int param_action;
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
		param_action = (int) dis.readUnsignedByte() & 0x00FF;
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
		dos.writeByte(param_action & 0x00FF);
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
		return "MAVLINK_MSG_ID_PARAM_COMMIT_TRANSACTION : " + "  target_system=" + target_system + "  target_component="
				+ target_component + "  param_action=" + param_action + "  response=" + response;
	}
}
