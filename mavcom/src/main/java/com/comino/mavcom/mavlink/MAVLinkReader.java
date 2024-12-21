/****************************************************************************
 *
 *   Copyright (c) 2017,2018 Eike Mansfeld ecm@gmx.de. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 ****************************************************************************/

package com.comino.mavcom.mavlink;

import java.util.Arrays;
import java.util.Vector;

import org.mavlink.IMAVLinkCRC;
import org.mavlink.IMAVLinkMessage;
import org.mavlink.MAVLinkCRC;
import org.mavlink.messages.MAVLinkMessage;
import org.mavlink.messages.MAVLinkMessageFactory;

import us.ihmc.log.LogTools;


public class MAVLinkReader {

	private static final byte MAVLINK_IFLAG_SIGNED = 0x01;

	private static int MAVLINK_SIGNATURE_BLOCK_LEN = 13;
	private static int MAVLINK_MAX_PAYLOAD_SIZE = 255;

	private enum t_parser_state {
		MAVLINK_PARSE_STATE_IDLE, MAVLINK_PARSE_STATE_GOT_STX, MAVLINK_PARSE_STATE_GOT_LENGTH,
		MAVLINK_PARSE_STATE_GOT_INCOMPAT_FLAGS, MAVLINK_PARSE_STATE_GOT_COMPAT_FLAGS, MAVLINK_PARSE_STATE_GOT_SEQ,
		MAVLINK_PARSE_STATE_GOT_SYSID, MAVLINK_PARSE_STATE_GOT_COMPID, MAVLINK_PARSE_STATE_GOT_MSGID1,
		MAVLINK_PARSE_STATE_GOT_MSGID2, MAVLINK_PARSE_STATE_GOT_MSGID3, MAVLINK_PARSE_STATE_GOT_PAYLOAD,
		MAVLINK_PARSE_STATE_GOT_CRC1, MAVLINK_PARSE_STATE_GOT_BAD_CRC1, MAVLINK_PARSE_STATE_SIGNATURE_WAIT
	};

	private enum mavlink_framing_t {
		MAVLINK_FRAMING_INCOMPLETE, MAVLINK_FRAMING_OK, MAVLINK_FRAMING_BAD_CRC, MAVLINK_FRAMING_BAD_SIGNATURE,
		MAVLINK_FRAMING_BAD_SEQUENCE,
	};

	private t_parser_state state = t_parser_state.MAVLINK_PARSE_STATE_IDLE;
	private RxMsg rxmsg = new RxMsg();

	private final int[] lastPacket = new int[256];

	private int packet_lost = 0;
	private int id;

	/**
	 * MAVLink messages received
	 */
	protected final Vector<MAVLinkMessage> packets = new Vector<MAVLinkMessage>(50);

	private volatile int lengthToRead = 0;
	private volatile boolean noCRCCheck = false;

	public MAVLinkReader(int id) {
		this(id, false);
	}

	public MAVLinkReader(int id, boolean noCRCCheck) {
		this.id = id;
		this.noCRCCheck = noCRCCheck;
		for (int i = 0; i < lastPacket.length; i++) {
			lastPacket[i] = -1;
		}
		if (noCRCCheck)
			LogTools.info("MAVLinkReader2 " + id + " started without CRC");
		else
			LogTools.info("MAVLinkReader2 " + id + " started");
	}

	/**
	 * @return the number of unread messages
	 */
	public int nbUnreadMessages() {
		return packets.size();
	}

	/**
	 * @return the protocol start tag
	 */
	public int getProtocol() {
		return rxmsg.start;
	}

	public void put(byte buf[], int len) {
		for (int i = 0; i < len; i++)
			readMavLinkMessageFromBuffer(buf[i]);
	}

	public void put(int c) {
		readMavLinkMessageFromBuffer(c);
	}

	public MAVLinkMessage getNextMessage(byte buf[], int len) {
		for (int i = 0; i < len; i++)
			readMavLinkMessageFromBuffer(buf[i]);

		MAVLinkMessage msg = null;
		if (packets.size() > 0) {
			msg = packets.remove(0);
		}
		return msg;
	}

	public MAVLinkMessage getNextMessage() {
		MAVLinkMessage msg = null;
		if (packets.size() > 0) {
			msg = packets.remove(0);
		}
		return msg;
	}

