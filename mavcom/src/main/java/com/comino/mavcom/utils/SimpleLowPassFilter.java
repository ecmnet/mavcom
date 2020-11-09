package com.comino.mavcom.utils;

public class SimpleLowPassFilter {
	
	double mean   = 0;
	double factor = 1;
	
	
	public SimpleLowPassFilter(double factor) {
		super();
		this.factor = factor;
	}
	
	public void add(double value) {
		if(mean!=0)
		  mean = mean * (1 - factor) + value * factor;
		else
		  mean = value;
	}
	
	public double getMeanAbs() {
		return Math.abs(mean);
	}
	
	public double getMean() {
		return mean;
	}
	
	public void clear() {
		mean = 0;
	}

}
