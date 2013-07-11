package org.tmme.ci.analytics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.model.Visit;

public interface VisitRepository extends MongoRepository<Visit, String> {

}
