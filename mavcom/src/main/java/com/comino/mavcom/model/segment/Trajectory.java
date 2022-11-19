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

public class Trajectory extends Segment {

	private static final long serialVersionUID = 3575306268521569009L;

	// Polynomial Trajectory

	public float ls = 0; // length in secs
	public float fs = 0; // already done in secs

	public float ax = 0; // alpha
	public float ay = 0;
	public float az = 0;

	public float bx = 0; // beta
	public float by = 0;
	public float bz = 0;

	public float gx = 0; // gamma
	public float gy = 0;
	public float gz = 0;

	public float sx = 0; // gamma
	public float sy = 0;
	public float sz = 0;

	public float svx = 0; // gamma
	public float svy = 0;
	public float svz = 0;
	
	public float sax = 0; // gamma
	public float say = 0;
	public float saz = 0;

	public Trajectory clone() {
		Trajectory t = new Trajectory();
		t.set(this);
		return t;
	}

	public void set(Trajectory t) {

		ls = t.ls;

		ax = t.ax;
		ay = t.ay;
		az = t.az;

		bx = t.bx;
		by = t.by;
		bz = t.bz;

		gx = t.gx;
		gy = t.gy;
		gz = t.gz;

		sx = t.sx;
		sy = t.sy;
		sz = t.sz;

		svx = t.svx;
		svy = t.svy;
		svz = t.svz;
		
		sax = t.sax;
		say = t.say;
		saz = t.saz;

	}

	public void clear() {
		ls = -1f;
		fs = -1f;
		ax = 0;
		ay = 0;
		az = 0;
		sx = 0;
		svx = 0;
		bx = 0;
		by = 0;
		bz = 0;
		sy = 0;
		svy = 0;
		gx = 0;
		gy = 0;
		gz = 0;
		sz = 0;
		svz = 0;
		sax = 0;
		say = 0;
		saz = 0;
	  
	}

}
