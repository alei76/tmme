package org.tmme.ci.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.model.SocialConnection;

public interface SocialUsersConnectionRepository extends
		MongoRepository<SocialConnection, String> {

}
