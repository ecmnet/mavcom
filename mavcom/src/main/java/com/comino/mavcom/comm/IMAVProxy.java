package com.comino.mavcom.comm;

import org.mavlink.messages.MAVLinkMessage;

import com.comino.mavcom.mavlink.IMAVLinkListener;

public interface IMAVProxy {

	boolean open();

	boolean isConnected();

	boolean isProxyEnabled();

	void enableProxy(boolean enable);

	void close();

	void registerListener(Class<?> clazz, IMAVLinkListener listener);

	void unregisterListener(Class<?> clazz);

	int getBadCRC();

	void write(MAVLinkMessage msg);

	long getTransferRate();

	void write(byte[] buffer, int length);

	void shutdown();

	void broadcast();

}