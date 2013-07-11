package org.tmme.ci.analytics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.model.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {

}
