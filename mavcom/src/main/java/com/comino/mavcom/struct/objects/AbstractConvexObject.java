package com.comino.mavcom.struct.objects;

import georegression.struct.GeoTuple3D_F32;
import georegression.struct.GeoTuple4D_F32;

public abstract class AbstractConvexObject {
	
	protected GeoTuple3D_F32<?> center;
	
	public AbstractConvexObject(GeoTuple3D_F32<?> center) {
		this.center = center;
	}
	
	public abstract Boundary getTangentPlane(GeoTuple3D_F32<?> p); 
	public abstract boolean  isPointInside(GeoTuple3D_F32<?> p); 
	public abstract boolean  isValid();
	
	public GeoTuple3D_F32<?> getCenter() {
		return center;
	}
	

}
