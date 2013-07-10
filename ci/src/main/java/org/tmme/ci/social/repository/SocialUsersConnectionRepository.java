package org.tmme.ci.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.model.SocialConnection;

public interface SocialUsersConnectionRepository extends
		MongoRepository<SocialConnection, String> {

}
