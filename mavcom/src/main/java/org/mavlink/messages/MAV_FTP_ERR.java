/**
 * Generated class : MAV_FTP_ERR
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MAV_FTP_ERR
 * MAV FTP error codes (https://mavlink.io/en/services/ftp.html)
 **/
public interface MAV_FTP_ERR {
    /**
     * None: No error
     */
    public final static int MAV_FTP_ERR_NONE = 0;
    /**
     * Fail: Unknown failure
     */
    public final static int MAV_FTP_ERR_FAIL = 1;
    /**
     * FailErrno: Command failed, Err number sent back in PayloadHeader.data[1].
		This is a file-system error number understood by the server operating system.
     */
    public final static int MAV_FTP_ERR_FAILERRNO = 2;
    /**
     * InvalidDataSize: Payload size is invalid
     */
    public final static int MAV_FTP_ERR_INVALIDDATASIZE = 3;
    /**
     * InvalidSession: Session is not currently open
     */
    public final static int MAV_FTP_ERR_INVALIDSESSION = 4;
    /**
     * NoSessionsAvailable: All available sessions are already in use
     */
    public final static int MAV_FTP_ERR_NOSESSIONSAVAILABLE = 5;
    /**
     * EOF: Offset past end of file for ListDirectory and ReadFile commands
     */
    public final static int MAV_FTP_ERR_EOF = 6;
    /**
     * UnknownCommand: Unknown command / opcode
     */
    public final static int MAV_FTP_ERR_UNKNOWNCOMMAND = 7;
    /**
     * FileExists: File/directory already exists
     */
    public final static int MAV_FTP_ERR_FILEEXISTS = 8;
    /**
     * FileProtected: File/directory is write protected
     */
    public final static int MAV_FTP_ERR_FILEPROTECTED = 9;
    /**
     * FileNotFound: File/directory not found
     */
    public final static int MAV_FTP_ERR_FILENOTFOUND = 10;
}
