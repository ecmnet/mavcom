package com.comino.mavcom.utils;

import java.util.ArrayList;
import java.util.List;

import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.struct.Polar3D_F32;
import com.comino.mavcom.struct.Polar4D_F32;

import georegression.geometry.ConvertRotation3D_F32;
import georegression.geometry.ConvertRotation3D_F64;
import georegression.struct.EulerType;
import georegression.struct.GeoTuple3D_F32;
import georegression.struct.GeoTuple3D_F64;
import georegression.struct.GeoTuple4D_F32;
import georegression.struct.GeoTuple4D_F64;
import georegression.struct.point.Point3D_F64;
import georegression.struct.point.Point3D_I32;
import georegression.struct.point.Vector3D_F32;
import georegression.struct.point.Vector3D_F64;
import georegression.struct.point.Vector4D_F32;
import georegression.struct.point.Vector4D_F64;
import georegression.struct.se.Se3_F32;
import georegression.struct.se.Se3_F64;

public class MSP3DUtils {

	public static final int ROLL = 0;
	public static final int PITCH = 1;
	public static final int YAW = 2;

	private static Point3D_F64 tmp_p = new Point3D_F64();

	public static Point3D_F64 toNED(Point3D_F64 p) {
		tmp_p.setTo(p);
		p.x = tmp_p.z;
		p.y = tmp_p.x;
		p.z = -tmp_p.y;
		return p;
	}

	public static String vector3D_F64ToString(Vector3D_F64 v) {
		return String.format("( % .2f,% .2f,% .2f )", v.x, v.y, v.z);
	}

	public static float distance3D(Vector4D_F32 t, Vector4D_F32 c) {
		return (float) Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
	}
	
	public static float distance3D(Vector4D_F32 t, Vector3D_F32 c) {
		return (float) Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
	}
	
	
	public static float distance3DSQ(Vector4D_F32 t, Vector3D_F32 c) {
		return (float)((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
	}
	
//	public static double distance3D(Vector4D_F64 t, Vector4D_F64 c) {
//		return Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
//	}
	
	public static double distance3D(GeoTuple3D_F64<?> t, GeoTuple4D_F64<?> c) {
		return Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
	}

	public static double distance3D(GeoTuple4D_F32<?> t, GeoTuple3D_F32<?> c) {
		return Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
	}

	public static double distance3DSQ(GeoTuple4D_F32<?> t, GeoTuple3D_F32<?> c) {
		return (t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z);
	}
	
	
	public static float distance2D(GeoTuple4D_F32<?> t, GeoTuple4D_F32<?> c) {
		return (float) Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));
	}
	
