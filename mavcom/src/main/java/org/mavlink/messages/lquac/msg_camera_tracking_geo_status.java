/**
 * Generated class : msg_camera_tracking_geo_status
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
 * Class msg_camera_tracking_geo_status Camera tracking status, sent while in
 * active tracking. Use MAV_CMD_SET_MESSAGE_INTERVAL to define message interval.
 **/
public class msg_camera_tracking_geo_status extends MAVLinkMessage {
	public static final int MAVLINK_MSG_ID_CAMERA_TRACKING_GEO_STATUS = 276;
	private static final long serialVersionUID = MAVLINK_MSG_ID_CAMERA_TRACKING_GEO_STATUS;

	public msg_camera_tracking_geo_status() {
		this(1, 1);
	}

	public msg_camera_tracking_geo_status(int sysId, int componentId) {
		messageType = MAVLINK_MSG_ID_CAMERA_TRACKING_GEO_STATUS;
		this.sysId = sysId;
		this.componentId = componentId;
		payload_length = 49;
	}

	/**
	 * Latitude of tracked object
	 */
	public long lat;
	/**
	 * Longitude of tracked object
	 */
	public long lon;
	/**
	 * Altitude of tracked object(AMSL, WGS84)
	 */
	public float alt;
	/**
	 * Horizontal accuracy. NAN if unknown
	 */
	public float h_acc;
	/**
	 * Vertical accuracy. NAN if unknown
	 */
	public float v_acc;
	/**
	 * North velocity of tracked object. NAN if unknown
	 */
	public float vel_n;
	/**
	 * East velocity of tracked object. NAN if unknown
	 */
	public float vel_e;
	/**
	 * Down velocity of tracked object. NAN if unknown
	 */
	public float vel_d;
	/**
	 * Velocity accuracy. NAN if unknown
	 */
	public float vel_acc;
	/**
	 * Distance between camera and tracked object. NAN if unknown
	 */
	public float dist;
	/**
	 * Heading in radians, in NED. NAN if unknown
	 */
	public float hdg;
	/**
	 * Accuracy of heading, in NED. NAN if unknown
	 */
	public float hdg_acc;
	/**
	 * Current tracking status
	 */
	public int tracking_status;

	/**
	 * Decode message with raw data
	 */
	public void decode(LittleEndianDataInputStream dis) throws IOException {
		lat = (int) dis.readInt();
		lon = (int) dis.readInt();
		alt = (float) dis.readFloat();
		h_acc = (float) dis.readFloat();
		v_acc = (float) dis.readFloat();
		vel_n = (float) dis.readFloat();
		vel_e = (float) dis.readFloat();
		vel_d = (float) dis.readFloat();
		vel_acc = (float) dis.readFloat();
		dist = (float) dis.readFloat();
		hdg = (float) dis.readFloat();
		hdg_acc = (float) dis.readFloat();
		tracking_status = (int) dis.readUnsignedByte() & 0x00FF;
	}

	/**
	 * Encode message with raw data and other informations
	 */
	public byte[] encode() throws IOException {
		byte[] buffer = new byte[12 + 49];
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
		dos.writeInt((int) (lat & 0x00FFFFFFFF));
		dos.writeInt((int) (lon & 0x00FFFFFFFF));
		dos.writeFloat(alt);
		dos.writeFloat(h_acc);
		dos.writeFloat(v_acc);
		dos.writeFloat(vel_n);
		dos.writeFloat(vel_e);
		dos.writeFloat(vel_d);
		dos.writeFloat(vel_acc);
		dos.writeFloat(dist);
		dos.writeFloat(hdg);
		dos.writeFloat(hdg_acc);
		dos.writeByte(tracking_status & 0x00FF);
		dos.flush();
		byte[] tmp = dos.toByteArray();
		for (int b = 0; b < tmp.length; b++)
			buffer[b] = tmp[b];
		int crc = MAVLinkCRC.crc_calculate_encode(buffer, 49);
		crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[messageType], crc);
		byte crcl = (byte) (crc & 0x00FF);
		byte crch = (byte) ((crc >> 8) & 0x00FF);
		buffer[59] = crcl;
		buffer[60] = crch;
		dos.close();
		return buffer;
	}

	public String toString() {
		return "MAVLINK_MSG_ID_CAMERA_TRACKING_GEO_STATUS : " + "  lat=" + lat + "  lon=" + lon + "  alt="
				+ format((float) alt) + "  h_acc=" + format((float) h_acc) + "  v_acc=" + format((float) v_acc)
				+ "  vel_n=" + format((float) vel_n) + "  vel_e=" + format((float) vel_e) + "  vel_d="
				+ format((float) vel_d) + "  vel_acc=" + format((float) vel_acc) + "  dist=" + format((float) dist)
				+ "  hdg=" + format((float) hdg) + "  hdg_acc=" + format((float) hdg_acc) + "  tracking_status="
				+ tracking_status;
	}

}
