package org.tmme.ci.analytics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.analytics.models.Purchase;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {

}
