package org.tmme.ci.analytics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.analytics.models.RejectRecommendation;

public interface RejectRecommendationRepository extends
		MongoRepository<RejectRecommendation, String> {

}
