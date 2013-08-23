package org.tmme.ci.recommender.cb.algorithm.impl;

import java.util.Map;

import org.apache.commons.lang.Validate;

public class KMeans extends AbstractAlgorithm {

	private String distanceMeasure;
	private String k;
	private String x;

	private final String executable;

	public KMeans(final String executable, final Map<String, String> args) {
		Validate.notEmpty(executable);
		this.executable = executable;
		parseArgs(args);
	}

	@Override
	protected String buildCmd(final String inputDir, final String outputDir) {
		return new StringBuilder(executable).append(" kmeans -c ")
				.append(outputDir).append("/random-seeds -i ").append(inputDir)
				.append(" -o ").append(outputDir).append(" -dm ")
				.append(distanceMeasure).append(" -k ").append(k)
				.append(" -x ").append(x).append(" -ow -cl").toString();
	}

	private void parseArgs(final Map<String, String> args) {
		Validate.notEmpty(args);
		final String dm = args.get("dm");
		Validate.notEmpty(dm);
		this.distanceMeasure = dm;
		final String k = args.get("k");
		Validate.notEmpty(k);
		this.k = k;
		final String x = args.get("x");
		Validate.notEmpty(x);
		this.x = x;

	}

	@Override
	public String toString() {
		return "kmeans";
	}

}
