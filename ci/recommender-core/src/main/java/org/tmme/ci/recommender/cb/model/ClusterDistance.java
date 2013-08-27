package org.tmme.ci.recommender.cb.model;

public class ClusterDistance {

	private double max;
	private double min;
	private double avg;

	public ClusterDistance(final double max, final double min, final double avg) {
		this.max = max;
		this.min = min;
		this.avg = avg;
	}

	public double getMax() {
		return max;
	}

	public void setMax(final double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(final double min) {
		this.min = min;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(final double avg) {
		this.avg = avg;
	}

	@Override
	public String toString() {
		return "{max: " + max + ", min: " + min + ", avg: " + avg + "}";
	}

}
