package org.tmme.ci.recommender.cb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.recommender.cb.model.ClusterConfig;

public interface ClusterConfigRepository extends
		MongoRepository<ClusterConfig, String> {

}
