package com.comino.mavcom.utils;

public class SimpleComplementaryFilter {

	double mean   = 0;
	double factor = 1;


	public SimpleComplementaryFilter(double factor) {
		super();
		this.factor = factor;
	}

	public void add(double value1, double value2) {
		mean = value2 * (1 - factor) + value1 * factor;
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
