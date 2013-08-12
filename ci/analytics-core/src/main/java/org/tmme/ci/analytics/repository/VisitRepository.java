package org.tmme.ci.analytics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.analytics.models.Visit;

public interface VisitRepository extends MongoRepository<Visit, String> {

}
