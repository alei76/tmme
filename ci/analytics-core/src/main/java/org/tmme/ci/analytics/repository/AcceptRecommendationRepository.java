package org.tmme.ci.analytics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.analytics.models.AcceptRecommendation;

public interface AcceptRecommendationRepository extends
		MongoRepository<AcceptRecommendation, String> {

}
