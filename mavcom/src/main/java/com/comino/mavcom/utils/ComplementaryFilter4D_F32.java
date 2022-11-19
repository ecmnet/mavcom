package com.comino.mavcom.utils;

import georegression.struct.GeoTuple4D_F32;
import georegression.struct.point.Point4D_F32;

public class ComplementaryFilter4D_F32 {

	private float factor = 1;
	private GeoTuple4D_F32<?>  filtered;
	private int count;

	public ComplementaryFilter4D_F32(float factor) {
		this(factor, new Point4D_F32 ());
	}

	public ComplementaryFilter4D_F32(float factor, GeoTuple4D_F32<?>  filtered) {
		this.factor = factor;
		this.filtered = filtered;
	}

	public void add(GeoTuple4D_F32<?> value) {
		if (filtered.x != 0)
			filtered.x = filtered.x * (1 - factor) + value.x * factor;
		else
			filtered.x = value.x;

		if (filtered.y != 0)
			filtered.y = filtered.y * (1 - factor) + value.y * factor;
		else
			filtered.y = value.y;

		if (filtered.z != 0)
			filtered.z = filtered.z * (1 - factor) + value.z * factor;
		else
			filtered.z = value.z;
		
		if (filtered.w != 0)
			filtered.w = filtered.w * (1 - factor) + value.w * factor;
		else
			filtered.w = value.w;

		count++;
	}
	
	public void add(float x, float y, float z, float w) {
		if (filtered.x != 0)
			filtered.x = filtered.x * (1 - factor) + x * factor;
		else
			filtered.x = x;

		if (filtered.y != 0)
			filtered.y = filtered.y * (1 - factor) + y * factor;
		else
			filtered.y = y;

		if (filtered.z != 0)
			filtered.z = filtered.z * (1 - factor) + z * factor;
		else
			filtered.z = z;
		
		if (filtered.w != 0)
			filtered.w = filtered.w * (1 - factor) + w * factor;
		else
			filtered.w = w;

		count++;
	}

	public void add(GeoTuple4D_F32<?>  value1, GeoTuple4D_F32<?>  value2) {
		filtered.x = value2.x * (1 - factor) + value1.x * factor;
		filtered.y = value2.y * (1 - factor) + value1.y * factor;
		filtered.z = value2.z * (1 - factor) + value1.z * factor;
		filtered.w = value2.w * (1 - factor) + value1.w * factor;
		count++;
	}

	public GeoTuple4D_F32<?>  get() {
		return filtered;
	}

	public double getNorm() {
		return filtered.norm();
	}

	public int getCount() {
		return count;
	}

	public void reset() {
		filtered.setTo(0, 0, 0, 0);
		count = 0;
	}

}
