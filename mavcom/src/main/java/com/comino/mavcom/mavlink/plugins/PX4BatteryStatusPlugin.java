package com.comino.mavcom.mavlink.plugins;

import org.mavlink.messages.lquac.msg_battery_status;

public class PX4BatteryStatusPlugin extends MAVLinkPluginBase {

	public PX4BatteryStatusPlugin() {
		super(msg_battery_status.class);
	}

	@Override
	public void received(Object o) {
		msg_battery_status bat = (msg_battery_status) o;

		if(bat.voltages[0]<65535) {

			if (bat.current_consumed > 0)
				model.battery.a0 = bat.current_consumed;

			if(bat.battery_remaining > 0) {
				model.battery.p = (short) bat.battery_remaining;
			}

			if(bat.current_battery > 10)
				model.battery.c0 = bat.current_battery / 100f;
			else
				model.battery.c0 = Float.NaN;

			model.battery.b0 = 0;
			for(int i=0;i<bat.voltages.length;i++) {
				if(bat.voltages[i] < 65535)
					model.battery.b0 += bat.voltages[i];
			}
			model.sys.bat_state = (int)bat.fault_bitmask;
			model.battery.b0 = model.battery.b0 / 1000f;
			model.battery.tms = System.currentTimeMillis() * 1000;
		}


	}
}
