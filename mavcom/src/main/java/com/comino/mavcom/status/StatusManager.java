/****************************************************************************
 *
 *   Copyright (c) 2017,2019 Eike Mansfeld ecm@gmx.de. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 ****************************************************************************/

package com.comino.mavcom.status;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.mavlink.messages.MAV_SEVERITY;
import org.mavlink.messages.MSP_AUTOCONTROL_MODE;

import com.comino.mavcom.control.IMAVController;
import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.model.segment.LogMessage;
import com.comino.mavcom.model.segment.Status;
import com.comino.mavcom.model.segment.Vision;
import com.comino.mavcom.status.listener.IMSPStatusChangedListener;
import com.comino.mavutils.workqueue.WorkQueue;

import us.ihmc.log.LogTools;

public class StatusManager implements Runnable {

	private static final long TIMEOUT_IMU = 2000000;
	private static final long TIMEOUT_VISION = 3000000;
	private static final long TIMEOUT_CONNECTED = 3000000;
	private static final long TIMEOUT_GCL_CONNECTED = 5_000_000;
	private static final long TIMEOUT_RC_ATTACHED = 1000000;
	private static final long TIMEOUT_JOY_ATTACHED = 2000000;
	private static final long TIMEOUT_GPOS = 2000000;
	private static final long TIMEOUT_LPOS = 2000000;
	private static final long TIMEOUT_GPS = 2000000;
	private static final long TIMEOUT_SLAM = 5000000;
	private static final long TIMEOUT_GRID = 5000000;
	private static final long TIMEOUT_LIDAR = 2000000;
	private static final long TIMEOUT_FLOW = 2000000;

	public static final byte TYPE_ALL = 0;
	public static final byte TYPE_MSP_STATUS = 1;
	public static final byte TYPE_PX4_NAVSTATE = 2;
	public static final byte TYPE_MSP_SLAMSTATUS = 3;
	public static final byte TYPE_MSP_AUTOPILOT = 4;
	public static final byte TYPE_MSP_SERVICES = 5;
	public static final byte TYPE_ESTIMATOR = 6;
	public static final byte TYPE_BATTERY = 7;

	public static final byte EDGE_BOTH = 0;
	public static final byte EDGE_RISING = 1;
	public static final byte EDGE_FALLING = 2;

	public static final int MASK_ALL = 0xFFFFFFFF;

	public final DataModel      model;
	
	private Status status_current       = null;
	private Status status_old           = null;

	private List<StatusListenerEntry> list = null;
	private volatile ConcurrentLinkedQueue<Action> actions = null;

	private boolean isRunning = false;

	private long t_armed_start = 0;

	private final WorkQueue wq = WorkQueue.getInstance();
	private boolean is_gcl = false;

	public StatusManager(DataModel model, boolean isGCL) {
		this.model = model;
		this.status_current = new Status();
		this.status_old = new Status();
		this.list = new ArrayList<StatusListenerEntry>();
		this.actions = new ConcurrentLinkedQueue<Action>();
		this.is_gcl = isGCL;
	}

	public void start() {
		if (isRunning)
			return;
		this.status_old.status = 0;
		this.status_current.status = 0;
		wq.addCyclicTask("NP", 20, this);
		isRunning = true;
	}

	public void stop() {
		isRunning = false;
	}

	public int getSize() {
		return list.size();
	}

	private void addListener(byte type, int mask, int timeout_ms, int edge, IMSPStatusChangedListener listener) {
		StatusListenerEntry entry = new StatusListenerEntry();
		entry.listener = listener;
		entry.type = type;
		entry.mask = mask;
		entry.timeout_ms = timeout_ms;
		entry.state = edge;
		list.add(entry);
	}

	public void addListener(byte type, int box, int edge, IMSPStatusChangedListener listener) {
		// For NAV State / Estimator state use mask as comparison value
		if (type == TYPE_PX4_NAVSTATE || type == TYPE_ESTIMATOR || type == TYPE_BATTERY)
			addListener(type, box, 0, edge, listener);
		else
			addListener(type, 1 << box, 0, edge, listener);
	}

	public void addListener(byte type, int box, IMSPStatusChangedListener listener) {
		if (type == TYPE_PX4_NAVSTATE || type == TYPE_ESTIMATOR || type == TYPE_BATTERY)
			addListener(type, box, 0, EDGE_BOTH, listener);
		else
			addListener(type, 1 << box, 0, EDGE_BOTH, listener);
	}

	public void addListener(int box, IMSPStatusChangedListener listener) {
		addListener(TYPE_MSP_STATUS, 1 << box, 0, EDGE_BOTH, listener);
	}

	public void addListener(IMSPStatusChangedListener listener) {
		addListener(TYPE_MSP_STATUS, MASK_ALL, 0, EDGE_BOTH, listener);
	}

	public void removeAll() {
		list.clear();
	}

