package org.tmme.ci.clients.impl;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.tmme.ci.clients.AnalyticsClient;
import org.tmme.ci.common.utils.RestClient;

public class AnalyticsClientImpl implements AnalyticsClient {

	private final String analyticsUrl;
	private final RestClient restClient;

	public AnalyticsClientImpl(final RestClient restClient,
			final String analyticsUrl) {
		Validate.notNull(restClient);
		Validate.notBlank(analyticsUrl);
		this.restClient = restClient;
		this.analyticsUrl = analyticsUrl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRejectedRecommendations(final String userId) {
		return restClient.exchange(analyticsUrl + "/rejects/user/" + userId,
				new HttpEntity<Object>(buildHeaders()), HttpMethod.GET,
				List.class);

	}

	private HttpHeaders buildHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
