package org.tmme.ci.recommender.cb.factory;

import java.util.Map;

import org.tmme.ci.recommender.cb.algorithm.Algorithm;

public interface AlgorithmFactory {

	Algorithm createAlgorithm(final AlgorithmName algorithmName,
			final Map<String, String> args);
}
