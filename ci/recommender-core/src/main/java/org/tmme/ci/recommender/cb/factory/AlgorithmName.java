package org.tmme.ci.recommender.cb.factory;

import java.util.Map;

import org.tmme.ci.recommender.cb.algorithm.Algorithm;
import org.tmme.ci.recommender.cb.algorithm.impl.Canopy;
import org.tmme.ci.recommender.cb.algorithm.impl.Dirichlet;
import org.tmme.ci.recommender.cb.algorithm.impl.FuzzyKMeans;
import org.tmme.ci.recommender.cb.algorithm.impl.KMeans;

public enum AlgorithmName {

	KMEANS {
		@Override
		public Algorithm createAlgorithm(final String executable,
				final Map<String, String> args) {
			return new KMeans(executable, args);
		}
	},
	FKMEANS {
		@Override
		public Algorithm createAlgorithm(final String executable,
				final Map<String, String> args) {
			return new FuzzyKMeans(executable, args);
		}
	},
	DIRICHLET {
		@Override
		public Algorithm createAlgorithm(final String executable,
				final Map<String, String> args) {
			return new Dirichlet(executable, args);
		}
	},
	CANOPY {
		@Override
		public Algorithm createAlgorithm(final String executable,
				final Map<String, String> args) {
			return new Canopy(executable, args);
		}
	};

	public abstract Algorithm createAlgorithm(String executable,
			Map<String, String> args);

}
