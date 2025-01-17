/****************************************************************************
 *
 *   Copyright (c) 2017 Eike Mansfeld ecm@gmx.de. All rights reserved.
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

package com.comino.mavcom.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.Instant;

import com.comino.mavcom.model.buffers.BodyToNedBuffer;
import com.comino.mavcom.model.segment.Attitude;
import com.comino.mavcom.model.segment.Battery;
import com.comino.mavcom.model.segment.Debug;
import com.comino.mavcom.model.segment.Distance;
import com.comino.mavcom.model.segment.Esc;
import com.comino.mavcom.model.segment.EstStatus;
import com.comino.mavcom.model.segment.Flow;
import com.comino.mavcom.model.segment.GPS;
import com.comino.mavcom.model.segment.Grid;
import com.comino.mavcom.model.segment.Hud;
import com.comino.mavcom.model.segment.Imu;
import com.comino.mavcom.model.segment.LogMessage;
import com.comino.mavcom.model.segment.Obstacle;
import com.comino.mavcom.model.segment.Rc;
import com.comino.mavcom.model.segment.Servo;
import com.comino.mavcom.model.segment.Slam;
import com.comino.mavcom.model.segment.State;
import com.comino.mavcom.model.segment.Status;
import com.comino.mavcom.model.segment.Telemetry;
import com.comino.mavcom.model.segment.Trajectory;
import com.comino.mavcom.model.segment.Vibration;
import com.comino.mavcom.model.segment.Vision;
import com.comino.mavcom.model.segment.Waypoints;
import com.comino.mavcom.model.segment.generic.Segment;

import georegression.struct.se.Se3_F64;

// Consolidated device data model after IMU

public class DataModel extends Segment implements Serializable {
	
	private static final int NED_BUFFER_SIZE = 100;

	/**
	 *
	 */
	private static final long serialVersionUID = 3439530621929819600L;

	public static long t_offset_us = 0;
	public static long tms_boot = getSynchronizedPX4Time_us();
	
	public final BodyToNedBuffer<Se3_F64> toNEDBuffer;

	public Attitude attitude = null;
	public Battery battery = null;
	public Hud hud = null;
	public Imu imu = null;
	public State state = null;
	public State target_state = null;
	public State home_state = null;
	public Telemetry telemetry = null;
	public GPS gps = null;
	public GPS base = null;
	public Flow flow = null;
	public Distance distance = null;
	public Status sys = null;
	public Servo servo = null;
	public Rc rc = null;
	public Vibration vibration = null;
	public Debug debug = null;
	public LogMessage msg = null;
	public Vision vision = null;
	public EstStatus est = null;
	public Grid grid = null;
	public Slam slam = null;
	public Waypoints way = null;
	public Esc esc = null;
	public Trajectory traj = null;
	public Obstacle  obs = null;

	public DataModel() {
		this.toNEDBuffer = new BodyToNedBuffer<Se3_F64>(this,NED_BUFFER_SIZE);
		this.attitude = new Attitude();
		this.battery = new Battery();
		this.hud = new Hud();
		this.imu = new Imu();
		this.state = new State();
		this.target_state = new State();
		this.home_state = new State();
		this.telemetry = new Telemetry();
		this.gps = new GPS();
		this.base = new GPS();
		this.flow = new Flow();
		this.distance = new Distance();
		this.sys = new Status();
		this.servo = new Servo();
		this.rc = new Rc();
		this.vibration = new Vibration();
		this.debug = new Debug();
		this.msg = new LogMessage();
		this.vision = new Vision();
		this.est = new EstStatus();
		this.slam = new Slam();
		this.grid = new Grid();
		this.way = new Waypoints();
		way.clear();
		this.esc = new Esc();
		this.traj = new Trajectory();
		this.obs  = new Obstacle();
	}

	public DataModel(DataModel m) {
		this.toNEDBuffer = m.toNEDBuffer;
		this.copy(m);
	}

	public void copy(DataModel m) {
		this.attitude = m.attitude.clone();
		this.battery = m.battery.clone();
		this.hud = m.hud.clone();
		this.imu = m.imu.clone();
		this.state = m.state.clone();
		this.target_state = m.target_state.clone();
		this.home_state = m.home_state.clone();
		this.telemetry = m.telemetry.clone();
		this.gps = m.gps.clone();
		this.base = m.base.clone();
		this.flow = m.flow.clone();
		this.sys = m.sys.clone();
		this.servo = m.servo.clone();
		this.rc = m.rc.clone();
		this.vibration = m.vibration.clone();
		this.debug = m.debug.clone();
		this.tms = m.tms;
		this.msg = m.msg.clone();
		this.vision = m.vision.clone();
		this.est = m.est.clone();
		this.slam = m.slam.clone();
		this.grid = m.grid.clone();
		this.way = m.way.clone();
		this.esc = m.esc.clone();
		this.distance = m.distance.clone();
		this.traj = m.traj.clone();
		this.obs  = m.obs.clone();
	}

	public void set(DataModel m) {
		this.attitude.set(m.attitude);
		this.battery.set(m.battery);
		this.hud.set(m.hud);
		this.imu.set(m.imu);
		this.state.set(m.state);
		this.target_state.set(m.target_state);
		this.home_state.set(m.home_state);
		this.telemetry.set(m.telemetry);
		this.gps.set(m.gps);
		this.base.set(m.base);
		this.flow.set(flow);
		this.sys.set(m.sys);
		this.servo.set(m.servo);
		this.rc.set(m.rc);
		this.vibration.set(m.vibration);
		this.debug.set(m.debug);
		this.tms = m.tms;
		this.msg.set(m.msg);
		this.vision.set(m.vision);
		this.est.set(m.est);
		this.slam.set(m.slam);
		this.grid.set(m.grid);
		this.way.set(m.way);
		this.esc.set(m.esc);
		this.distance.set(m.distance);
		this.traj.set(m.traj);
		this.obs.set(m.obs);;
	}

	public DataModel clone() {
		return new DataModel(this);
	}
	
	public BodyToNedBuffer<Se3_F64> getBodyToNedBuffer() {
		return toNEDBuffer;
	}

	public void clear() {
		reset();
		this.sys.clear();
		this.est.clear();
		this.battery.clear();
		this.slam.clear();
		this.vision.clear();
		this.tms = 0;

	}

	public void reset() {
		this.attitude.clear();
		this.hud.clear();
		this.imu.clear();
		this.state.clear();
		this.target_state.clear();
		this.home_state.clear();
		this.telemetry.clear();
		this.gps.clear();
		this.base.clear();
		this.servo.clear();
		this.rc.clear();
		this.vibration.clear();
		this.debug.clear();
		this.msg.clear();
		this.vision.clear();
		this.slam.clear();
		this.way.clear();
		this.esc.clear();
		this.distance.clear();
		this.traj.clear();
		this.obs.clear();
	}

	public float getValue(String classkey) {
		try {
			String[] key = classkey.split("\\.");
			Field mclass_field = this.getClass().getField(key[0]);
			Object mclass = mclass_field.get(this);
			Field mfield_field = mclass.getClass().getField(key[1]);
			return new Double(mfield_field.getDouble(mclass)).floatValue();
		} catch (Exception e) {
			return Float.NaN;
		}
	}

	public static long getSynchronizedPX4Time_us() {
		Instant ins = Instant.now();
//		long now_ns = ins.getEpochSecond() * 1000000000L + ins.getNano();
//		return (now_ns - t_offset_ns) / 1000L;
		
		long now = ins.getEpochSecond() * 1000000L;
		now = now + (ins.getNano() / 1000L - t_offset_us);
		return now;
		
	}

	public static long getSynchronizedPX4Time_us(long tms_ms) {
		return tms_ms * 1000L - t_offset_us;
	}

	
	public static long getBootTime() {
		return tms_boot;
	}
	
	public static long getUnixTime_us() {
		Instant ins = Instant.now();
		long now_ns = ins.getEpochSecond() * 1000000000L + ins.getNano();
		return now_ns / 1000L;
	}

	public static void main(String[] args) {
		DataModel m = new DataModel();
		m.battery.a0 = 5;
		try {
			System.out.println(m.getValue("battery.a0"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
