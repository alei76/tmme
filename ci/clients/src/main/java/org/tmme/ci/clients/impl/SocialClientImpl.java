package org.tmme.ci.clients.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.tmme.ci.clients.SocialClient;
import org.tmme.ci.common.utils.RestClient;

public class SocialClientImpl implements SocialClient {

	private final String socialUrl;
	private final RestClient restClient;

	private static final String USER_PARAM = "?user={userid}";
	private static final String STATUS_PATH = "/conn/status" + USER_PARAM;
	private static final String OPERATION_PATH = "/{operation}/{providerid}"
			+ USER_PARAM;

	private static final String CONNECTION = "conn";
	private static final String LIKES = "likes";
	private static final String CHECKINS = "checkins";

	public SocialClientImpl(final RestClient restClient, final String socialUrl) {
		Validate.notNull(restClient);
		Validate.notBlank(socialUrl);
		this.restClient = restClient;
		this.socialUrl = socialUrl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Boolean> connectionStatus(final String userId) {
		return restClient.exchange(
				socialUrl + STATUS_PATH.replace("{userid}", userId),
				new HttpEntity<Object>(new HttpHeaders()), HttpMethod.GET,
				Map.class);
	}

	@Override
	public String connect(final String userId, final String providerId,
			final String referer) {
		return restClient.exchange(buildUrl(userId, providerId, CONNECTION),
				new HttpEntity<Object>(buildHeaders(referer)), HttpMethod.POST,
				String.class);
	}

	@Override
	public void removeConnections(final String userId, final String providerId) {
		restClient.exchange(buildUrl(userId, providerId, CONNECTION),
				new HttpEntity<Object>(null), HttpMethod.DELETE, Void.class);
	}

	@Override
	public void oauth2Callback(final String userId, final String providerId,
			final String code, final String referer) {
		final String url = buildUrl(userId, providerId, CONNECTION) + "&code="
				+ code;
		oauthCallback(url, referer);
	}

	@Override
	public void oauth1Callback(final String userId, final String providerId,
			final String oauthToken, final String referer) {
		final String url = buildUrl(userId, providerId, CONNECTION)
				+ "&oauth_token=" + oauthToken;
		oauthCallback(url, referer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> likes(final String userId, final String providerId) {
		return restClient.exchange(buildUrl(userId, providerId, LIKES),
				new HttpEntity<Object>(new HttpHeaders()), HttpMethod.GET,
				List.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> checkins(final String userId, final String providerId) {
		return restClient.exchange(buildUrl(userId, providerId, CHECKINS),
				new HttpEntity<Object>(new HttpHeaders()), HttpMethod.GET,
				List.class);
	}

	private void oauthCallback(final String url, final String referer) {
		restClient.exchange(url, new HttpEntity<Object>(buildHeaders(referer)),
				HttpMethod.GET, Void.class);
	}

	private HttpHeaders buildHeaders(final String referer) {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Referer", referer);
		return headers;
	}

	private String buildUrl(final String userId, final String providerId,
			final String operation) {
		return socialUrl
				+ OPERATION_PATH.replace("{operation}", operation)
						.replace("{providerid}", providerId)
						.replace("{userid}", userId);
	}

}
