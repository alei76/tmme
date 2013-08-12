package org.tmme.ci.social.service;

import org.springframework.ui.Model;
import org.springframework.web.context.request.NativeWebRequest;

public interface ConnectorService {

	String connect(String providerId, NativeWebRequest request);

	void removeConnections(String providerId, NativeWebRequest request);

	void oauth2Callback(String providerId, NativeWebRequest request);

	void oauth1Callback(String providerId, NativeWebRequest request);

	void connectionStatus(NativeWebRequest request, Model model);

}
