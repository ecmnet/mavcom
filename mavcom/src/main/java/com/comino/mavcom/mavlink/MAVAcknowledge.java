package com.comino.mavcom.mavlink;

import org.mavlink.messages.lquac.msg_command_long;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.control.IMAVCmdAcknowledge;

public class MAVAcknowledge {

	final public IMAVCmdAcknowledge callback;
	final public IMAVComm link;
	final public msg_command_long msg;

	private int retries = 0;

	public MAVAcknowledge(IMAVCmdAcknowledge callback, msg_command_long msg, IMAVComm link, int retries) {
		super();
		this.callback = callback;
		this.msg = msg;
		this.retries = retries;
		this.link = link;
	}

	public int getAndDecreaseRetries() {
		return retries--;
	}

}
