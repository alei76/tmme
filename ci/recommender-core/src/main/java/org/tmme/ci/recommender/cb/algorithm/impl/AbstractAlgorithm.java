package org.tmme.ci.recommender.cb.algorithm.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmme.ci.recommender.cb.algorithm.Algorithm;

public abstract class AbstractAlgorithm implements Algorithm {

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractAlgorithm.class);

	@Override
	public void compute(final String inputDir, final String outputDir)
			throws Exception {
		final String cmd = buildCmd(inputDir, outputDir);
		LOG.info("Executing the command {}", cmd);
		Runtime.getRuntime().exec(cmd).waitFor();
	}

	protected abstract String buildCmd(String inputDir, String outputDir);

}
