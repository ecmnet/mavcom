package com.comino.mavcom.struct.objects;

import com.comino.mavcom.utils.MSP3DUtils;

import georegression.struct.GeoTuple3D_F32;

public class RectPrism extends AbstractConvexObject {
	
	// Note no ratiation!
	
	private GeoTuple3D_F32<?> lengths;
	
	public RectPrism(GeoTuple3D_F32<?> center, GeoTuple3D_F32<?> sideLengths) {
		super(center);
		this.lengths = sideLengths;
	}

	@Override
	public Boundary getTangentPlane(GeoTuple3D_F32<?> p) {
		Boundary boundary = new Boundary();
        boundary.n.setTo(p.x - center.x, p.y - center.y,p.z - center.z); boundary.n.normalize();
        
        
        for (int i = 0; i < 3; i++) {
            if (boundary.p.getIdx(i) < -lengths.getIdx(i) / 2) {
            	boundary.p.setIdx(i, -lengths.getIdx(i) / 2);
            } else if (boundary.p.getIdx(i) > lengths.getIdx(i) / 2) {
            	boundary.p.setIdx(i, lengths.getIdx(i) / 2);
            } 
        }
        
        // Calculates boundary.n = (p - boundary.p).GetUnitVector();
        boundary.n.setTo(boundary.p); boundary.n.scale(-1.0f); boundary.n.plusIP(p); boundary.n.normalize();
        
		return boundary;
	}

	@Override
	public boolean isPointInside(GeoTuple3D_F32<?> p) {
		return (Math.abs(p.x) <= lengths.x / 2)
		   &&  (Math.abs(p.y) <= lengths.y / 2)
		   &&  (Math.abs(p.z) <= lengths.z / 2);
	}

	@Override
	public boolean isValid() {
		return MSP3DUtils.isFinite(center) && MSP3DUtils.isFinite(lengths) && lengths.normSq() > 0;
	}

}
