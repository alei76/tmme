package org.tmme.ci.recommender.cb.algorithm.impl;

import java.util.Map;

import org.apache.commons.lang.Validate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.clustering.canopy.CanopyDriver;

public class Canopy extends AbstractAlgorithm {

	private String distanceMeasure;
	private String t1;
	private String t2;

	public Canopy(final Configuration conf, final Map<String, String> args) {
		super(conf);
		parseArgs(args);
	}

	@Override
	public void compute(final String inputDir, final String outputDir)
			throws Exception {
		final CanopyDriver canopy = new CanopyDriver();
		canopy.setConf(getConfig());
		ToolRunner.run(canopy, new String[] { "-i", inputDir, "-o", outputDir,
				"-dm", distanceMeasure, "-t1", t1, "-t2", t2, "-cl", "-ow",
				"-xm", "mapreduce" });
	}

	private void parseArgs(final Map<String, String> args) {
		Validate.notEmpty(args);
		final String dm = args.get("dm");
		Validate.notNull(dm);
		this.distanceMeasure = dm;
		final String t1 = args.get("t1");
		Validate.notNull(t1);
		this.t1 = t1;
		final String t2 = args.get("t2");
		Validate.notNull(t2);
		this.t2 = t2;

	}

	@Override
	public String toString() {
		return "canopy";
	}
}
