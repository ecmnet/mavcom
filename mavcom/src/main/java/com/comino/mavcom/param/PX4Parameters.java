package com.comino.mavcom.param;

import java.util.HashMap;
import java.util.Map;

import org.mavlink.messages.MAV_SEVERITY;
import org.mavlink.messages.lquac.msg_param_request_list;
import org.mavlink.messages.lquac.msg_param_set;
import org.mavlink.messages.lquac.msg_param_value;

import com.comino.mavcom.control.IMAVController;
import com.comino.mavcom.log.MSPLogger;
import com.comino.mavcom.mavlink.IMAVLinkListener;
import com.comino.mavcom.model.segment.Status;

public class PX4Parameters implements IMAVLinkListener {

	private static PX4Parameters parameters = null;

	protected ParameterFactMetaData metadata = null;
	protected Map<String,ParameterAttributes> parameterList = null;

	protected boolean       isLoaded = false;
	protected IMAVController control = null;

	public static PX4Parameters getInstance(IMAVController control) {
		if(parameters == null)
			parameters = new PX4Parameters(control);
		return parameters;
	}

	public static PX4Parameters getInstance() {
		assert(parameters!=null);
		return parameters;
	}

	public PX4Parameters(IMAVController control) {

		this.control = control;
		this.control.addMAVLinkListener(this);
		this.metadata = new ParameterFactMetaData("PX4ParameterFactMetaData.xml");
		this.parameterList = new HashMap<String,ParameterAttributes>();

	}

	@Override
	public void received(Object _msg) {

		if(!(_msg instanceof msg_param_value) )
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


		if(msg.param_index >= msg.param_count-1 && !isLoaded) {
			isLoaded = true;
			control.getCurrentModel().sys.setStatus(Status.MSP_PARAMS_LOADED, true);
			System.out.println("PX4 Parameters loaded succesfully");
		}
	}

	public void requestRefresh() {
		isLoaded = false;
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

	public void sendParameter(String name, float val) {

		ParameterAttributes att = parameterList.get(name.toUpperCase());

		if(att==null) {
			MSPLogger.getInstance().writeLocalMsg("Setting Parameter "+name+" failed", MAV_SEVERITY.MAV_SEVERITY_DEBUG);
			return;
		}

		System.out.println("Parameter "+name+" set to "+val);

		att.value = val;

		final msg_param_set msg = new msg_param_set(255,1);
		msg.target_component = 1;
		msg.target_system = 1;
		msg.param_type = att.vtype;
		msg.setParam_id(att.name);
		msg.param_value = ParamUtils.valToParam(att.vtype, val);
		control.sendMAVLinkMessage(msg);
	}

}
