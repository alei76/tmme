package org.tmme.ci.recommender.cb.algorithm.impl;

import org.apache.commons.lang.Validate;
import org.apache.hadoop.conf.Configuration;
import org.tmme.ci.recommender.cb.algorithm.Algorithm;

public abstract class AbstractAlgorithm implements Algorithm {

	private final Configuration configuration;

	public AbstractAlgorithm(final Configuration configuration) {
		Validate.notNull(configuration);
		this.configuration = configuration;
	}

	protected Configuration getConfig() {
		return configuration;
	}

}
