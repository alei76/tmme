package org.tmme.ci.recommender.cb.factory.impl;

import java.util.Map;

import org.apache.commons.lang.Validate;
import org.tmme.ci.recommender.cb.algorithm.Algorithm;
import org.tmme.ci.recommender.cb.factory.AlgorithmFactory;
import org.tmme.ci.recommender.cb.factory.AlgorithmName;

public class AlgorithmFactoryImpl implements AlgorithmFactory {

	private final String executable;

	public AlgorithmFactoryImpl(final String executable) {
		Validate.notEmpty(executable);
		this.executable = executable;
	}

	@Override
	public Algorithm createAlgorithm(final AlgorithmName algorithmName,
			final Map<String, String> args) {
		return algorithmName.createAlgorithm(executable, args);
	}

}
