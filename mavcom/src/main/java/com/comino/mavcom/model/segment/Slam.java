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

package com.comino.mavcom.model.segment;

import com.comino.mavcom.model.segment.generic.Segment;

public class Slam extends Segment {

	private static final long serialVersionUID = -353494527253663585L;
	
	public static final short OFFBOARD_FLAG_NONE     = 0;
	public static final short OFFBOARD_FLAG_HOLD     = 1;
	public static final short OFFBOARD_FLAG_SPEEDUP  = 2;
	public static final short OFFBOARD_FLAG_SLOWDOWN = 3;
	public static final short OFFBOARD_FLAG_MOVE     = 4;
	public static final short OFFBOARD_FLAG_ADJUST   = 5;
	public static final short OFFBOARD_FLAG_TURN     = 6;
	public static final short OFFBOARD_FLAG_SPEED    = 7;
	
	public static final short OFFBOARD_FLAG_LAND     = 9;
	public static final short OFFBOARD_FLAG_TAKEOFF  = 10;
	

	public float    px;		// planned path x
	public float    py;		// planned path y
	public float    pz;		// planned path Z
	public float    pd;		// planned direction XY
	public float    pp;		// planned direction YZ
	public float    pv;		// planned speed
	public float    dw;     // distance to next waypoint
	public float    dm;     // minimal distance to obstacle
	public float    di;     // distance to target
	public float    ox;		// nearest obstacle x
	public float    oy;		// nearest obstacle y
	public float    oz;		// nearest obstacle z
	public short    flags;  // Offboard Flags
	public float    fps;    // Rate in Hz.
	public float  quality;	// SLAM quality
	public int    wpcount;

	public Slam() {
		clear();
	}

	public void set(Slam a) {
		pv = a.pv;
		pd = a.pd;
		pp = a.pp;
		px = a.px;
		py = a.py;
		pz = a.pz;
		di = a.di;
		dw = a.dw;
		dm = a.dm;
		ox = a.ox;
		oy = a.oy;
		oz = a.oz;
		fps = a.fps;
		flags = a.flags;
		quality = a.quality;
		wpcount = a.wpcount;
	}

	public Slam clone() {
		Slam at = new Slam();
		at.set(this);
		return at;
	}

	public void clear() {
		pv = 0;
		pd = 0;
		pp = Float.NaN;
		px = Float.NaN;
		py = Float.NaN;
		pz = Float.NaN;
		di = Float.NaN;
		dw = Float.NaN;
		dm = Float.NaN;
		ox = Float.NaN;
		oy = Float.NaN;
		oz = Float.NaN;
		fps = 0;
		flags = 0;
		quality = 0;
		wpcount = 0;
	}

	public void set(float pd, float pp, float pv, float di) {
		this.di = di;
		this.pd = pd;
		this.pp = pp;
		this.pv = pv;
	}

}
