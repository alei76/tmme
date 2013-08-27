package org.tmme.ci.recommender.cb.algorithm.impl;

import java.util.Map;

import org.apache.commons.lang3.Validate;

public class Canopy extends AbstractAlgorithm {

	private String t1;
	private String t2;

	public Canopy(final String executable, final Map<String, String> args) {
		super(executable, args);
	}

	@Override
	protected String buildCmd(final String inputDir, final String outputDir) {
		return new StringBuilder(getExecutable()).append(" canopy -i ")
				.append(inputDir).append(" -o ").append(outputDir)
				.append(" -dm ").append(getDistanceMeasureAsString())
				.append(" -t1 ").append(t1).append(" -t2 ").append(t2)
				.append(" -ow -cl").toString();
	}

	@Override
	protected void parseArgs() {
		final String t1 = getArgs().get("t1");
		Validate.notBlank(t1);
		this.t1 = t1;
		final String t2 = getArgs().get("t2");
		Validate.notBlank(t2);
		this.t2 = t2;
	}

	@Override
	public String toString() {
		return "canopy";
	}

}
