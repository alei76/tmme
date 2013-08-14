package org.tmme.ci.social.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.tmme.ci.social.service.ConnectorService;

public class ConnectorServiceImpl implements ConnectorService {

	private static final Logger LOG = Logger
			.getLogger(ConnectorServiceImpl.class);

	private final ConnectController connectController;

	public ConnectorServiceImpl(final ConnectController connectController) {
		Validate.notNull(connectController);
		this.connectController = connectController;
	}

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
	public Map<String, Boolean> connectionStatus(final NativeWebRequest request) {
		final Model model = new ExtendedModelMap();
		connectController.connectionStatus(request, model);
		return asMap(model);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Boolean> asMap(final Model model) {
		final Map<String, Object> map = model.asMap();
		final Map<String, List<Connection<?>>> connectionMap = (Map<String, List<Connection<?>>>) map
				.get("connectionMap");
		final Map<String, Boolean> result = new HashMap<>();
		final Set<String> keys = connectionMap.keySet();
		for (final String key : keys) {
			final List<Connection<?>> connections = connectionMap.get(key);
			result.put(key, !connections.isEmpty());
		}
		return result;
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
