package org.tmme.ci.recommender.cb.algorithm.impl;

import java.util.Map;

import org.apache.commons.lang3.Validate;

public class FuzzyKMeans extends AbstractAlgorithm {

	private String k;
	private String x;
	private String m;

	public FuzzyKMeans(final String executable, final Map<String, String> args) {
		super(executable, args);
	}

	@Override
	protected String buildCmd(final String inputDir, final String outputDir) {
		return new StringBuilder(getExecutable()).append(" fkmeans -c ")
				.append(outputDir).append("/random-seeds -i ").append(inputDir)
				.append(" -o ").append(outputDir).append(" -dm ")
				.append(getDistanceMeasureAsString()).append(" -k ").append(k)
				.append(" -x ").append(x).append(" -m ").append(m)
				.append(" -ow -cl -e").toString();
	}

	@Override
	protected void parseArgs() {
		final String k = getArgs().get("k");
		Validate.notBlank(k);
		this.k = k;
		final String x = getArgs().get("x");
		Validate.notBlank(x);
		this.x = x;
		final String m = getArgs().get("m");
		Validate.notBlank(m);
		this.m = m;

	}

	@Override
	public String toString() {
		return "fkmeans";
	}
}
