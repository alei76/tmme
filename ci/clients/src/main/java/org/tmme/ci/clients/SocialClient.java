package org.tmme.ci.clients;

import java.util.List;
import java.util.Map;

public interface SocialClient {

	String connect(String userId, String providerId, String referer);

	void removeConnections(String userId, String providerId);

	void oauth2Callback(String userId, String providerId, String code,
			String referer);

	void oauth1Callback(String userId, String providerId, String oauthToken,
			String referer);

	List<String> likes(String userId, String providerId);

	List<String> checkins(String userId, String providerId);

	Map<String, Boolean> connectionStatus(String userId);

}
