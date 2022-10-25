package com.comino.mavcom.messaging.msgs;

public class msp_msg_base {
	
	private static final int TIMEOUT = 1000;
	
	public long tms = 0;
	
	public boolean isTimeout() {
		return (System.currentTimeMillis() - tms) > TIMEOUT;
	}

}
