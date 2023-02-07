package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_file_transfer_protocol;


public class PX4FileTransferProtocolPlugin extends MAVLinkPluginBase {

	public PX4FileTransferProtocolPlugin() {
		super(msg_file_transfer_protocol.class);
	}

	@Override
	public void received(Object o) {

		msg_file_transfer_protocol p = (msg_file_transfer_protocol)o;
		

	}
}
