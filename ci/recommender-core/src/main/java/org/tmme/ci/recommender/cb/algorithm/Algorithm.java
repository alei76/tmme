package org.tmme.ci.recommender.cb.algorithm;

import org.apache.mahout.common.distance.DistanceMeasure;

public interface Algorithm {

	void compute(String inputDir, String outputDir) throws Exception;

	DistanceMeasure getDistanceMeasure();

}
