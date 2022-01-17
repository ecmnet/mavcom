package com.comino.mavcom.mavlink.plugins;

import com.comino.mavcom.flow.MessageBus;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.model.DataModel;

public abstract class MAVLinkPluginBase implements IMAVLinkListener {

	private Class<?> clazz = null;
	protected DataModel model = null;
	protected MessageBus bus = MessageBus.getInstance();

	public MAVLinkPluginBase(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void setDataModel(DataModel model) {
		this.model = model;
	}

	public Class<?> getMessageClass() {
		return clazz;
	}

}
