package org.tmme.ci.social.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.social.models.SocialConnection;

public interface SocialConnectionRepository extends
		MongoRepository<SocialConnection, String> {

	SocialConnection findByUsernameAndProviderIdAndProviderUserId(
			String username, String providerId, String providerUserId);

	List<SocialConnection> findByUsernameAndProviderId(String username,
			String providerId);

	List<SocialConnection> findByUsername(String username);

	List<SocialConnection> findByProviderIdAndProviderUserId(String providerId,
			String providerUserId);

}
