package org.tmme.ci.analytics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.model.RejectRecommendation;

public interface RejectRecommendationRepository extends
		MongoRepository<RejectRecommendation, String> {

}