	private int c = 0;
	
//	private byte[] testbuffer = new byte[300];
//	private int testcount = 0;

	private boolean readMavLinkMessageFromBuffer(int v) {
		try {

			c = v & 0x000000FF;
			// System.out.println(state+":"+byteToHex(c));
			
//			testbuffer[testcount++] = (byte)c;

			switch (state) {
			case MAVLINK_PARSE_STATE_IDLE:
//				testcount = 1;
				if ((byte) c == IMAVLinkMessage.MAVPROT_PACKET_START_V20) {
					rxmsg.clear();
					rxmsg.start = IMAVLinkMessage.MAVPROT_PACKET_START_V20;
					state = t_parser_state.MAVLINK_PARSE_STATE_GOT_STX;
					return true;
				}
				if ((byte) c == IMAVLinkMessage.MAVPROT_PACKET_START_V10) {
					rxmsg.clear();
					rxmsg.start = IMAVLinkMessage.MAVPROT_PACKET_START_V10;
					state = t_parser_state.MAVLINK_PARSE_STATE_GOT_STX;
					return true;
				}
				break;
			case MAVLINK_PARSE_STATE_GOT_STX:
		//		Arrays.fill(testbuffer, 2,299 ,(byte) 0);
				Arrays.fill(rxmsg.rawData, 0,MAVLINK_MAX_PAYLOAD_SIZE, (byte) 0);
				rxmsg.len = c;
				lengthToRead = 0;
				rxmsg.crc = MAVLinkCRC.crc_accumulate((byte) c, rxmsg.crc);
				if (rxmsg.start == IMAVLinkMessage.MAVPROT_PACKET_START_V10) {
					state = t_parser_state.MAVLINK_PARSE_STATE_GOT_COMPAT_FLAGS;
				}
				else
					state = t_parser_state.MAVLINK_PARSE_STATE_GOT_LENGTH;
				break;
			case MAVLINK_PARSE_STATE_GOT_LENGTH:
				rxmsg.incompat = c;
				rxmsg.crc = MAVLinkCRC.crc_accumulate((byte) c, rxmsg.crc);
				state = t_parser_state.MAVLINK_PARSE_STATE_GOT_INCOMPAT_FLAGS;
				break;
			case MAVLINK_PARSE_STATE_GOT_INCOMPAT_FLAGS:
				rxmsg.compat = c;
				rxmsg.crc = MAVLinkCRC.crc_accumulate((byte) c, rxmsg.crc);
				state = t_parser_state.MAVLINK_PARSE_STATE_GOT_COMPAT_FLAGS;
				break;
			case MAVLINK_PARSE_STATE_GOT_COMPAT_FLAGS:
				rxmsg.packet = c;
				rxmsg.crc = MAVLinkCRC.crc_accumulate((byte) c, rxmsg.crc);
				state = t_parser_state.MAVLINK_PARSE_STATE_GOT_SEQ;
				break;
			case MAVLINK_PARSE_STATE_GOT_SEQ:
				rxmsg.sysId = c;
				rxmsg.crc = MAVLinkCRC.crc_accumulate((byte) c, rxmsg.crc);
				state = t_parser_state.MAVLINK_PARSE_STATE_GOT_SYSID;
				break;
			case MAVLINK_PARSE_STATE_GOT_SYSID:
				rxmsg.componentId = c;
				rxmsg.crc = MAVLinkCRC.crc_accumulate((byte) c, rxmsg.crc);
				state = t_parser_state.MAVLINK_PARSE_STATE_GOT_COMPID;
				break;
			case MAVLINK_PARSE_STATE_GOT_COMPID:
				rxmsg.msgId = c;
				rxmsg.crc = MAVLinkCRC.crc_accumulate((byte) c, rxmsg.crc);
				if (((byte)rxmsg.start) == IMAVLinkMessage.MAVPROT_PACKET_START_V10) {
					state = t_parser_state.MAVLINK_PARSE_STATE_GOT_MSGID3;
				}else 
					state = t_parser_state.MAVLINK_PARSE_STATE_GOT_MSGID1;
				break;
			case MAVLINK_PARSE_STATE_GOT_MSGID1:
				rxmsg.msgId |= c << 8;
				rxmsg.crc = MAVLinkCRC.crc_accumulate((byte) c, rxmsg.crc);
				state = t_parser_state.MAVLINK_PARSE_STATE_GOT_MSGID2;
				break;
			case MAVLINK_PARSE_STATE_GOT_MSGID2:
				rxmsg.msgId |= c << 16;
				rxmsg.crc = MAVLinkCRC.crc_accumulate((byte) c, rxmsg.crc);
				state = t_parser_state.MAVLINK_PARSE_STATE_GOT_MSGID3;
				break;
			case MAVLINK_PARSE_STATE_GOT_MSGID3:
				rxmsg.rawData[lengthToRead] = (byte) c;
				rxmsg.crc = MAVLinkCRC.crc_accumulate((byte) c, rxmsg.crc);
				if (++lengthToRead >= rxmsg.len) {
					// clear some additional bytes of the payload buffer
					state = t_parser_state.MAVLINK_PARSE_STATE_GOT_PAYLOAD;
				}
				break;
			case MAVLINK_PARSE_STATE_GOT_PAYLOAD:
				try {
					if (IMAVLinkCRC.MAVLINK_EXTRA_CRC)
						rxmsg.crc = MAVLinkCRC.crc_accumulate((byte) IMAVLinkCRC.MAVLINK_MESSAGE_CRCS[rxmsg.msgId],
								rxmsg.crc);
				} catch (Exception e) {
					state = t_parser_state.MAVLINK_PARSE_STATE_GOT_BAD_CRC1;
				}

				if (c != (rxmsg.crc & 0x00FF))
					state = t_parser_state.MAVLINK_PARSE_STATE_GOT_BAD_CRC1;
				else
					state = t_parser_state.MAVLINK_PARSE_STATE_GOT_CRC1;
				break;
			case MAVLINK_PARSE_STATE_GOT_BAD_CRC1:
			case MAVLINK_PARSE_STATE_GOT_CRC1:
				if ((state == t_parser_state.MAVLINK_PARSE_STATE_GOT_BAD_CRC1 || c != ((int) (rxmsg.crc >> 8) & 0x00FF))
						&& !noCRCCheck) {

					switch (rxmsg.msgId) {
					case 332: // ignore CRC of Trajectory
						rxmsg.msg_received = mavlink_framing_t.MAVLINK_FRAMING_OK;
						break;
					default:
						rxmsg.msg_received = mavlink_framing_t.MAVLINK_FRAMING_BAD_CRC;
						// System.out.println("BadCRC: "+rxmsg.msgId+":"+rxmsg.len);
						break;
					}
				} else
					rxmsg.msg_received = mavlink_framing_t.MAVLINK_FRAMING_OK;

				if ((rxmsg.incompat & MAVLINK_IFLAG_SIGNED) == MAVLINK_IFLAG_SIGNED) {
					rxmsg.msg_received = mavlink_framing_t.MAVLINK_FRAMING_INCOMPLETE;
					rxmsg.signature_wait = MAVLINK_SIGNATURE_BLOCK_LEN;
					state = t_parser_state.MAVLINK_PARSE_STATE_SIGNATURE_WAIT;
				} else {
					state = t_parser_state.MAVLINK_PARSE_STATE_IDLE;
				}

				if (rxmsg.msg_received == mavlink_framing_t.MAVLINK_FRAMING_OK) {
					
					
			//		MAVLinkMessage msg = MAVLinkMessagePool.getInstance().checkout(rxmsg.msgId, rxmsg.sysId, rxmsg.componentId,rxmsg.rawData);
					MAVLinkMessage msg = MAVLinkMessageFactory.getMessage(rxmsg.msgId, rxmsg.sysId, rxmsg.componentId,rxmsg.rawData);
						
//					if(id==1) {
//					printTestBuffer();
//					System.out.println(msg);
//					}
					
					if (msg != null && (checkPacket(rxmsg.sysId, rxmsg.packet))) {
						msg.isValid = true;
						msg.packet = rxmsg.packet;
						packets.addElement(msg);
						synchronized (this) {
							notify();
						}
						// System.out.println("added: "+rxmsg.packet+":"+msg);
					} else {
						packet_lost++;
						// System.out.println("Packet check failed: "+rxmsg);
					}
				} else {
					// System.out.println("Framing not ok: "+rxmsg);
					packet_lost++;
				}
				break;

			case MAVLINK_PARSE_STATE_SIGNATURE_WAIT:
				rxmsg.signature[MAVLINK_SIGNATURE_BLOCK_LEN - rxmsg.signature_wait] = (byte) c;
				rxmsg.signature_wait--;
				if (rxmsg.signature_wait == 0) {
					// check signature here
					// ...
					state = t_parser_state.MAVLINK_PARSE_STATE_IDLE;
					if (rxmsg.msg_received == mavlink_framing_t.MAVLINK_FRAMING_OK) {
					//	MAVLinkMessage msg = MAVLinkMessagePool.getInstance().checkout(rxmsg.msgId, rxmsg.sysId, rxmsg.componentId,rxmsg.rawData);
						MAVLinkMessage msg = MAVLinkMessageFactory.getMessage(rxmsg.msgId, rxmsg.sysId,rxmsg.componentId, rxmsg.rawData);
						if (msg != null && checkPacket(rxmsg.sysId, rxmsg.packet)) {
							msg.packet = rxmsg.packet;
							packets.addElement(msg);
							synchronized (this) {
								notify();
							}
							// System.out.println("added: "+rxmsg.packet+":"+msg);
						} else {
							packet_lost++;
						}
					} else {
						packet_lost++;
					}
				}
				break;
			default:
				break;
			}
			return true;
		} catch (Exception io) {
			io.printStackTrace();
			state = t_parser_state.MAVLINK_PARSE_STATE_IDLE;
			return false;
		}
	}

