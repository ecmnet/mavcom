package com.comino.mavcom.utils;

public class SimplePoseJumpDetector {

	private float a = 0;
	private float b = 0;

	private float d1 = 0;
	private float d2 = 0;

	private float threshold = 0;

	private boolean is_initialized = false;

	public SimplePoseJumpDetector(float threshold) {
		this.threshold = threshold;
	}
	
	public SimplePoseJumpDetector(double threshold) {
		this.threshold = (float)threshold;
	}
	
	public boolean isJump(double a0, double b0, float dt_sec) {
		return isJump((float) a0, (float)b0, dt_sec);
	}

	public boolean isJump(float a0, float b0, float dt_sec) {
		if(is_initialized && dt_sec < 1f && dt_sec > 0.010f) {
			d1 = Math.abs(a - a0) / dt_sec;
			d2 = Math.abs(b - b0) / dt_sec;
			a = a0; b = b0;
			return d1 > threshold || d2 > threshold;
		} else {
			a = a0; b = b0;
			is_initialized = true;
			return false;
		}
	}
	
	public boolean isJump(double a0, double b0, double limit_a, double limit_b, float dt_sec) {
		return isJump((float) a0, (float)b0, (float)limit_a, (float)limit_b, dt_sec);
	}
	
	public boolean isJump(float a0, float b0, float limit_a, float limit_b, float dt_sec) {
		if(is_initialized && dt_sec < 1f && dt_sec > 0.010f) {
			d1 = Math.abs(a - a0) / dt_sec;
			d2 = Math.abs(b - b0) / dt_sec;
			a = a0; b = b0;
			return ( d1 > limit_a && d1 > threshold) || ( d2 > limit_b && d2 > threshold );
		} else {
			a = a0; b = b0;
			is_initialized = true;
			return false;
		}
	}

	public void reset() {
		is_initialized = false;
	}


}
