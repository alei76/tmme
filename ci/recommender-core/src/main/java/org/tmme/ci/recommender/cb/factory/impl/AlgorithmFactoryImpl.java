package org.tmme.ci.recommender.cb.factory.impl;

import java.util.Map;

import org.apache.commons.lang.Validate;
import org.apache.hadoop.conf.Configuration;
import org.tmme.ci.recommender.cb.algorithm.Algorithm;
import org.tmme.ci.recommender.cb.factory.AlgorithmFactory;
import org.tmme.ci.recommender.cb.factory.AlgorithmName;

public class AlgorithmFactoryImpl implements AlgorithmFactory {

	private final Configuration config;

	public AlgorithmFactoryImpl(final Configuration config) {
		Validate.notNull(config);
		this.config = config;
	}

	@Override
	public Algorithm createAlgorithm(final AlgorithmName algorithmName,
			final Map<String, String> args) {
		return algorithmName.createAlgorithm(config, args);
	}

}
