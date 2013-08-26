package org.tmme.ci.recommender.cb.algorithm.impl;

import java.util.Map;

import org.apache.commons.lang.Validate;

public class Canopy extends AbstractAlgorithm {

	private String distanceMeasure;
	private String t1;
	private String t2;

	private final String executable;

	public Canopy(final String executable, final Map<String, String> args) {
		Validate.notEmpty(executable);
		this.executable = executable;
		parseArgs(args);
	}

	@Override
	protected String buildCmd(final String inputDir, final String outputDir) {
		return new StringBuilder(executable).append(" canopy -i ")
				.append(inputDir).append(" -o ").append(outputDir)
				.append(" -dm ").append(distanceMeasure).append(" -t1 ")
				.append(t1).append(" -t2 ").append(t2).append(" -ow -cl")
				.toString();
	}

	private void parseArgs(final Map<String, String> args) {
		Validate.notEmpty(args);
		final String dm = args.get("dm");
		Validate.notEmpty(dm);
		this.distanceMeasure = dm;
		final String t1 = args.get("t1");
		Validate.notEmpty(t1);
		this.t1 = t1;
		final String t2 = args.get("t2");
		Validate.notEmpty(t2);
		this.t2 = t2;

	}

	@Override
	public String toString() {
		return "canopy";
	}

}
