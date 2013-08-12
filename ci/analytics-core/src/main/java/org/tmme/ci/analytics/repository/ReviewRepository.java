package org.tmme.ci.analytics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.analytics.models.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {

}