	public int getLostPackages() {
		return packet_lost;
	}

	/**
	 * Check if we don't lost messages...
	 *
	 * @param sequence current sequence
	 * @return true if we don't lost messages
	 */
	protected boolean checkPacket(int sysId, int packet) {
		// boolean check = false;

		if (sysId != 1)
			return true;

		if (lastPacket[sysId] == -1) {
			// it is the first message read
			// check = true;
		}

		// if(packet==255) {
		// packet = lastPacket[sysId] + 1;
		// check = true;
		// }

		if (lastPacket[sysId] < packet) {
			if (packet - lastPacket[sysId] == 1) {
				// check = true;
			}
		} else
		// We have reached the max number (255) and restart to 0
		if (packet + 256 - lastPacket[sysId] == 1) {
			// check = true;
		}

		lastPacket[sysId] = packet;
		return true;
	}

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(byte[] bytes, int len) {
		char[] hexChars = new char[len * 2];
		for (int j = 0; j < len; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static String byteToHex(int c) {
		int v = c & 0xFF;
		char[] hexChars = new char[2];
		hexChars[0] = hexArray[v >>> 4];
		hexChars[1] = hexArray[v & 0x0F];
		return new String(hexChars);
	}
	
//	private void printTestBuffer() {
//		String s = "";
//		for(int i=0; i<testbuffer.length;i++)
//			s = s + " "+byteToHex(testbuffer[i]);
//		System.out.println(s);
//	}

	private class RxMsg {
		public byte start;
		public int len;
		public int incompat;
		public int compat;
		public int packet;
		public int sysId;
		public int componentId;
		public int msgId;
		public int crc = MAVLinkCRC.crc_init();;
		public byte[] rawData = new byte[MAVLINK_MAX_PAYLOAD_SIZE + 32];
		public byte[] signature = new byte[MAVLINK_SIGNATURE_BLOCK_LEN];

		public mavlink_framing_t msg_received;
		public int signature_wait = MAVLINK_SIGNATURE_BLOCK_LEN;

		public void clear() {
			start = 0;
			len = 0;
			incompat = 0;
			compat = 0;
			packet = 0;
			sysId = 0;
			componentId = 0;
			msgId = 0;
			signature_wait = MAVLINK_SIGNATURE_BLOCK_LEN;
			msg_received = mavlink_framing_t.MAVLINK_FRAMING_INCOMPLETE;
			crc = MAVLinkCRC.crc_init();
		}

		public String toString() {
			String s=  " MSG " + msgId + " STX=" + start + " LEN=" + len + " PAK=" + packet + " SYS=" + sysId + " COMP="+componentId+" => "
					+ msg_received;
			
			for(int i=0; i<MAVLINK_MAX_PAYLOAD_SIZE;i++)
				s = s + " "+rawData[i];
			
			return s;
		}

	}

}