	public void reset() {
		status_old.status &= 0x1;
		status_old.px4_status = 0;
		status_old.autopilot = 0;
		status_old.nav_state = 0;
		status_old.sensors = 0;
	}

	public Status get() {
		return status_current;
	}

	@Override
	public void run() {

		checkTimeouts();

		status_current.set(model.sys);
		
		if(model.sys.isStatus(Status.MSP_ARMED))
			model.sys.t_armed_ms = System.currentTimeMillis() - t_armed_start;
		else {
			model.sys.t_armed_ms = 0;
			t_armed_start = System.currentTimeMillis();
		}

		if (status_old.isEqual(status_current))
			return;

		// if(status_old.nav_state!=status_current.nav_state)
		// System.out.println(status_old.nav_state+" -> "+status_current.nav_state)

		try {
			

			for (StatusListenerEntry entry : list) {

				switch (entry.type) {

				case TYPE_MSP_STATUS:
					switch (entry.state) {
					case EDGE_BOTH:
						if (status_current.isStatusChanged(status_old, entry.mask)) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					case EDGE_RISING:
						if (status_current.isStatusChanged(status_old, entry.mask, true)) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					case EDGE_FALLING:
						if (status_current.isStatusChanged(status_old, entry.mask, false)) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					}
					break;
				case TYPE_PX4_NAVSTATE:

					switch (entry.state) {
					case EDGE_BOTH:
						if ((status_current.nav_state != entry.mask && status_old.nav_state == entry.mask)
								|| (status_current.nav_state == entry.mask && status_old.nav_state != entry.mask)) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					case EDGE_RISING:
						if (status_current.nav_state == entry.mask && status_old.nav_state != entry.mask) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					case EDGE_FALLING:

						if (status_current.nav_state != entry.mask && status_old.nav_state == entry.mask) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					}

					break;

				case TYPE_MSP_SLAMSTATUS:

					// TODO

					break;
				case TYPE_MSP_SERVICES:

					switch (entry.state) {
					case EDGE_BOTH:
						if (status_current.isSensorChanged(status_old, entry.mask)) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					case EDGE_RISING:
						if (status_current.isSensorChanged(status_old, entry.mask, true)) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					case EDGE_FALLING:

						if (status_current.isSensorChanged(status_old, entry.mask, false)) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					}
					break;

				case TYPE_MSP_AUTOPILOT:
					if (status_current.isAutopilotModeChanged(status_old, entry.mask)) {
						actions.add(new Action(entry.listener, status_current));
						entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
					}
					break;

				case TYPE_ESTIMATOR:

					switch (entry.state) {
					case EDGE_BOTH:
						if (((status_current.est_state & entry.mask) == 0 && (status_old.est_state & entry.mask) != 0)
								|| ((status_current.est_state & entry.mask) != 0
								&& (status_old.est_state & entry.mask) == 0)) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					case EDGE_FALLING:
						if ((status_current.est_state & entry.mask) == 0 && (status_old.est_state & entry.mask) != 0) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					case EDGE_RISING:

						if ((status_current.est_state & entry.mask) != 0 && (status_old.est_state & entry.mask) == 0) {
							actions.add(new Action(entry.listener, status_current));
							entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
						}
						break;
					}
					break;

				case TYPE_BATTERY:

					if (status_current.bat_state != status_old.bat_state && status_current.bat_state == entry.mask) {
						actions.add(new Action(entry.listener, status_current));
						entry.last_triggered = DataModel.getSynchronizedPX4Time_us();
					}

					break;
				}
			}
		} catch (Exception e) { }

		run_callbacks();

		status_old.set(status_current);

	}

	private synchronized void run_callbacks() {

		if (!actions.isEmpty()) {
			while (!actions.isEmpty()) {
				wq.addSingleTask("NP", 0, actions.poll());
			}
		}
	}

