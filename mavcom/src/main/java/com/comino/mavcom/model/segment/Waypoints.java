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

public class Waypoints extends Segment {

	private static final long serialVersionUID = 9133173059474851575L;

	public float[] posx = new float[5];
	public float[] posy = new float[5];
	public float[] posz = new float[5];

	public float[] velx = new float[5];
	public float[] vely = new float[5];
	public float[] velz = new float[5];

	public float[] accx = new float[5];
	public float[] accy = new float[5];
	public float[] accz = new float[5];

	public float[] pyaw = new float[5];
	public float[] vyaw = new float[5];

	public short[]  cmd  = new short[5];

	public short valid_points = 0;


	public Waypoints clone() {
		Waypoints t = new Waypoints();
		t.set(this);
		return t;
	}

	public void set(Waypoints t) {
		for(int i=0;i<5;i++) {
			
			posx[i] = t.posx[i];
			posy[i] = t.posy[i];
			posz[i] = t.posz[i];
			
			velx[i] = t.velx[i];
			vely[i] = t.vely[i];
			velz[i] = t.velz[i];
			
			accx[i] = t.accx[i];
			accy[i] = t.accy[i];
			accz[i] = t.accz[i];
			
			pyaw[i] = t.pyaw[i];
			vyaw[i] = t.vyaw[i];
			
			cmd[i] = Short.MAX_VALUE;
			
			valid_points = 0;

		}

	}

	public void clear() {	
		for(int i=0;i<5;i++) {
			
			posx[i] = Float.NaN;
			posy[i] = Float.NaN;
			posz[i] = Float.NaN;
			
			velx[i] = Float.NaN;
			vely[i] = Float.NaN;
			velz[i] = Float.NaN;
			
			accx[i] = Float.NaN;
			accy[i] = Float.NaN;
			accz[i] = Float.NaN;
			
			pyaw[i] = Float.NaN;
			vyaw[i] = Float.NaN;
			
			cmd[i] = Short.MAX_VALUE;
			
			valid_points = 0;
		}

	}

}
