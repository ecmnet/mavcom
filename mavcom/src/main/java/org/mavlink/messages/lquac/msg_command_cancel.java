/**
 * Generated class : msg_command_cancel
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
 * Class msg_command_cancel Cancel a long running command. The target system
 * should respond with a COMMAND_ACK to the original command with
 * result=MAV_RESULT_CANCELLED if the long running process was cancelled. If it
 * has already completed, the cancel action can be ignored. The cancel action
 * can be retried until some sort of acknowledgement to the original command has
 * been received. The command microservice is documented at
 * https://mavlink.io/en/services/command.html
 **/
public class msg_command_cancel extends MAVLinkMessage {
	public static final int MAVLINK_MSG_ID_COMMAND_CANCEL = 80;
	private static final long serialVersionUID = MAVLINK_MSG_ID_COMMAND_CANCEL;

	public msg_command_cancel() {
		this(1, 1);
	}

	public msg_command_cancel(int sysId, int componentId) {
		messageType = MAVLINK_MSG_ID_COMMAND_CANCEL;
		this.sysId = sysId;
		this.componentId = componentId;
		payload_length = 4;
	}

	/**
	 * Command ID (of command to cancel).
	 */
	public int command;
	/**
	 * System executing long running command. Should not be broadcast (0).
	 */
	public int target_system;
	/**
	 * Component executing long running command.
	 */
	public int target_component;

	/**
	 * Decode message with raw data
	 */
	public void decode(LittleEndianDataInputStream dis) throws IOException {
		command = (int) dis.readUnsignedShort() & 0x00FFFF;
		target_system = (int) dis.readUnsignedByte() & 0x00FF;
		target_component = (int) dis.readUnsignedByte() & 0x00FF;
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
		dos.writeShort(command & 0x00FFFF);
		dos.writeByte(target_system & 0x00FF);
		dos.writeByte(target_component & 0x00FF);
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
		return "MAVLINK_MSG_ID_COMMAND_CANCEL : " + "  command=" + command + "  target_system=" + target_system
				+ "  target_component=" + target_component;
	}

}
