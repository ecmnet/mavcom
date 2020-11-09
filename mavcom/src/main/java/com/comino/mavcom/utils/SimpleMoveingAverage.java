package com.comino.mavcom.utils;

public class SimpleMoveingAverage {

	private int     index  = 0;
	private int     count  = 0;
	private double[] data  = null;
	private double   mean  = 0;

	public SimpleMoveingAverage(int length) {
		this.data = new double[length];
	}

	public void add(double value) {
		double sum = 0;

		if(count < data.length) 
			data[++count-1] = value;
		else {
            data[index++] = value;
            if(index == data.length)
            	index = 0;
		}
		
		for(int i = 0; i < count; i++ )
			sum += data[i];
		mean = sum / count;	  
	}

	public double getMean() {
		return mean;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		for(int i=0;i<data.length;i++) 
		b.append(Double.toString(data[i])+" ");
		return b.toString();
	}

}
