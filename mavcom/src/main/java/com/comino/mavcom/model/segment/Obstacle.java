package com.comino.mavcom.model.segment;

import com.comino.mavcom.model.segment.generic.Segment;

import georegression.struct.GeoTuple3D_F32;
import georegression.struct.point.Point3D_F32;

public class Obstacle extends Segment {
	
	private static final long serialVersionUID = 2857821916988848659L;
	
	public float x;
	public float y;
	public float z;
	
	public float sx;
	public float sy;
	public float sz;
	
	public void set(Obstacle o) {
		x = o.x;
		y = o.y;
		z = o.z;
		
		sx = o.sx;
		sy = o.sy;
		sz = o.sz;	
	}
	
	public Obstacle clone() {
		Obstacle o = new Obstacle();
		o.set(this);;
		return o;
	}
	
	public void clear() {
		x = y = z = Float.NaN;
		sx = sy = sz = 0;
	}
	
	public GeoTuple3D_F32<?> getPos(Point3D_F32 p) {
		if(p==null)
			p = new Point3D_F32();
		p.setTo(x,y,z);
		return p;
	}
	
	public GeoTuple3D_F32<?> getSize(Point3D_F32 p) {
		if(p==null)
			p = new Point3D_F32();
		p.setTo(sx,sy,sz);
		return p;
	}

}
