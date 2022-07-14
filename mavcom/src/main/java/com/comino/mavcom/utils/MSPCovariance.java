package com.comino.mavcom.utils;

import org.apache.commons.math3.stat.correlation.Covariance;

public class MSPCovariance {

	private final int      window_size;
	private final double[]  data1;
	private final double[]  data2;
	private int            count=0;
	
	private Covariance cov;


	public MSPCovariance(int window_size) {
		this.window_size  = window_size;
		this.data1        = new double[window_size];
		this.data2        = new double[window_size];
		this.cov          = new Covariance();
	}

	public double determine(double val1, double val2, boolean bias) {
		
		if(count < window_size) {
			data1[count]   = val1;
			data2[count++] = val2;
			return 0;
		}
		
		for(int i = 0; i < window_size-1; i++) {
			data1[i] = data1[i+1];
			data2[i] = data2[i+1];
		}
		data1[window_size - 1] = val1;
		data2[window_size - 1] = val2;
		
		return cov.covariance(data2, data1, bias);
	}
	
	public void reset() {
		count = 0;
	}
	
//	public static void main(String[] args) {
//		
//		MSPCovariance c = new MSPCovariance(10);
//		for(int i=0; i<100; i++)
//			System.out.println(c.determine(Math.random(), Math.random(), false));
//		
//	}

}
