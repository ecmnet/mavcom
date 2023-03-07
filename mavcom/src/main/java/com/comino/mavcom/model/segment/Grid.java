/****************************************************************************
 *
 *   Copyright (c) 2017-2018 Eike Mansfeld ecm@gmx.de. All rights reserved.
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

import java.util.Arrays;
import java.util.LinkedList;

import com.comino.mavcom.model.segment.generic.Segment;

public class Grid extends Segment {

	public static final byte STATUS_TRANSFER = 0;
	public static final byte STATUS_CLEARING = 1;

	private static final long serialVersionUID = -77272456745165428L;

	private static final LinkedList<Long> transfer = new LinkedList<Long>(); 

	public int count;
	public byte status;

	public float ox, oy, oz; // Origin
	public float vx, vy, vz; // Vehicle position
	public float resolution;

	public Grid() {

		this.count = 0;
		this.status = 0;

	}

	public void set(Grid a) {

		ox = a.ox;
		oy = a.oy;
		oz = a.oz;

		vx = a.vx;
		vy = a.vy;
		vz = a.vz;
		
		resolution = a.resolution;

	}

	public Grid clone() {
		Grid a = new Grid();
		a.set(this);
		return a;
	}

	// Transfer via block only. 

	public boolean toArray(long[] array) {
		Long e;
		try {
			if (!hasTransfers() || transfer==null)
				return false;
			
			if (transfer.isEmpty() || array == null)
				return false;

				Arrays.fill(array, 0);
				for (int i = 0; i < array.length && !transfer.isEmpty(); i++) {
					e = transfer.poll();
					if(e!=null)
					   array[i] = e.longValue();

			}
			return true;
		} catch (Exception k) {
			k.printStackTrace();
			return false;
		}
	}

	public boolean hasTransfers() {
		return transfer != null && !transfer.isEmpty();
	}

	public LinkedList<Long> getTransfers() {
		return transfer;
	}
	
	public Long pop() {
		return transfer.pop();
	}
	
	public void add(Long e) {
	// if(!transfer.contains(e))
		   transfer.add(e);
	}

	public void setOrigin(float ox, float oy, float oz) {
		this.ox = ox;
		this.oy = oy;
		this.oz = oz;
	}
	
	public void setResolution(float resolution) {
		this.resolution = resolution;
	}

	@SuppressWarnings("unlikely-arg-type")
	public int fromArray(long[] array) {
		for (int i = 0; i < array.length && array[i] != 0; i++) {
			transfer.push(array[i]);	
		}
		count = transfer.size();
		return count;
	}

}