	private void checkTimeouts() {

		if (checkTimeOut(model.attitude.tms, TIMEOUT_IMU) && model.sys.isSensorAvailable(Status.MSP_IMU_AVAILABILITY)) {
			model.sys.setSensor(Status.MSP_IMU_AVAILABILITY, false);
		}

		if (checkTimeOut(model.state.tms, TIMEOUT_LPOS) && model.sys.isStatus(Status.MSP_LPOS_VALID)) {
			model.sys.setStatus(Status.MSP_LPOS_VALID, false);
		}

		if (checkTimeOut(model.state.gpos_tms, TIMEOUT_GPOS) && model.sys.isStatus(Status.MSP_GPOS_VALID)) {
			model.sys.setStatus(Status.MSP_GPOS_VALID, false);
		}

		if (checkTimeOut(model.distance.tms, TIMEOUT_LIDAR)
				&& model.sys.isSensorAvailable(Status.MSP_LIDAR_AVAILABILITY)) {
			// System.out.println("LIDAR timeout");
			model.sys.setSensor(Status.MSP_LIDAR_AVAILABILITY, false);
		}

		if (checkTimeOut(model.flow.tms, TIMEOUT_FLOW)
				&& model.sys.isSensorAvailable(Status.MSP_PIX4FLOW_AVAILABILITY)) {
			// System.out.println("LIDAR timeout");
			model.sys.setSensor(Status.MSP_PIX4FLOW_AVAILABILITY, false);
		}

		if (checkTimeOut(model.vision.tms, TIMEOUT_VISION)
				&& model.sys.isSensorAvailable(Status.MSP_OPCV_AVAILABILITY)) {
			model.sys.setSensor(Status.MSP_OPCV_AVAILABILITY, false);
			model.vision.setStatus(Vision.AVAILABLE, false);
			model.vision.setStatus(Vision.FIDUCIAL_LOCKED, false);
			model.vision.setStatus(Vision.POS_VALID, false);
			model.vision.setStatus(Vision.PUBLISHED, false);
		}

		if (checkTimeOut(model.gps.tms, TIMEOUT_GPS) && model.sys.isSensorAvailable(Status.MSP_GPS_AVAILABILITY)) {
			model.sys.setSensor(Status.MSP_GPS_AVAILABILITY, false);
			model.gps.clear();
		}

		if (checkTimeOut(model.slam.tms, TIMEOUT_SLAM) && model.sys.isSensorAvailable(Status.MSP_SLAM_AVAILABILITY)) {
			model.sys.setSensor(Status.MSP_SLAM_AVAILABILITY, false);
			model.slam.clear();
		}

		if (checkTimeOut(model.slam.tms, TIMEOUT_GRID) && model.sys.isSensorAvailable(Status.MSP_GRID_AVAILABILITY)) {
			model.sys.setSensor(Status.MSP_GRID_AVAILABILITY, false);
		}

		if (checkTimeOutSystem(model.sys.gcl_tms, TIMEOUT_GCL_CONNECTED)
				&& model.sys.isStatus(Status.MSP_GCL_CONNECTED)) {
			model.sys.setStatus(Status.MSP_GCL_CONNECTED, (false));
//			LogTools.info(
//					("GCL lost at " + (model.sys.gcl_tms - DataModel.getSynchronizedPX4Time_us()) / 1000) + "ms");
	//		System.out.println(model.sys);
		}

		if (checkTimeOutSystem(model.sys.msp_tms, TIMEOUT_GCL_CONNECTED)
				&& model.sys.isSensorAvailable(Status.MSP_MSP_AVAILABILITY)) {
			model.sys.setSensor(Status.MSP_MSP_AVAILABILITY, (false));
			model.sys.setSensor(Status.MSP_ROS_AVAILABILITY, (false));
			model.sys.setStatus(Status.MSP_ACTIVE, false);
		}

		if (!model.sys.isStatus(Status.MSP_SITL)) {
			if (checkTimeOut(model.rc.tms, TIMEOUT_RC_ATTACHED)) {
				model.sys.setStatus(Status.MSP_RC_ATTACHED, false);
				model.rc.rssi = 0;
			}
		}

		if (checkTimeOut(model.sys.tms, TIMEOUT_CONNECTED) && model.sys.isStatus(Status.MSP_CONNECTED)) {
			model.sys.setStatus(Status.MSP_CONNECTED, false);
			model.sys.setStatus(Status.MSP_ACTIVE, false);
			model.sys.clear();
			model.vision.clear();
			// System.out.println("..Connection timeout
			// "+(model.sys.tms+TIMEOUT_CONNECTED)+" vs
			// "+DataModel.getSynchronizedPX4Time_us()+" > "+model.sys.tms);
		//	LogTools.info(("Connection lost: " + (DataModel.getSynchronizedPX4Time_us() - model.sys.tms) / 1000) + "ms");
			//System.out.println(model.sys);
			model.sys.wifi_quality = 0;
			model.sys.tms = DataModel.getSynchronizedPX4Time_us();
		}
	}

	private boolean checkTimeOut(long tms, long timeout) {
		if (tms == 0)
			return false;
		return DataModel.getSynchronizedPX4Time_us() > (tms + timeout);
	}

	private boolean checkTimeOutSystem(long tms, long timeout) {
		if (tms == 0)
			return false;
		return (System.currentTimeMillis() * 1000L) > (tms + timeout);
	}

	private class Action implements Runnable {

		public Status status;
		public IMSPStatusChangedListener listener;

		public Action(IMSPStatusChangedListener listener, Status status) {
			this.listener = listener;
			this.status = status.clone();
		}

		public void run() {
			listener.update(status);
		}

	}

	private class StatusListenerEntry {

		public IMSPStatusChangedListener listener = null;
		public int mask = MASK_ALL;
		public byte type = TYPE_MSP_STATUS;
		public long last_triggered = 0;
		public int timeout_ms = 0;
		public int state = EDGE_BOTH;
	}
}
