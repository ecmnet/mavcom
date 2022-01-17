package com.comino.mavcom.struct;

import georegression.struct.GeoTuple3D_F32;

@SuppressWarnings("serial")
public class MapPoint3D_F32 extends GeoTuple3D_F32<MapPoint3D_F32> {

	public int value = 0;
	public long tms = 0;

	public MapPoint3D_F32(GeoTuple3D_F32<?> pt) {
		super(pt.x, pt.y, pt.z);
		this.value = 0;
		this.tms = System.currentTimeMillis();
	}

	public MapPoint3D_F32(MapPoint3D_F32 pt) {
		this(pt.x, pt.y, pt.z, pt.value, pt.tms);
	}

	public MapPoint3D_F32(float x, float y, float z) {
		super(x, y, z);
		this.tms = System.currentTimeMillis();
	}

	public MapPoint3D_F32(float x, float y, float z, int value) {
		super(x, y, z);
		this.value = value > 31 ? 31 : value;
		this.tms = System.currentTimeMillis();
	}

	public MapPoint3D_F32(float x, float y, float z, int value, long tms) {
		super(x, y, z);
		this.value = value > 31 ? 31 : value;
		this.tms = tms;
	}

	public int getValue() {
		return value;
	}

	public void set(float x, float y, float z, int value) {
		setTo(x, y, z);
		this.value = value > 31 ? 31 : value;
		this.tms = System.currentTimeMillis();
	}

	public boolean isIdentical(MapPoint3D_F32 t, float val, float pos_tol, int val_tol) {
		return this.isIdentical(t, pos_tol) && Math.abs(this.value - t.value) <= val_tol;
	}

	public long getTimestamp() {
		return tms;
	}

	public MapPoint3D_F32() {
		this.tms = System.currentTimeMillis();
	}

	@Override
	public MapPoint3D_F32 createNewInstance() {
		return new MapPoint3D_F32();
	}

}
