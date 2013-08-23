package org.tmme.ci.recommender.cb.algorithm.impl;

import java.util.Map;

import org.apache.commons.lang.Validate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.clustering.kmeans.KMeansDriver;

public class KMeans extends AbstractAlgorithm {

	private String distanceMeasure;
	private String k;
	private String x;

	public KMeans(final Configuration conf, final Map<String, String> args) {
		super(conf);
		parseArgs(args);
	}

	@Override
	public void compute(final String inputDir, final String outputDir)
			throws Exception {
		final KMeansDriver kmeans = new KMeansDriver();
		kmeans.setConf(getConfig());
		ToolRunner.run(kmeans, new String[] { "-c",
				outputDir + "/random-seeds", "-i", inputDir, "-o", outputDir,
				"-dm", distanceMeasure, "-k", k, "-x", x, "-ow", "-cl" });
	}

	private void parseArgs(final Map<String, String> args) {
		Validate.notEmpty(args);
		final String dm = args.get("dm");
		Validate.notNull(dm);
		this.distanceMeasure = dm;
		final String k = args.get("k");
		Validate.notNull(k);
		this.k = k;
		final String x = args.get("x");
		Validate.notNull(x);
		this.x = x;

	}

	@Override
	public String toString() {
		return "kmeans";
	}

}
