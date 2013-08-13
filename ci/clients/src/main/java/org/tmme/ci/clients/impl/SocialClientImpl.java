package org.tmme.ci.clients.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.tmme.ci.clients.SocialClient;
import org.tmme.ci.common.utils.RestClient;

public class SocialClientImpl implements SocialClient {

	private final String socialConnectUrl;
	private final RestClient restClient;

	public SocialClientImpl(final RestClient restClient,
			final String socialConnectUrl) {
		Validate.notNull(restClient);
		Validate.notBlank(socialConnectUrl);
		this.restClient = restClient;
		this.socialConnectUrl = socialConnectUrl;
	}

	@Override
	public String connect(final String userId, final String providerId,
			final String referer) {
		return restClient.exchange(
				socialConnectUrl.replace("{providerid}", providerId).replace(
						"{userid}", userId), new HttpEntity<Object>(
						buildHeaders(referer)), HttpMethod.POST, String.class);
	}

	@Override
	public void removeConnections(final String userId, final String providerId) {
		restClient.exchange(socialConnectUrl
				.replace("{providerid}", providerId)
				.replace("{userid}", userId), new HttpEntity<Object>(null),
				HttpMethod.DELETE, Void.class);
	}

	@Override
	public void oauth2Callback(final String userId, final String providerId,
			final String code, final String referer) {
		final String url = socialConnectUrl.replace("{providerid}", providerId)
				.replace("{userid}", userId) + "&code=" + code;
		oauthCallback(url, referer);
	}

	@Override
	public void oauth1Callback(final String userId, final String providerId,
			final String oauthToken, final String referer) {
		final String url = socialConnectUrl.replace("{providerid}", providerId)
				.replace("{userid}", userId) + "&oauth_token=" + oauthToken;
		oauthCallback(url, referer);
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

}
