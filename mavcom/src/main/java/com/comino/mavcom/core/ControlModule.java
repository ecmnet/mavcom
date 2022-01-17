package com.comino.mavcom.core;

import com.comino.mavcom.control.IMAVMSPController;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.LogMessage;
import com.comino.mavcom.status.StatusManager;

public class ControlModule {

	protected final DataModel model;
	protected final StatusManager statusManager;
	protected final IMAVMSPController control;

	public ControlModule(IMAVMSPController control) {
		this.control = control;
		this.model = control.getCurrentModel();
		this.statusManager = control.getStatusManager();
	}

	protected void writeLogMessage(LogMessage m) {
		control.writeLogMessage(m);
	}

}
