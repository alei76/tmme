package org.tmme.ci.repositories.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Repository;
import org.tmme.ci.model.SocialConnection;
import org.tmme.ci.repositories.SocialConnectionRepository;

@Repository
public class UsersConnectionRepositoryImpl implements UsersConnectionRepository {

	@Autowired
	private TextEncryptor textEncryptor;

	@Autowired
	private SocialConnectionRepository helper;

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<String> findUserIdsWithConnection(final Connection<?> connection) {
		final ConnectionKey key = connection.getKey();
		final List<SocialConnection> socialConnections = helper
				.findByProviderIdAndProviderUserId(key.getProviderId(),
						key.getProviderUserId());
		final List<String> userIds = new ArrayList<String>();
		if (socialConnections != null && !socialConnections.isEmpty()) {
			for (final SocialConnection socialConnection : socialConnections) {
				userIds.add(socialConnection.getUser().getId());
			}
		}
		return userIds;
	}

	@Override
	public Set<String> findUserIdsConnectedTo(final String providerId,
			final Set<String> providerUserIds) {
		final Query query = new Query(Criteria.where("providerId")
				.is(providerId).and("providerUserId").in(providerUserIds));
		final List<SocialConnection> socialConnections = mongoTemplate.find(
				query, SocialConnection.class);
		final Set<String> userIds = new HashSet<String>();
		if (socialConnections != null && !socialConnections.isEmpty()) {
			for (final SocialConnection socialConnection : socialConnections) {
				userIds.add(socialConnection.getUser().getId());
			}
		}
		return userIds;
	}

	@Override
	public ConnectionRepository createConnectionRepository(final String userId) {
		return new ConnectionRepositoryImpl(userId, textEncryptor, helper,
				connectionFactoryLocator);
	}

}
