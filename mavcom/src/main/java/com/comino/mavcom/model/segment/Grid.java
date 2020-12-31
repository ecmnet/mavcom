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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.comino.mavcom.model.segment.generic.Segment;
import com.comino.mavcom.model.struct.MapPoint3D_F32;

import georegression.struct.point.Point3D_F32;

public class Grid extends Segment {

	public static final byte  STATUS_TRANSFER   = 0;
	public static final byte  STATUS_CLEARING   = 1;

	public static final float GRID_EXTENSION_M  = 20.0f;
	public static final float GRID_RESOLUTION_M = 0.05f;

	private static final long serialVersionUID = -77272456745165428L;

	private static int blockx;
	private static int blocky;
	private static int blockz;

	private int      dimension 		 = 0;
	private int      resolution_cm 	 = 0;
	private int      extension_cm    = 0;
	private int      max_length      = 0;
	private int      blocks_per_m    = 0;

	private static  LinkedList<Integer>  transfer;

	public int      count;
	public byte    status;

	private int      cx,cy, cz;
	private int      vx,vy, vz;

	public Grid() {
		this(GRID_EXTENSION_M, GRID_RESOLUTION_M);
	}


	public Grid(float extension_m, float resolution_m) {
		//		this.data  = new ConcurrentHashMap<Integer, BlockPoint2D>(0);
		this.count    = 0;
		this.status   = 0;
		this.extension_cm = (int)(extension_m) * 100 * 2;
		this.dimension = (int)(extension_m/resolution_m)*2;
		this.resolution_cm = (int)(resolution_m*100f);
		this.blocks_per_m = 100/resolution_cm;
		this.cx = dimension / 2;
		this.cy = dimension / 2;
		this.cz = dimension / 2;
		this.max_length = dimension * dimension * dimension;

		transfer = new LinkedList<Integer>();

		setIndicator(0,0,0);

	}

	public void set(Grid a) {

		dimension 		 = a.dimension;
		resolution_cm 	 = a.resolution_cm;
		extension_cm     = a.extension_cm;
		max_length       = a.max_length;
		blocks_per_m     = a.blocks_per_m;

		cx               = a.cx;
		cy               = a.cy;
		cz               = a.cz;

		vx               = a.vx;
		vy               = a.vy;
		vz               = a.vz;

	}

	public Grid clone() {
		Grid a = new Grid();
		a.set(this);
		return a;
	}

	// Transfer via block only. positive values => set block; negative => remove block

	public boolean toArray(long[] array) {
		try {
			if(!hasTransfers())
				return false;
			if(transfer.isEmpty() || array == null )
				return false;

			synchronized(this) {
				Arrays.fill(array, 0);
				for(int i=0; i< array.length && transfer.size() > 0;i++) {
					array[i] = transfer.poll();
				}
			}
			return true;
		}
		catch(Exception e) {
			//	System.out.println("Array-Transfer: "+e.getMessage()+"A="+array+" T="+transfer);
			return false;
		}
	}

	public boolean hasTransfers() {
		return transfer!=null && !transfer.isEmpty();
	}

	public LinkedList<Integer> getTransfers() {
		return transfer;
	}

	@SuppressWarnings("unlikely-arg-type")
	public void fromArray(long[] array) {

		for(int i=0; i< array.length && array[i]!=0;i++) {
			if(!transfer.contains((int)array[i]))
			   transfer.add((int)array[i]);
		}
		count = transfer.size();
	}

	

	public void setProperties(float extension_m, float resolution_m) {

		if(extension_m == 0 || resolution_m == 0)
			return;

		this.dimension = (int)(extension_m/resolution_m)*2;
		this.resolution_cm = (int)(resolution_m*100f);
		this.cx = dimension / 2;
		this.cy = dimension / 2;
		this.max_length = dimension * dimension;
	}

	public void setIndicator(double vx, double vy, double vz) {
		this.vx = (int)Math.round((float)(vx) * blocks_per_m)+cx;
		this.vy = (int)Math.round((float)(vy) * blocks_per_m)+cy;
		this.vz = (int)Math.round((float)(vz) * blocks_per_m)+cz;
	}

	public boolean setBlock(double xpos, double ypos, double zpos) {
		return setBlock(xpos,ypos,zpos,true);
	}

	public boolean  setBlock(double xpos, double ypos, double zpos, boolean set) {
		setBlock(calculateBlock(xpos, ypos, zpos),set);
		return true;
	}

	public boolean  setBlock(int block, boolean set) {

		if(block< 0 || block > max_length)
			return false;

		if(set) {
			if(transfer.contains(block))
				return true;
			transfer.add(block);
		}
		else {
			if(transfer.contains(-block))
				return true;
			transfer.add(-block);
		}
		count = transfer.size();
		return true;
	}

	public float getResolution() {
		return resolution_cm / 100f;
	}

	public float getExtension() {
		return extension_cm / 200f;
	}

	public float getIndicatorX() {
		return (float)(vx-cx)*resolution_cm/100f;
	}

	public float getIndicatorY() {
		return (float)(vy-cy)*resolution_cm/100f;
	}

	public float getIndicatorZ() {
		return (float)(vz-cz)*resolution_cm/100f;
	}


	private int calculateBlock(double xpos, double ypos, double zpos) {
		blockx  =  (int)Math.round((float)xpos * blocks_per_m) + cx;
		if(blockx > dimension-1)
			blockx = dimension -1;
		if(blockx < 0)
			blockx = 0;
		blocky = (int)Math.round((float)ypos * blocks_per_m ) + cy;
		if(blocky > dimension-1)
			blocky = dimension -1;
		if(blocky < 0)
			blocky = 0;
		blockz = (int)Math.round((float)zpos * blocks_per_m ) + cy;
		if(blockz > dimension-1)
			blockz = dimension -1;
		if(blockz < 0)
			blockz = 0;
		return blockx + blocky * dimension + blockz * dimension * dimension;
	}





}
