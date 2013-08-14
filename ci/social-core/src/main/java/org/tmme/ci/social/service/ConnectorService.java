package org.tmme.ci.social.service;

import java.util.Map;

import org.springframework.web.context.request.NativeWebRequest;

public interface ConnectorService {

	String connect(String providerId, NativeWebRequest request);

	void removeConnections(String providerId, NativeWebRequest request);

	void oauth2Callback(String providerId, NativeWebRequest request);

	void oauth1Callback(String providerId, NativeWebRequest request);

	Map<String, Boolean> connectionStatus(NativeWebRequest request);

}
