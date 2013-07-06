package org.tmme.ci.repositories.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang3.Validate;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.tmme.ci.model.SocialConnection;
import org.tmme.ci.repositories.SocialConnectionRepository;

@Repository
public class ConnectionRepositoryImpl implements ConnectionRepository {

	private final String userId;
	private final TextEncryptor textEncryptor;
	private final SocialConnectionRepository helper;
	private final ConnectionFactoryLocator connectionFactoryLocator;

	private final ConnectionMapper connectionMapper;

	public ConnectionRepositoryImpl(final String userId,
			final TextEncryptor textEncryptor,
			final SocialConnectionRepository helper,
			final ConnectionFactoryLocator connectionFactoryLocator) {
		Validate.notBlank(userId);
		Validate.notNull(textEncryptor);
		Validate.notNull(helper);
		Validate.notNull(connectionFactoryLocator);
		this.userId = userId;
		this.textEncryptor = textEncryptor;
		this.helper = helper;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.connectionMapper = new ConnectionMapper();
	}

	@Override
	public MultiValueMap<String, Connection<?>> findAllConnections() {
		final MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();
		final Set<String> registeredProviderIds = connectionFactoryLocator
				.registeredProviderIds();
		for (final String registeredProviderId : registeredProviderIds) {
			connections.put(registeredProviderId,
					Collections.<Connection<?>> emptyList());
		}
		final List<SocialConnection> userConnections = helper
				.findByUserId(userId);
		final List<Connection<?>> connectionList = connectionMapper
				.mapConnections(userConnections);
		for (final Connection<?> connection : connectionList) {
			final String providerId = connection.getKey().getProviderId();
			if (connections.get(providerId).size() == 0) {
				connections.put(providerId, new LinkedList<Connection<?>>());
			}
			connections.add(providerId, connection);
		}
		return connections;
	}

	@Override
	public List<Connection<?>> findConnections(final String providerId) {
		final List<SocialConnection> connections = helper
				.findByUserIdAndProviderId(userId, providerId);
		return connectionMapper.mapConnections(connections);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> List<Connection<A>> findConnections(final Class<A> apiType) {
		final List<?> connections = findConnections(getProviderId(apiType));
		return (List<Connection<A>>) connections;
	}

	@Override
	public MultiValueMap<String, Connection<?>> findConnectionsToUsers(
			final MultiValueMap<String, String> providerUserIds) {
		throw new NotImplementedException(
				"findConnectionsToUsers not implemented");
	}

	@Override
	public Connection<?> getConnection(final ConnectionKey connectionKey) {
		final SocialConnection connection = helper
				.findByUserIdAndProviderIdAndProviderUserId(userId,
						connectionKey.getProviderId(),
						connectionKey.getProviderUserId());
		return connectionMapper.mapConnection(connection);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> Connection<A> getConnection(final Class<A> apiType,
			final String providerUserId) {
		final String providerId = getProviderId(apiType);
		return (Connection<A>) getConnection(new ConnectionKey(providerId,
				providerUserId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> Connection<A> getPrimaryConnection(final Class<A> apiType) {
		final String providerId = getProviderId(apiType);
		final Connection<A> connection = (Connection<A>) findPrimaryConnection(providerId);
		if (connection == null) {
			throw new NotConnectedException(providerId);
		}
		return connection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> Connection<A> findPrimaryConnection(final Class<A> apiType) {
		return (Connection<A>) findPrimaryConnection(getProviderId(apiType));
	}

	private Connection<?> findPrimaryConnection(final String providerId) {
		final List<SocialConnection> connections = helper
				.findByUserIdAndProviderId(userId, providerId);
		if (connections != null && !connections.isEmpty()) {
			return connectionMapper.mapConnection(connections.get(0));
		}
		return null;
	}

	@Override
	public void addConnection(final Connection<?> connection) {
		final ConnectionData data = connection.createData();
		final SocialConnection social = new SocialConnection();
		social.setProviderId(data.getProviderId());
		social.setProviderUserId(data.getProviderUserId());
		social.setDisplayName(data.getDisplayName());
		social.setProfileUrl(data.getProfileUrl());
		social.setImageUrl(data.getImageUrl());
		social.setAccessToken(encrypt(data.getAccessToken()));
		social.setSecret(encrypt(data.getSecret()));
		social.setRefreshToken(encrypt(data.getRefreshToken()));
		social.setExpireTime(data.getExpireTime());
		helper.save(social);
	}

	@Override
	public void updateConnection(final Connection<?> connection) {
		final ConnectionData data = connection.createData();
		final SocialConnection social = helper
				.findByUserIdAndProviderIdAndProviderUserId(userId,
						data.getProviderId(), data.getProviderUserId());
		social.setDisplayName(data.getDisplayName());
		social.setProfileUrl(data.getProfileUrl());
		social.setImageUrl(data.getImageUrl());
		social.setAccessToken(encrypt(data.getAccessToken()));
		social.setSecret(encrypt(data.getSecret()));
		social.setRefreshToken(encrypt(data.getRefreshToken()));
		social.setExpireTime(data.getExpireTime());
		helper.save(social);
	}

	@Override
	public void removeConnections(final String providerId) {
		final List<SocialConnection> connections = helper
				.findByUserIdAndProviderId(userId, providerId);
		helper.delete(connections);
	}

	@Override
	public void removeConnection(final ConnectionKey connectionKey) {
		final SocialConnection connection = helper
				.findByUserIdAndProviderIdAndProviderUserId(userId,
						connectionKey.getProviderId(),
						connectionKey.getProviderUserId());
		helper.delete(connection);
	}

	private final class ConnectionMapper {

		private List<Connection<?>> mapConnections(
				final List<SocialConnection> socialConnections) {
			final List<Connection<?>> connections = new ArrayList<Connection<?>>();
			if (socialConnections != null) {
				for (final SocialConnection socialConnection : socialConnections) {
					final Connection<?> connection = mapConnection(socialConnection);
					connections.add(connection);
				}
			}
			return connections;
		}

		private Connection<?> mapConnection(
				final SocialConnection socialConnection) {
			if (socialConnection == null) {
				return null;
			}
			final ConnectionData connectionData = mapConnectionData(socialConnection);
			final ConnectionFactory<?> connectionFactory = connectionFactoryLocator
					.getConnectionFactory(connectionData.getProviderId());
			return connectionFactory.createConnection(connectionData);
		}

		private ConnectionData mapConnectionData(
				final SocialConnection socialConnection) {
			return new ConnectionData(socialConnection.getProviderId(),
					socialConnection.getProviderUserId(),
					socialConnection.getDisplayName(),
					socialConnection.getProfileUrl(),
					socialConnection.getImageUrl(),
					decrypt(socialConnection.getAccessToken()),
					decrypt(socialConnection.getSecret()),
					decrypt(socialConnection.getRefreshToken()),
					expireTime(socialConnection.getExpireTime()));
		}

		private String decrypt(final String encryptedText) {
			return encryptedText != null ? textEncryptor.decrypt(encryptedText)
					: encryptedText;
		}

		private Long expireTime(final Long expireTime) {
			return expireTime == null || expireTime == 0 ? null : expireTime;
		}

	}

	private String encrypt(final String text) {
		return text != null ? textEncryptor.encrypt(text) : text;
	}

	private <A> String getProviderId(final Class<A> apiType) {
		return connectionFactoryLocator.getConnectionFactory(apiType)
				.getProviderId();
	}

}
