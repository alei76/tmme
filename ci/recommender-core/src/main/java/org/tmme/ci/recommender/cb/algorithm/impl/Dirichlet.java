package org.tmme.ci.recommender.cb.algorithm.impl;

import java.util.Map;

import org.apache.commons.lang.Validate;

public class Dirichlet extends AbstractAlgorithm {

	private String distanceMeasure;
	private String a0;
	private String md;
	private String mp;
	private String k;
	private String x;

	private final String executable;

	public Dirichlet(final String executable, final Map<String, String> args) {
		Validate.notEmpty(executable);
		this.executable = executable;
		parseArgs(args);
	}

	@Override
	protected String buildCmd(final String inputDir, final String outputDir) {
		return new StringBuilder(executable).append(" dirichlet -i ")
				.append(inputDir).append(" -o ").append(outputDir)
				.append(" -a0 ").append(a0).append(" -md ").append(md)
				.append(" -mp ").append(mp).append(" -dm ")
				.append(distanceMeasure).append(" -k ").append(k)
				.append(" -x ").append(x).append(" -ow -cl -e").toString();
	}

	private void parseArgs(final Map<String, String> args) {
		Validate.notEmpty(args);
		final String a0 = args.get("a0");
		Validate.notEmpty(a0);
		this.a0 = a0;
		final String md = args.get("md");
		Validate.notEmpty(md);
		this.md = md;
		final String mp = args.get("mp");
		Validate.notEmpty(mp);
		this.mp = mp;
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
		return "dirichlet";
	}

}
