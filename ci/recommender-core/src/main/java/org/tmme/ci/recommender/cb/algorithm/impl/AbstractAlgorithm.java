package org.tmme.ci.recommender.cb.algorithm.impl;

import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.mahout.common.ClassUtils;
import org.apache.mahout.common.distance.DistanceMeasure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmme.ci.recommender.cb.algorithm.Algorithm;

public abstract class AbstractAlgorithm implements Algorithm {

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractAlgorithm.class);

	private final Map<String, String> args;
	private final String executable;
	private final String dm;
	private final DistanceMeasure distanceMeasure;

	public AbstractAlgorithm(final String executable,
			final Map<String, String> args) {
		Validate.notBlank(executable);
		Validate.notNull(args);
		this.executable = executable;
		this.args = args;
		final String dm = args.get("dm");
		Validate.notEmpty(dm);
		this.dm = dm;
		this.distanceMeasure = ClassUtils.instantiateAs(dm,
				DistanceMeasure.class);
		parseArgs();
	}

	@Override
	public void compute(final String inputDir, final String outputDir)
			throws Exception {
		final String cmd = buildCmd(inputDir, outputDir);
		LOG.info("Executing the command {}", cmd);
		Runtime.getRuntime().exec(cmd).waitFor();
	}

	protected abstract String buildCmd(String inputDir, String outputDir);

	protected abstract void parseArgs();

	@Override
	public DistanceMeasure getDistanceMeasure() {
		return distanceMeasure;
	}

	protected String getDistanceMeasureAsString() {
		return dm;
	}

	protected String getExecutable() {
		return executable;
	}

	protected Map<String, String> getArgs() {
		return args;
	}

}
