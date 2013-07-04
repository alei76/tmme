package org.tmme.ci.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.model.SocialConnection;

public interface SocialConnectionRepository extends
		MongoRepository<SocialConnection, String> {

	SocialConnection findByUserIdAndProviderIdAndProviderUserId(String userId,
			String providerId, String providerUserId);

	List<SocialConnection> findByUserIdAndProviderId(String userId,
			String providerId);

	List<SocialConnection> findByUserId(String userId);

	List<SocialConnection> findByProviderIdAndProviderUserId(String providerId,
			String providerUserId);

}
