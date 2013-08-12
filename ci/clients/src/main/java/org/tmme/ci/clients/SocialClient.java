package org.tmme.ci.clients;

public interface SocialClient {

	String connect(String userId, String providerId, String referer);

	void removeConnections(String userId, String providerId);

	void oauth2Callback(String userId, String providerId, String code,
			String referer);

	void oauth1Callback(String userId, String providerId, String oauthToken,
			String referer);

}
