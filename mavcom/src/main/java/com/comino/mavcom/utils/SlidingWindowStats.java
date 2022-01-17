package com.comino.mavcom.utils;

public class SlidingWindowStats {

	private final CircularBuffer circularBuffer;

	private double mean;
	private double dsquared;

	public SlidingWindowStats(int size) {
		this.circularBuffer = new CircularBuffer(size);
	}

	public int count() {
		return circularBuffer.length();
	}

	public double update(double newValue) {
		double poppedValue = this.circularBuffer.append(newValue);

		if (this.count() == 1 && poppedValue == 0) {
			// initialize when the first value is added
			this.mean = newValue;
			this.dsquared = 0;
		} else if (poppedValue == 0) {
			// if the buffer is not full yet, use standard Welford method
			double meanIncrement = (newValue - this.mean) / this.count();
			double newMean = this.mean + meanIncrement;

			double dSquaredIncrement = ((newValue - newMean) * (newValue - this.mean));
			double newDSquared = this.dsquared + dSquaredIncrement;

			this.mean = newMean;
			this.dsquared = newDSquared;
		} else {
			// once the buffer is full, adjust Welford Method for window size
			double meanIncrement = (newValue - poppedValue) / this.count();
			double newMean = this.mean + meanIncrement;

			double dSquaredIncrement = ((newValue - poppedValue) * (newValue - newMean + poppedValue - this.mean));
			double newDSquared = this.dsquared + dSquaredIncrement;

			this.mean = newMean;
			this.dsquared = newDSquared;
		}
		return poppedValue;
	}

	public double mean() {
		return this.mean;
	}

	public double dSquared() {
		return this.dsquared;
	}

	public double populationVariance() {
		return this.dsquared / this.count();
	}

	public double populationStdDev() {
		return Math.sqrt(this.populationVariance());
	}

	public double sampleVariance() {
		return this.count() > 1 ? this.dsquared / (this.count() - 1) : 0;
	}

	public double sampleStdDev() {
		return Math.sqrt(this.sampleVariance());
	}

	public double marginError95() {
		return 1.96 * (this.sampleStdDev() / Math.sqrt(this.count()));
	}

	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Count:              " + count());
		b.append('\n');
		b.append("Mean:               " + mean());
		b.append('\n');
		b.append("Variance (Sample):  " + sampleVariance());
		b.append('\n');
		b.append("Variance (Pop.):    " + populationVariance());
		b.append('\n');
		b.append("Std.Dev (Sample):   " + sampleStdDev());
		b.append('\n');
		b.append("Std.Dev (Pop.):     " + populationStdDev());
		b.append('\n');
		b.append("Error (95%):        " + marginError95());
		b.append('\n');
		return b.toString();
	}

	private class CircularBuffer {

		private final int size;
		private final double[] buffer;

		private int index = 0;
		private int count = 0;

		public CircularBuffer(int size) {
			this.size = size;
			this.buffer = new double[size];
		}

		public double append(double value) {
			double poppedValue = 0;
			if (count == size) {
				poppedValue = this.buffer[this.index];
			} else
				count++;

			this.buffer[this.index] = value;
			this.index = (this.index + 1) % this.size;

			return poppedValue;
		}

		public int length() {
			return this.count;
		}
	}

	public static void main(String[] args) {
		double val[] = { 1000000, 22.2, 33.3, 44.4, 55.5, 66.6, 77.7, 88.8, 0.0, 100.1 };
		SlidingWindowStats stats = new SlidingWindowStats(5);
		for (int i = 0; i < val.length; i++) {
			stats.update(val[i]);
			System.out.println(stats + "\n");
		}
	}
}
