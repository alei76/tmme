package org.tmme.ci.recommender.cb.factory;

import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.tmme.ci.recommender.cb.algorithm.Algorithm;
import org.tmme.ci.recommender.cb.algorithm.impl.Canopy;
import org.tmme.ci.recommender.cb.algorithm.impl.KMeans;

public enum AlgorithmName {

	KMEANS {
		@Override
		public Algorithm createAlgorithm(final Configuration configuration,
				final Map<String, String> args) {
			return new KMeans(configuration, args);
		}
	},
	CANOPY {
		@Override
		public Algorithm createAlgorithm(final Configuration configuration,
				final Map<String, String> args) {
			return new Canopy(configuration, args);
		}
	};

	public abstract Algorithm createAlgorithm(Configuration configuration,
			Map<String, String> args);

}
