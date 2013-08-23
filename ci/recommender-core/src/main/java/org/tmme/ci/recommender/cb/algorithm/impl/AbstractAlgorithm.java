package org.tmme.ci.recommender.cb.algorithm.impl;

import org.mortbay.log.Log;
import org.tmme.ci.recommender.cb.algorithm.Algorithm;

public abstract class AbstractAlgorithm implements Algorithm {

	@Override
	public void compute(final String inputDir, final String outputDir)
			throws Exception {
		final String cmd = buildCmd(inputDir, outputDir);
		Log.info("Executing the command {}", cmd);
		Runtime.getRuntime().exec(cmd).waitFor();
	}

	protected abstract String buildCmd(String inputDir, String outputDir);

}
