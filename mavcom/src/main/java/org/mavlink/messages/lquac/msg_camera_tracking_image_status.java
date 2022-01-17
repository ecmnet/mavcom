/**
 * Generated class : msg_camera_tracking_image_status
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
 * Class msg_camera_tracking_image_status Camera tracking status, sent while in
 * active tracking. Use MAV_CMD_SET_MESSAGE_INTERVAL to define message interval.
 **/
public class msg_camera_tracking_image_status extends MAVLinkMessage {
	public static final int MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS = 275;
	private static final long serialVersionUID = MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS;

	public msg_camera_tracking_image_status() {
		this(1, 1);
	}

	public msg_camera_tracking_image_status(int sysId, int componentId) {
		messageType = MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS;
		this.sysId = sysId;
		this.componentId = componentId;
		payload_length = 31;
	}

	/**
	 * Current tracked point x value if CAMERA_TRACKING_MODE_POINT (normalized 0..1,
	 * 0 is left, 1 is right), NAN if unknown
	 */
	public float point_x;
	/**
	 * Current tracked point y value if CAMERA_TRACKING_MODE_POINT (normalized 0..1,
	 * 0 is top, 1 is bottom), NAN if unknown
	 */
	public float point_y;
	/**
	 * Current tracked radius if CAMERA_TRACKING_MODE_POINT (normalized 0..1, 0 is
	 * image left, 1 is image right), NAN if unknown
	 */
	public float radius;
	/**
	 * Current tracked rectangle top x value if CAMERA_TRACKING_MODE_RECTANGLE
	 * (normalized 0..1, 0 is left, 1 is right), NAN if unknown
	 */
	public float rec_top_x;
	/**
	 * Current tracked rectangle top y value if CAMERA_TRACKING_MODE_RECTANGLE
	 * (normalized 0..1, 0 is top, 1 is bottom), NAN if unknown
	 */
	public float rec_top_y;
	/**
	 * Current tracked rectangle bottom x value if CAMERA_TRACKING_MODE_RECTANGLE
	 * (normalized 0..1, 0 is left, 1 is right), NAN if unknown
	 */
	public float rec_bottom_x;
	/**
	 * Current tracked rectangle bottom y value if CAMERA_TRACKING_MODE_RECTANGLE
	 * (normalized 0..1, 0 is top, 1 is bottom), NAN if unknown
	 */
	public float rec_bottom_y;
	/**
	 * Current tracking status
	 */
	public int tracking_status;
	/**
	 * Current tracking mode
	 */
	public int tracking_mode;
	/**
	 * Defines location of target data
	 */
	public int target_data;

	/**
	 * Decode message with raw data
	 */
	public void decode(LittleEndianDataInputStream dis) throws IOException {
		point_x = (float) dis.readFloat();
		point_y = (float) dis.readFloat();
		radius = (float) dis.readFloat();
		rec_top_x = (float) dis.readFloat();
		rec_top_y = (float) dis.readFloat();
		rec_bottom_x = (float) dis.readFloat();
		rec_bottom_y = (float) dis.readFloat();
		tracking_status = (int) dis.readUnsignedByte() & 0x00FF;
		tracking_mode = (int) dis.readUnsignedByte() & 0x00FF;
		target_data = (int) dis.readUnsignedByte() & 0x00FF;
	}

	/**
	 * Encode message with raw data and other informations
	 */
	public byte[] encode() throws IOException {
		byte[] buffer = new byte[12 + 31];
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
		dos.writeFloat(point_x);
		dos.writeFloat(point_y);
		dos.writeFloat(radius);
		dos.writeFloat(rec_top_x);
		dos.writeFloat(rec_top_y);
		dos.writeFloat(rec_bottom_x);
		dos.writeFloat(rec_bottom_y);
		dos.writeByte(tracking_status & 0x00FF);
		dos.writeByte(tracking_mode & 0x00FF);
		dos.writeByte(target_data & 0x00FF);
		dos.flush();
		byte[] tmp = dos.toByteArray();
		for (int b = 0; b < tmp.length; b++)
			buffer[b] = tmp[b];
		int crc = MAVLinkCRC.crc_calculate_encode(buffer, 31);
		crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
		byte crcl = (byte) (crc & 0x00FF);
		byte crch = (byte) ((crc >> 8) & 0x00FF);
		buffer[41] = crcl;
		buffer[42] = crch;
		dos.close();
		return buffer;
	}

	public String toString() {
		return "MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS : " + "  point_x=" + format((float) point_x) + "  point_y="
				+ format((float) point_y) + "  radius=" + format((float) radius) + "  rec_top_x="
				+ format((float) rec_top_x) + "  rec_top_y=" + format((float) rec_top_y) + "  rec_bottom_x="
				+ format((float) rec_bottom_x) + "  rec_bottom_y=" + format((float) rec_bottom_y) + "  tracking_status="
				+ tracking_status + "  tracking_mode=" + tracking_mode + "  target_data=" + target_data;
	}

}
