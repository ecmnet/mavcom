package com.comino.mavcom.struct.objects;

import georegression.struct.point.Vector3D_F32;

public class Boundary {
	
	public Vector3D_F32      p;
	public Vector3D_F32      n;
	
	public Boundary(Vector3D_F32 p, Vector3D_F32 n) {
		this.p = p;
		this.n = n;
	}
	
	public Boundary() {
		this.p = new Vector3D_F32();
		this.n = new Vector3D_F32();
	}
	
	public String toString() {
		return "P: "+p+" N: "+n;
	}

}
