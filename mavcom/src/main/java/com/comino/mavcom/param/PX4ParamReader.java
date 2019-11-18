package com.comino.mavcom.param;

import java.util.HashMap;
import java.util.Map;

import org.mavlink.messages.lquac.msg_param_request_list;
import org.mavlink.messages.lquac.msg_param_value;

import com.comino.mavcom.control.IMAVMSPController;
import com.comino.mavcom.mavlink.IMAVLinkListener;

public class PX4ParamReader implements IMAVLinkListener {

	protected ParameterFactMetaData metadata = null;
	protected Map<String,ParameterAttributes> parameterList = null;

	protected boolean       isLoaded = false;
	protected IMAVMSPController control = null;

	public PX4ParamReader(IMAVMSPController control) {

		this.control = control;
		this.control.addMAVLinkListener(this);
		this.metadata = new ParameterFactMetaData("PX4ParameterFactMetaData.xml");
		this.parameterList = new HashMap<String,ParameterAttributes>();

	}

	@Override
	public void received(Object _msg) {

		if(!(_msg instanceof msg_param_value))
			return;

		msg_param_value msg = (msg_param_value)_msg;

		if(msg.param_id[0]=='_')
			return;

		ParameterAttributes attributes = metadata.getMetaData(msg.getParam_id());
		if(attributes == null)
			attributes = new ParameterAttributes(msg.getParam_id(),"Default Group");
		attributes.value = ParamUtils.paramToVal(msg.param_type, msg.param_value);
		attributes.vtype = msg.param_type;

		parameterList.put(attributes.name,attributes);


		if(msg.param_index >= msg.param_count-1) {
			isLoaded = true;
			System.out.println("Parameters loaded succesfully...");
		}
	}

	public void requestRefresh() {
		System.out.println("Reading PX4 parameters from device: ");
		parameterList.clear();
		msg_param_request_list msg = new msg_param_request_list(1,1);
		msg.target_component = 1;
		msg.target_system = 1;
		control.sendMAVLinkMessage(msg);
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public ParameterFactMetaData getMetaData() {
		return this.metadata;
	}

	public ParameterAttributes getParam(String paramName) {
		if(isLoaded)
		  return this.parameterList.get(paramName);
		return null;
	}

}
