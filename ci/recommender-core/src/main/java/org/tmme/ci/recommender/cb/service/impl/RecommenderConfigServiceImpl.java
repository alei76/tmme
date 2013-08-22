package org.tmme.ci.recommender.cb.service.impl;

import org.apache.commons.lang.Validate;
import org.tmme.ci.recommender.cb.model.ClusterConfig;
import org.tmme.ci.recommender.cb.repository.ClusterConfigRepository;
import org.tmme.ci.recommender.cb.service.RecommenderConfigService;

public class RecommenderConfigServiceImpl implements RecommenderConfigService {

	private final ClusterConfigRepository repository;

	public RecommenderConfigServiceImpl(final ClusterConfigRepository repository) {
		Validate.notNull(repository);
		this.repository = repository;
	}

	@Override
	public void addConfig(final ClusterConfig criteria) {
		repository.save(criteria);
	}

}
