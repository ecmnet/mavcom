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
import com.comino.mavcom.struct.Attitude3D_F64;

import georegression.struct.point.Vector3D_F64;
import georegression.struct.point.Vector4D_F64;

public class Vision extends Segment {

	private static final long serialVersionUID = 270248566309263309L;

	public static final int ENABLED             = 0;
	public static final int AVAILABLE           = 1;
	public static final int RESETTING           = 2;
	public static final int POS_VALID           = 3;
	public static final int PUBLISHED           = 4;
	public static final int FIDUCIAL_LOCKED     = 5;
	
	public static final int FIDUCIAL_ACTIVE     = 15;
	
	private static final String[] MSP_VISION_TEXTS  = { "Enabled","Available", "Resetting", "Pos. valid", "Published", "Fiducial locked" };


	// Vision position
	public float x = Float.NaN;
	public float y = Float.NaN;
	public float z = Float.NaN;

	// Ground truth
	public float gx = Float.NaN;
	public float gy = Float.NaN;
	public float gz = Float.NaN;

	// Fiducial offset
	public float px = Float.NaN;
	public float py = Float.NaN;
	public float pz = Float.NaN;
	public float pw = Float.NaN;

	// Covariance
	public float cov_px = Float.NaN;
	public float cov_py = Float.NaN;
	public float cov_pz = Float.NaN;

	// Speed
	public float vx = Float.NaN;
	public float vy = Float.NaN;
	public float vz = Float.NaN;

	// Covariance speed
	public float cov_vx = Float.NaN;
	public float cov_vy = Float.NaN;
	public float cov_vz = Float.NaN;

	public float h    = Float.NaN;
	public float p    = Float.NaN;
	public float r    = Float.NaN;

	public int   flags = 0;
	public float fps   = Float.NaN;
	public float qual  = Float.NaN;

	public int   errors = 0;


	public void set(Vision a) {
		x  = a.x;
		y  = a.y;
		z  = a.z;

		gx  = a.gx;
		gy  = a.gy;
		gz  = a.gz;

		px  = a.px;
		py  = a.py;
		pz  = a.pz;
		pw  = a.pw;

		cov_px  = a.cov_px;
		cov_py  = a.cov_py;
		cov_pz  = a.cov_pz;

		vx = a.vx;
		vy = a.vy;
		vz = a.vz;

		cov_vx  = a.cov_vx;
		cov_vy  = a.cov_vy;
		cov_vz  = a.cov_vz;

		errors = a.errors;

		h = a.h;
		p = a.p;
		r = a.r;
		qual = a.qual;

		flags = a.flags;
		fps   = a.fps;
	}

	public Vision clone() {
		Vision a = new Vision();
        a.set(this);
		return a;
	}


	public void clear() {
		x  = Float.NaN;
		y  = Float.NaN;
		z  = Float.NaN;

		gx  = Float.NaN;
		gy  = Float.NaN;
		gz  = Float.NaN;
		
		px  = Float.NaN;
		py  = Float.NaN;
		pz  = Float.NaN;
		pw  = Float.NaN;
		
		cov_px  = Float.NaN;
		cov_py  = Float.NaN;
		cov_pz  = Float.NaN;

		vx = Float.NaN;
		vy = Float.NaN;
		vz = Float.NaN;

		cov_vx  = Float.NaN;
		cov_vy  = Float.NaN;
		cov_vz  = Float.NaN;

		h = Float.NaN;
		p = Float.NaN;
		r = Float.NaN;

		qual=Float.NaN;

		flags = 0;
		fps = Float.NaN;
	}

	public void  setStatus(int box, boolean val) {
		if(val)
			flags = (int) (flags | (1<<box));
		else
			flags = (int) (flags & ~(1<<box));
	}

	public boolean isStatus(int ...box) {
		for(int b : box)
		  if((flags & (1<<b))==0)
            return false;
		return true;
	}

	public void setPosition(Vector3D_F64 v) {
		this.x = (float)v.x;
		this.y = (float)v.y;
		this.z = (float)v.z;
	}

	public void setSpeed(Vector3D_F64 v) {
		this.vx = (float)v.x;
		this.vy = (float)v.y;
		this.vz = (float)v.z;
	}

	public void setPrecisionOffset(Vector3D_F64 v) {
		this.px = (float)v.x;
		this.py = (float)v.y;
		this.pz = (float)v.z;
	}
	
	public void setPrecisionOffset(Vector4D_F64 v) {
		this.px = (float)v.x;
		this.py = (float)v.y;
		this.pz = (float)v.z;
		this.pw = (float)v.w;
	}

	public void setGroundTruth(Vector3D_F64 v) {
		this.gx = (float)v.x;
		this.gy = (float)v.y;
		this.gz = (float)v.z;
	}


	public void setAttitude(Attitude3D_F64 a) {
		this.r = (float)a.getRoll();
		this.p = (float)a.getPitch();
		this.h = (float)a.getYaw();
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("!---------------------------------------------------!\n");
		b.append("Vision flags:\n");
		for(int i=0;i<MSP_VISION_TEXTS.length;i++) {
		   b.append((flags >> i) & 0x1).append(" = ").append(MSP_VISION_TEXTS[i]).append("\n"); 
		}
		b.append("!---------------------------------------------------!\n");
		return b.toString();
	}



}
