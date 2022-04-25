/**
 * Generated class : MAV_FTP_OPCODE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_FTP_OPCODE
 * MAV FTP opcodes: https://mavlink.io/en/services/ftp.html
 **/
public interface MAV_FTP_OPCODE {
    /**
     * None. Ignored, always ACKed
     */
    public final static int MAV_FTP_OPCODE_NONE = 0;
    /**
     * TerminateSession: Terminates open Read session
     */
    public final static int MAV_FTP_OPCODE_TERMINATESESSION = 1;
    /**
     * ResetSessions: Terminates all open read sessions
     */
    public final static int MAV_FTP_OPCODE_RESETSESSION = 2;
    /**
     * ListDirectory. List files and directories in path from offset
     */
    public final static int MAV_FTP_OPCODE_LISTDIRECTORY = 3;
    /**
     * OpenFileRO: Opens file at path for reading, returns session
     */
    public final static int MAV_FTP_OPCODE_OPENFILERO = 4;
    /**
     * ReadFile: Reads size bytes from offset in session
     */
    public final static int MAV_FTP_OPCODE_READFILE = 5;
    /**
     * CreateFile: Creates file at path for writing, returns session
     */
    public final static int MAV_FTP_OPCODE_CREATEFILE = 6;
    /**
     * WriteFile: Writes size bytes to offset in session
     */
    public final static int MAV_FTP_OPCODE_WRITEFILE = 7;
    /**
     * RemoveFile: Remove file at path
     */
    public final static int MAV_FTP_OPCODE_REMOVEFILE = 8;
    /**
     * CreateDirectory: Creates directory at path
     */
    public final static int MAV_FTP_OPCODE_CREATEDIRECTORY = 9;
    /**
     * RemoveDirectory: Removes directory at path. The directory must be empty.
     */
    public final static int MAV_FTP_OPCODE_REMOVEDIRECTORY = 10;
    /**
     * OpenFileWO: Opens file at path for writing, returns session
     */
    public final static int MAV_FTP_OPCODE_OPENFILEWO = 11;
    /**
     * TruncateFile: Truncate file at path to offset length
     */
    public final static int MAV_FTP_OPCODE_TRUNCATEFILE = 12;
    /**
     * Rename: Rename path1 to path2
     */
    public final static int MAV_FTP_OPCODE_RENAME = 13;
    /**
     * CalcFileCRC32: Calculate CRC32 for file at path
     */
    public final static int MAV_FTP_OPCODE_CALCFILECRC = 14;
    /**
     * BurstReadFile: Burst download session file
     */
    public final static int MAV_FTP_OPCODE_BURSTREADFILE = 15;
    /**
     * ACK: ACK response
     */
    public final static int MAV_FTP_OPCODE_ACK = 128;
    /**
     * NAK: NAK response
     */
    public final static int MAV_FTP_OPCODE_NAK = 129;
}
