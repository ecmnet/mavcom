package com.comino.mavcom.struct;

import org.ejml.data.DMatrixRMaj;

import georegression.geometry.ConvertRotation3D_F64;
import georegression.struct.EulerType;

public class Attitude3D_F64 {

	public final static int ROLL = 0;
	public final static int PITCH = 1;
	public final static int YAW = 2;

	public double[] att = null;

	private static final double fromRad = 180.0 / Math.PI;

	public Attitude3D_F64() {
		this.att = new double[3];
	}

	public void set(double r, double p, double y) {
		this.att[ROLL] = r;
		this.att[PITCH] = p;
		this.att[YAW] = y;
	}

	public Attitude3D_F64 setFromMatrix(DMatrixRMaj rotation) {
		ConvertRotation3D_F64.matrixToEuler(rotation, EulerType.XYZ, att);
		return this;
	}

	public Attitude3D_F64 setFromMatrix(DMatrixRMaj rotation, EulerType type) {
		ConvertRotation3D_F64.matrixToEuler(rotation, type, att);
		return this;
	}

	public Attitude3D_F64 subtract(Attitude3D_F64 offset) {
		att[0] -= offset.att[0];
		att[1] -= offset.att[1];
		att[2] -= offset.att[2];
		return this;
	}

	public Attitude3D_F64 add(Attitude3D_F64 offset) {
		att[0] += offset.att[0];
		att[1] += offset.att[1];
		att[2] += offset.att[2];
		return this;
	}

	public double getRoll() {
		return att[ROLL];
	}

	public double getPitch() {
		return att[PITCH];
	}

	public double getYaw() {
		return att[YAW];
	}

	public float getInDegree(int type) {
		switch (type) {
		case PITCH:
			return fromRad(att[PITCH]);
		case ROLL:
			return fromRad(att[ROLL]);
		case YAW:
			return fromRad(att[YAW]);
		}
		return -1;
	}

	private float fromRad(double radians) {
		float deg = (float) (radians * fromRad) % 360;
		if (deg < 0)
			deg += 360;
		return deg;

	}

	public String toString() {
		return "P:" + getInDegree(PITCH) + "R:" + getInDegree(ROLL) + "Y:" + getInDegree(YAW);
	}

}
