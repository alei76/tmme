package org.tmme.ci.recommender.cb.algorithm.impl;

import java.util.Map;

import org.apache.commons.lang3.Validate;

public class Dirichlet extends AbstractAlgorithm {

	private String a0;
	private String md;
	private String mp;
	private String k;
	private String x;

	public Dirichlet(final String executable, final Map<String, String> args) {
		super(executable, args);
	}

	@Override
	protected String buildCmd(final String inputDir, final String outputDir) {
		return new StringBuilder(getExecutable()).append(" dirichlet -i ")
				.append(inputDir).append(" -o ").append(outputDir)
				.append(" -a0 ").append(a0).append(" -md ").append(md)
				.append(" -mp ").append(mp).append(" -dm ")
				.append(getDistanceMeasureAsString()).append(" -k ").append(k)
				.append(" -x ").append(x).append(" -ow -cl -e").toString();
	}

	@Override
	protected void parseArgs() {
		final String a0 = getArgs().get("a0");
		Validate.notBlank(a0);
		this.a0 = a0;
		final String md = getArgs().get("md");
		Validate.notBlank(md);
		this.md = md;
		final String mp = getArgs().get("mp");
		Validate.notBlank(mp);
		this.mp = mp;
		final String k = getArgs().get("k");
		Validate.notBlank(k);
		this.k = k;
		final String x = getArgs().get("x");
		Validate.notBlank(x);
		this.x = x;

	}

	@Override
	public String toString() {
		return "dirichlet";
	}

}