	public static float distance2D(GeoTuple3D_F64<?> t, GeoTuple3D_F64<?> c) {
		return (float) Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));
	}
	
	public static float distance2DSQ(GeoTuple3D_F64<?> t, GeoTuple3D_F64<?> c) {
		return (float)((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));
	}
	
	public static float distance2D(GeoTuple3D_F32<?> t, GeoTuple3D_F32<?> c) {
		return (float) Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));
	}
	
	public static float distance2DSQ(GeoTuple3D_F32<?> t, GeoTuple3D_F32<?> c) {
		return (t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y);
	}
	
	
	public static float distance2D(GeoTuple4D_F32<?> t, GeoTuple3D_F32<?> c) {
		return (float) Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));
	}

	public static float distance2D(Vector3D_F32 t, Vector3D_F32 c) {
		return (float) Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));
	}

	public static double distance2D(Vector3D_F64 t, Vector3D_F64 c) {

		if(Double.isNaN(t.x) || Double.isNaN(c.x))
			return (float)Math.sqrt((t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
		if(Double.isNaN(t.y) || Double.isNaN(c.y))
			return (float)Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.z - c.z) * (t.z - c.z));
		if(Double.isNaN(t.z) || Double.isNaN(c.z))
			return (float)Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));

		return  Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));
	}

	public static float distance3D(Vector3D_F32 t, Vector3D_F32 c) {

		if(Float.isNaN(t.x) || Float.isNaN(c.x))
			return (float)Math.sqrt((t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
		if(Float.isNaN(t.y) || Float.isNaN(c.y))
			return (float)Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.z - c.z) * (t.z - c.z));
		if(Float.isNaN(t.z) || Float.isNaN(c.z))
			return (float)Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));

		return (float) Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
	}

	public static double distance3D(GeoTuple3D_F64<?> t, GeoTuple3D_F64<?> c) {

		if(Double.isNaN(t.x) || Double.isNaN(c.x))
			return (float)Math.sqrt((t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
		if(Double.isNaN(t.y) || Double.isNaN(c.y))
			return (float)Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.z - c.z) * (t.z - c.z));
		if(Double.isNaN(t.z) || Double.isNaN(c.z))
			return (float)Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));


		return Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
	}

	public static float distance3D(GeoTuple4D_F32<?> t, GeoTuple4D_F32<?> c) {
        return (float)Math.sqrt(distance3DSQ(t,c));
	}
	
	public static float distance3DSQ(GeoTuple4D_F32<?> t, GeoTuple4D_F32<?> c) {

		if(Float.isNaN(t.x) || Float.isNaN(c.x))
			return (float)((t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
		if(Float.isNaN(t.y) || Float.isNaN(c.y))
			return (float)((t.x - c.x) * (t.x - c.x) + (t.z - c.z) * (t.z - c.z));
		if(Float.isNaN(t.z) || Float.isNaN(c.z))
			return (float)((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));

		return (float)((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
	}

	public static float distance3D(GeoTuple4D_F64<?> t, GeoTuple3D_F64<?> c) {

		if(Double.isNaN(t.x) || Double.isNaN(c.x))
			return (float)Math.sqrt((t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
		if(Double.isNaN(t.y) || Double.isNaN(c.y))
			return (float)Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.z - c.z) * (t.z - c.z));
		if(Double.isNaN(t.z) || Double.isNaN(c.z))
			return (float)Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y));

		return (float)Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
	}

	public static float distance3D(Point3D_I32 t, Point3D_I32 c) {
		return (float) Math.sqrt((t.x - c.x) * (t.x - c.x) + (t.y - c.y) * (t.y - c.y) + (t.z - c.z) * (t.z - c.z));
	}

	public static float angleXZ(GeoTuple3D_F64<?> t) {
		return (float) Math.asin((t.z) / t.norm());
	}

	public static float angleXY(GeoTuple3D_F64<?> t) {
		return angleXY((float)t.x,(float)t.y);
	}
	
	public static float angleXY(GeoTuple4D_F64<?> t) {
		return angleXY((float)t.x,(float)t.y);
	}
	
	public static float angleXY(GeoTuple3D_F32<?> t) {
		return angleXY((float)t.x,(float)t.y);
	}
	
	public static float angleXY(GeoTuple4D_F32<?> t) {
		return angleXY((float)t.x,(float)t.y);
	}

	public static float angleXY(float dx, float dy) {

		if (dx == 0 && dy >= 0)
			return (float) Math.PI / 2;
		if (dx == 0 && dy < 0)
			return -(float) Math.PI / 2;

		if ((dx >= 0 && dy > 0) || (dx >= 0 && dy < 0))
			return (float) Math.atan(dy / dx);
		if ((dx < 0 && dy >= 0) || (dx < 0 && dy < 0))
			return (float) (Math.atan(dy / dx) + Math.PI);

		return 0;
	}

	public static float angleXY(Vector3D_F32 t, Vector3D_F32 c) {

		float dx = t.getX() - c.getX();
		float dy = t.getY() - c.getY();

		if (dx == 0 && dy >= 0)
			return (float) Math.PI / 2;
		if (dx == 0 && dy < 0)
			return -(float) Math.PI / 2;

		if ((dx >= 0 && dy > 0) || (dx >= 0 && dy < 0))
			return (float) Math.atan(dy / dx);
		if ((dx < 0 && dy >= 0) || (dx < 0 && dy < 0))
			return (float) (Math.atan(dy / dx) + Math.PI);

		return 0;
	}
	
	public static float angleXY(GeoTuple3D_F64<?> t, GeoTuple3D_F64<?> c) {
		float dx = (float)t.getX() -  (float)c.getX();
		float dy = (float)t.getY() -  (float)c.getY();

		if (dx == 0 && dy >= 0)
			return (float) Math.PI / 2;
		if (dx == 0 && dy < 0)
			return -(float) Math.PI / 2;

		if ((dx >= 0 && dy > 0) || (dx >= 0 && dy < 0))
			return (float) Math.atan(dy / dx);
		if ((dx < 0 && dy >= 0) || (dx < 0 && dy < 0))
			return (float) (Math.atan(dy / dx) + Math.PI);

		return 0;
	}

	public static float angleXY(GeoTuple4D_F32<?> t, GeoTuple4D_F32<?> c) {

		float dx = t.getX() - c.getX();
		float dy = t.getY() - c.getY();

		if (dx == 0 && dy >= 0)
			return (float) Math.PI / 2;
		if (dx == 0 && dy < 0)
			return -(float) Math.PI / 2;

		if ((dx >= 0 && dy > 0) || (dx >= 0 && dy < 0))
			return (float) Math.atan(dy / dx);
		if ((dx < 0 && dy >= 0) || (dx < 0 && dy < 0))
			return (float) (Math.atan(dy / dx) + Math.PI);

		return 0;
	}

	public static float angleXZ(Vector4D_F32 t, Vector4D_F32 c) {
		return (float) Math.asin((t.z - c.z) / distance3D(t, c));
	}
	
	public static float angleXZ(GeoTuple4D_F32 t, GeoTuple4D_F32 c) {
		return (float) Math.asin((t.z - c.z) / distance3D(t, c));
	}

	public static float angleXZ(Vector3D_F32 t, Vector3D_F32 c) {
		return (float) Math.asin((t.z - c.z) / distance3D(t, c));
	}

	public static Vector4D_F32 getCurrentVector4D(DataModel model) {
		return new Vector4D_F32(model.state.l_x, model.state.l_y, model.state.l_z, model.attitude.y);
	}

	public static Vector3D_F32 convertTo3D(Vector4D_F32 v) {
		return new Vector3D_F32(v.x, v.y, v.z);
	}

	public static Vector3D_F32 convertTo3D(Vector4D_F32 v, Vector3D_F32 t) {
		t.setTo(v.x, v.y, v.z);
		return t;
	}

	public static boolean convertCurrentPosition(DataModel model, GeoTuple4D_F64<?> point) {
		point.setTo(model.state.l_x, model.state.l_y, model.state.l_z, model.attitude.y);
		return isFinite(point);
	}

	public static boolean convertCurrentPosition(DataModel model, GeoTuple4D_F32<?> vector) {
		vector.setTo(model.state.l_x, model.state.l_y, model.state.l_z, model.attitude.y);
		return isFinite(vector);
	}

	public static boolean convertCurrentPosition(DataModel model, Vector3D_F64 vector) {
		vector.setTo(model.state.l_x, model.state.l_y, model.state.l_z);
		return isFinite(vector);
	}

	public static boolean convertCurrentPosition(DataModel model, Vector4D_F64 vector) {
		vector.setTo(model.state.l_x, model.state.l_y, model.state.l_z,model.attitude.y );
		return isFinite(vector);
	}

	public static boolean convertCurrentState(DataModel model, Vector4D_F64 pos, Vector4D_F64 speed) {
		pos.setTo(model.state.l_x, model.state.l_y, model.state.l_z,model.attitude.y );
		speed.setTo(model.state.l_vx, model.state.l_vy, model.state.l_vz, model.attitude.yr);
		return isFinite(pos) && isFinite(speed);
	}

	public static boolean convertTargetState(DataModel model, Vector4D_F32 vector) {
		vector.setTo(model.target_state.l_x, model.target_state.l_y, model.target_state.l_z, model.attitude.y);
		return isFinite(vector);
	}

	public static boolean convertTargetState(DataModel model, GeoTuple4D_F32<?> vector) {
		vector.setTo(model.target_state.l_x, model.target_state.l_y, model.target_state.l_z, model.attitude.y);
		return isFinite(vector);
	}
	
	public static boolean convertTargetSpeed(DataModel model, GeoTuple4D_F32<?> vector) {
		vector.setTo(model.target_state.l_vx, model.target_state.l_vy, model.target_state.l_vz, model.attitude.yr);
		return isFinite(vector);
	}
	
	public static boolean convertTargetAcceleration(DataModel model, GeoTuple3D_F32<?> vector) {
		vector.setTo(model.target_state.l_ax, model.target_state.l_ay, model.target_state.l_az);
		return isFinite(vector);
	}
	
	public static boolean convertTargetAcceleration(DataModel model, GeoTuple4D_F32<?> vector) {
		vector.setTo(model.target_state.l_ax, model.target_state.l_ay, model.target_state.l_az,0);
		return isFinite(vector);
	}

	public static boolean convertCurrentSpeed(DataModel model, Vector4D_F32 vector) {
		vector.setTo(model.state.l_vx, model.state.l_vy, model.state.l_vz, model.attitude.yr);
		return isFinite(vector);
	}
	
	public static boolean convertCurrentSpeed(DataModel model, Vector3D_F32 vector) {
		vector.setTo(model.state.l_vx, model.state.l_vy, model.state.l_vz);
		return isFinite(vector);
	}

	public static boolean convertCurrentSpeed(DataModel model, GeoTuple4D_F32<?> vector) {
		vector.setTo(model.state.l_vx, model.state.l_vy, model.state.l_vz, model.attitude.yr);
		return isFinite(vector);
	}

	public static boolean convertCurrentAcceleration(DataModel model, Vector4D_F32 vector) {
		vector.setTo(model.state.l_ax, model.state.l_ay, model.state.l_az, 0);
		return isFinite(vector);
	}

	public static boolean convertCurrentAcceleration(DataModel model, GeoTuple4D_F32<?> vector) {
		vector.setTo(model.state.l_ax, model.state.l_ay, model.state.l_az, 0);
		return isFinite(vector);
	}

	public static boolean convertCurrentSpeed(DataModel model, Vector4D_F64 vector) {
		vector.setTo(model.state.l_vx, model.state.l_vy, model.state.l_vz, model.attitude.yr);
		return isFinite(vector);
	}

	public static boolean convertCurrentSpeed(DataModel model, Vector3D_F64 vector) {
		vector.setTo(model.state.l_vx, model.state.l_vy, model.state.l_vz);
		return isFinite(vector);
	}

	public static boolean convertCurrentSpeed(DataModel model, Polar3D_F32 polar) {
		polar.set(model.state.l_vx, model.state.l_vy, model.state.l_vz);
		return Float.isFinite(polar.value);
	}

	public static boolean convertCurrentSpeed(DataModel model, Polar4D_F32 polar) {
		polar.set(model.state.l_vx, model.state.l_vy, model.state.l_vz, model.attitude.yr);
		return Float.isFinite(polar.value);
	}

	public static void setNaN(Vector4D_F32 vector) {
		vector.setTo(Float.NaN, Float.NaN, Float.NaN, Float.NaN);
	}

	public static void setNaN(Vector3D_F64 vector) {
		vector.setTo(Double.NaN, Double.NaN, Double.NaN);
	}

	public static void setNaN(Se3_F64 s) {
		s.T.setTo(Double.NaN, Double.NaN, Double.NaN);
		for (int i = 0; i < s.R.getNumElements(); i++)
			s.R.set(i, Double.NaN);
	}

	public static Vector4D_F32 filter(Vector4D_F32 current, Vector4D_F32 last, float factor) {
		if (Float.isFinite(last.x))
			current.x = factor * current.x + (1 - factor) * last.x;
		if (Float.isFinite(last.y))
			current.y = factor * current.y + (1 - factor) * last.y;
		if (Float.isFinite(last.z))
			current.z = factor * current.z + (1 - factor) * last.z;
		if (Float.isFinite(last.w))
			current.w = factor * current.w + (1 - factor) * last.w;
		last.setTo(current);
		return current;
	}

	public static Vector3D_F64 filter(Vector3D_F64 current, Vector3D_F64 last, float factor) {
		if (Double.isFinite(last.x))
			current.x = factor * current.x + (1 - factor) * last.x;
		if (Double.isFinite(last.y))
			current.y = factor * current.y + (1 - factor) * last.y;
		if (Double.isFinite(last.z))
			current.z = factor * current.z + (1 - factor) * last.z;
		last.setTo(current);
		return current;
	}

	public static boolean hasNaN(Vector3D_F64 vector) {
		return Double.isNaN(vector.x) || Double.isNaN(vector.y) || Double.isNaN(vector.z);
	}

	public static boolean hasNaN(Vector4D_F32 vector) {
		return Float.isNaN(vector.x) || Float.isNaN(vector.y) || Float.isNaN(vector.z);
	}

	public static boolean isNaN(Vector4D_F32 vector) {
		return Float.isNaN(vector.x) && Float.isNaN(vector.y) && Float.isNaN(vector.z) && Float.isNaN(vector.w);
	}
	
	public static boolean isFinite(GeoTuple3D_F32<?>  vector) {
		return Double.isFinite(vector.x) && Double.isFinite(vector.y) && Double.isFinite(vector.z);
	}

	public static boolean isFinite(GeoTuple4D_F64<?>  vector) {
		return Double.isFinite(vector.x) && Double.isFinite(vector.y) && Double.isFinite(vector.z);
	}
	
	public static boolean isFinite(GeoTuple3D_F64<?>  vector) {
		return Double.isFinite(vector.x) && Double.isFinite(vector.y) && Double.isFinite(vector.z);
	}

	public static boolean isFinite(GeoTuple4D_F32<?> vector) {
		return Float.isFinite(vector.x) && Float.isFinite(vector.y) && Float.isFinite(vector.z);
	}

	public static boolean isFinite(Vector4D_F64 vector) {
		return Double.isFinite(vector.x) && Double.isFinite(vector.y) && Double.isFinite(vector.z);
	}

	public static boolean isFinite(Vector3D_F64 vector) {
		return Double.isFinite(vector.x) && Double.isFinite(vector.y) && Double.isFinite(vector.z);
	}

	public static boolean replaceNaN3D(GeoTuple4D_F32<?> target, GeoTuple4D_F32<?> source) {
		if (Float.isNaN(target.x))
			target.x = source.x;
		if (Float.isNaN(target.y))
			target.y = source.y;
		if (Float.isNaN(target.z))
			target.z = source.z;
		return isFinite(target);
	}

	public static void convertModelToSe3_F32(DataModel model, Se3_F32 state) {
		convertToSe3_F32(model.state.l_x, model.state.l_y, model.state.l_z, model.attitude.r, model.attitude.p,
				model.attitude.y, state);
	}

	public static void convertModelToSe3_F64(DataModel model, Se3_F64 state) {
		if(isValid(model)) {
			convertToSe3_F64(model.state.l_x, model.state.l_y, model.state.l_z, model.attitude.r, model.attitude.p,
					model.attitude.y, state);
		}
	}

	public static void convertModel90ToSe3_F64(DataModel model, Se3_F64 state) {
		convertToSe3_F64(model.state.l_y, model.state.l_x, model.state.l_z, model.attitude.p, model.attitude.r,
				model.attitude.y, state);
	}

	public static void convertModelRotationToSe3_F64(DataModel model, Se3_F64 state) {
		ConvertRotation3D_F64.eulerToMatrix(EulerType.XYZ, model.attitude.r, model.attitude.p, model.attitude.y,
				state.R);
	}

	public static void convertModelAngularRatesToVec_F64(DataModel model, Vector3D_F64 state) {
		state.x = model.attitude.rr;
		state.y = model.attitude.pr;
		state.z = model.attitude.yr;
	}

	public static void convertModelXYToSe3_F32(DataModel model, Se3_F32 state) {
		convertToSe3_F32(model.state.l_x, model.state.l_y, model.state.l_z, 0, 0, model.attitude.y, state);
	}

	public static void convertModelXYToSe3_F64(DataModel model, Se3_F64 state) {
		convertToSe3_F64(model.state.l_x, model.state.l_y, model.state.l_z, 0, 0, model.attitude.y, state);
	}

	public static void convertToSe3_F32(float x, float y, float z, float r, float p, float yw, Se3_F32 state) {
		state.setTranslation(x, y, z);
		ConvertRotation3D_F32.eulerToMatrix(EulerType.XYZ, r, p, yw, state.getRotation());
	}

	public static void convertToSe3_F64(double x, double y, double z, double r, double p, double yw, Se3_F64 state) {
		state.setTranslation(x, y, z);
		ConvertRotation3D_F64.eulerToMatrix(EulerType.XYZ, r, p, yw, state.getRotation());
	}

	public static float ConvertSe3_F32ToYaw(Se3_F32 state) {
		float[] v = new float[3];
		return ConvertSe3_F32ToYaw(state, v);
	}

	public static float ConvertSe3_F32ToYaw(Se3_F32 state, float[] v) {
		ConvertRotation3D_F32.matrixToEuler(state.R, EulerType.XYZ, v);
		return v[YAW];
	}

	public static double ConvertSe3_F64ToYaw(Se3_F64 state) {
		double[] v = new double[3];
		return ConvertSe3_F64ToYaw(state, v);
	}

	public static double ConvertSe3_F64ToYaw(Se3_F64 state, double[] v) {
		ConvertRotation3D_F64.matrixToEuler(state.R, EulerType.XYZ, v);
		return v[YAW];
	}

	public static boolean isValid(Vector4D_F64 vector) {
		return  Double.isFinite(vector.x)  &&
				Double.isFinite(vector.y)  &&
				Double.isFinite(vector.z)  &&
				Double.isFinite(vector.w);		
	}


	public static boolean isValid(DataModel model) {
		return  Float.isFinite(model.state.l_x)  &&
				Float.isFinite(model.state.l_y)  &&
				Float.isFinite(model.state.l_z)  &&
				Float.isFinite(model.attitude.p) &&
				Float.isFinite(model.attitude.r) &&
				Float.isFinite(model.attitude.y);
	}

	public static float getXYDirection(float dx, float dy) {

		if (dx == 0 && dy >= 0)
			return (float) Math.PI / 2;
		if (dx == 0 && dy < 0)
			return -(float) Math.PI / 2;

		if ((dx > 0 && dy >= 0) || (dx >= 0 && dy < 0))
			return (float) Math.atan(dy / dx);
		if ((dx < 0 && dy >= 0) || (dx < 0 && dy < 0))
			return (float) (Math.atan(dy / dx) + Math.PI);

		return Float.NaN;
	}

	public static void rotateXY(Vector4D_F32 in, Vector4D_F32 out, float angle) {

		if (Float.isNaN(in.x))
			out.x = Float.NaN;
		else
			out.x = in.x * (float) Math.cos(angle) + in.y * (float) Math.sin(angle);

		if (Float.isNaN(in.y))
			out.y = Float.NaN;
		else
			out.y = -in.x * (float) Math.sin(angle) + in.y * (float) Math.cos(angle);
		out.z = in.z;
		out.w = in.w;
	}

	public static float getXYDirection(Vector4D_F32 target, Vector4D_F32 current) {

		return getXYDirection(target.getX() - current.getX(), target.getY() - current.getY());
	}

	public static Vector3D_F32 convertToVector3D(float angle_xy, float angle_xz, float speed, Vector3D_F32 result) {
		result.setTo((float) (Math.cos(angle_xy) * Math.cos(angle_xz)),
				(float) (Math.sin(angle_xy) * Math.cos(angle_xz)), (float) Math.sin(angle_xz));
		result.scale(speed);
		return result;
	}

	public static Vector3D_F32 interpolateLinear(float where, Vector3D_F32 start, Vector3D_F32 end,
			Vector3D_F32 result) {
		result.x = where * (end.x - start.x);
		result.y = where * (end.y - start.y);
		result.z = where * (end.z - start.z);
		return result;
	}

	public List<Vector3D_F32> interpolateLinear(int steps, Vector3D_F32 start, Vector3D_F32 end) {
		List<Vector3D_F32> result = new ArrayList<Vector3D_F32>();
		float delta = end.distance(start) / steps;
		for (int i = 1; i < steps; i++)
			result.add(interpolateLinear(i * delta, start, end, new Vector3D_F32()));
		return result;
	}

	public static float norm3D(GeoTuple4D_F32<?> v) {
		return (float)Math.sqrt(v.x*v.x+v.y*v.y+v.z*v.z);
	}

}
