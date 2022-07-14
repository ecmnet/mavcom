/****************************************************************************
 *
 *   Copyright (c) 2021 Eike Mansfeld ecm@gmx.de. All rights reserved.
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

// ok
import com.comino.mavcom.model.segment.generic.Segment;

public class GPS extends Segment {

	private static final long serialVersionUID = 3343922430238721067L;

	public static final int GPS_SAT_FIX = 0;
	public static final int GPS_SAT_VALID = 1;
	public static final int GPS_SAT_RTK = 2;
	public static final int GPS_SAT_RTKFIX = 3;

	public int flags = 0;

	public int numsat = 0;
	public double latitude = Double.NaN;
	public double longitude = Double.NaN;
	public float heading = 0;
	public float altitude = 0;
	public float eph = Float.NaN;
	public float epv = Float.NaN;
	public float hdop = Float.NaN;
	public float speed = Float.NaN;
	public byte fixtype = (byte) 0;
	public float lx = Float.NaN;
	public float ly = Float.NaN;

	public GPS() {

	}

	public GPS(float _lat, float _lon, float _heading) {
		latitude = _lat;
		longitude = _lon;
		heading =  _heading;
		setFlag(GPS_SAT_VALID, true);
		numsat = 99;
	}

	public void set(int _fix, int _numsat, float _lat, float _lon, int _altitude, float _heading, float _eph,
			float _speed) {

		setFlag(GPS_SAT_FIX, _fix > 0);
		setFlag(GPS_SAT_RTK, _fix > 3);
		setFlag(GPS_SAT_RTKFIX, _fix > 4);
		setFlag(GPS_SAT_VALID, numsat > 6);

		fixtype = (byte) _fix;

		numsat = (byte) _numsat;
		latitude = _lat;
		longitude = _lon;
		heading =  _heading;
		altitude = (short) _altitude;
		eph = _eph;
		speed = _speed;

	}

	public void set(GPS gps) {
		flags = gps.flags;
		numsat = gps.numsat;
		latitude = gps.latitude;
		longitude = gps.longitude;
		altitude = gps.altitude;
		heading = gps.heading;
		eph = gps.eph;
		epv = gps.epv;
		hdop = gps.hdop;
		speed = gps.speed;
		lx = gps.lx;
		ly = gps.ly;
	}

	public GPS clone() {
		GPS g = new GPS();

		g.flags = flags;
		g.numsat = numsat;
		g.heading = heading;
		g.latitude = latitude;
		g.longitude = longitude;
		g.eph = eph;
		g.epv = epv;
		g.hdop = hdop;
		g.altitude = altitude;
		g.speed = speed;
		g.fixtype = fixtype;
		g.lx = lx;
		g.ly = ly;

		return g;
	}

	public void clear() {

		flags = 0;
		numsat = 0;
		latitude = Double.NaN;
		longitude = Double.NaN;
		heading = 0;
		altitude = 0;
		eph = 0;
		epv = 0;
		hdop = 0;
		speed = 0;
		lx = Float.NaN;
		ly = Float.NaN;
		fixtype = 0;

	}

	public void setFlag(int box, boolean val) {
		if (val)
			flags = (short) (flags | (1 << box));
		else
			flags = (short) (flags & ~(1 << box));
	}

	public boolean isFlagSet(int... box) {
		for (int b : box)
			if ((flags & (1 << b)) == 0)
				return false;
		return true;
	}
}
