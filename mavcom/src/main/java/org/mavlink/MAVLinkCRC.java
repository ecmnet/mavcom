package org.mavlink;

public class MAVLinkCRC {
	
	public final static int X25_INIT_CRC = 0xffff;
	
	public static int crc_init() {
		return IMAVLinkMessage.X25_INIT_CRC;
	}

    /**
     * Accumulate the X.25 CRC by adding one char at a time. The checksum function adds the hash of one char at a time
     * to the 16 bit checksum
     *
     * @param data new char to hash
     * @param crc  the already accumulated checksum
     * @return the new accumulated checksum
     */
    public static int crc_accumulate(byte data, int crc) {
        int tmp = (data ^ crc) & 0xff;
        tmp ^= (tmp << 4) & 0xff;
        return ((crc >> 8) ^ (tmp << 8) ^ (tmp << 3) ^ (tmp >> 4)) & 0xffff;
    }

    public static int crc_calculate(byte[] data) {
        int crc = X25_INIT_CRC;
        for (byte b : data) {
            crc = crc_accumulate(b, crc);
        }
        return crc;
    }
    
    public static int crc_calculate_encode(byte[] buffer, int length) {
    	int crc = X25_INIT_CRC;
		for (int i = 1; i < buffer.length - 2; i++) {
			crc = crc_accumulate(buffer[i], crc);
		}
		return crc;
	}
    
    public static int crc_calculate_decode(byte[] buffer, int dataLength) {
		int crc = X25_INIT_CRC;
		for (int i = 1; i <= dataLength + IMAVLinkMessage.CRC_LEN; i++) {
			crc = crc_accumulate(buffer[i], crc);
		}
		return crc;
	}
    
    public static byte[] stringToByte(String data) {
		byte[] buffer = new byte[data.length()];
		for (int i = 0; i < data.length(); i++) {
			buffer[i] = (byte) data.charAt(i);
		}
		return buffer;
	}

}
