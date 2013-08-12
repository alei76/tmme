package org.tmme.ci.social.repository.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.tmme.ci.social.models.SocialConnection;
import org.tmme.ci.social.repository.SocialConnectionRepository;

public class UsersConnectionRepositoryImpl implements UsersConnectionRepository {

	private final TextEncryptor textEncryptor;
	private final SocialConnectionRepository helper;
	private final ConnectionFactoryLocator connectionFactoryLocator;
	private final MongoTemplate mongoTemplate;

	public UsersConnectionRepositoryImpl(final TextEncryptor textEncryptor,
			final SocialConnectionRepository helper,
			final ConnectionFactoryLocator connectionFactoryLocator,
			final MongoTemplate mongoTemplate) {
		Validate.notNull(textEncryptor);
		Validate.notNull(helper);
		Validate.notNull(connectionFactoryLocator);
		Validate.notNull(mongoTemplate);
		this.textEncryptor = textEncryptor;
		this.helper = helper;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<String> findUserIdsWithConnection(final Connection<?> connection) {
		final ConnectionKey key = connection.getKey();
		final List<SocialConnection> socialConnections = helper
				.findByProviderIdAndProviderUserId(key.getProviderId(),
						key.getProviderUserId());
		final List<String> userIds = new ArrayList<String>();
		if (socialConnections != null && !socialConnections.isEmpty()) {
			for (final SocialConnection socialConnection : socialConnections) {
				userIds.add(socialConnection.getUsername());
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
				userIds.add(socialConnection.getUsername());
			}
		}
		return userIds;
	}

	@Override
	public ConnectionRepository createConnectionRepository(final String userName) {
		return new ConnectionRepositoryImpl(userName, textEncryptor, helper,
				connectionFactoryLocator);
	}

}
