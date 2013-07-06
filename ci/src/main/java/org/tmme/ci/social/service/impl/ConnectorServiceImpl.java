package org.tmme.ci.social.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.tmme.ci.social.service.ConnectorService;

@Service
public class ConnectorServiceImpl implements ConnectorService {

	private static final Logger LOG = Logger
			.getLogger(ConnectorServiceImpl.class);

	@Autowired
	private ConnectController connectController;

	@Override
	public String connect(final String providerId,
			final NativeWebRequest request) {
		final String url = connectController.connect(providerId, request)
				.getUrl();
		checkForError(request);
		return url;
	}

	@Override
	public void removeConnections(final String providerId,
			final NativeWebRequest request) {
		connectController.removeConnections(providerId, request);
	}

	@Override
	public void oauth2Callback(final String providerId,
			final NativeWebRequest request) {
		connectController.oauth2Callback(providerId, request);
		checkForError(request);
	}

	@Override
	public void oauth1Callback(final String providerId,
			final NativeWebRequest request) {
		connectController.oauth1Callback(providerId, request);
		checkForError(request);
	}

	@Override
	public void connectionStatus(final NativeWebRequest request,
			final Model model) {
		connectController.connectionStatus(request, model);
	}

	private void checkForError(final NativeWebRequest request) {
		final Exception errorAttr = (Exception) request.getAttribute(
				"social.provider.error", RequestAttributes.SCOPE_SESSION);
		if (errorAttr != null) {
			LOG.error("Error connecting to social network", errorAttr);
			throw new RuntimeException(errorAttr.getMessage());
		}

	}

}
