package org.tmme.ci.clients.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.tmme.ci.clients.SocialClient;

public class SocialClientImpl implements SocialClient {

	private final RestTemplate restTemplate;
	private final String socialConnectUrl;

	public SocialClientImpl(final RestTemplate restTemplate,
			final String socialConnectUrl) {
		Validate.notNull(restTemplate);
		Validate.notBlank(socialConnectUrl);
		this.restTemplate = restTemplate;
		this.socialConnectUrl = socialConnectUrl;
	}

	@Override
	public String connect(final String userId, final String providerId,
			final String referer) {
		final String url = socialConnectUrl.replace("{providerid}", providerId)
				.replace("{userid}", userId);
		final HttpEntity<Object> entity = new HttpEntity<Object>(
				buildHeaders(referer));
		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.postForEntity(new URI(url), entity,
					String.class);
		} catch (final RestClientException e) {
			e.printStackTrace();
		} catch (final URISyntaxException e) {
			e.printStackTrace();
		}
		return responseEntity.getBody();
	}

	@Override
	public void removeConnections(final String userId, final String providerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void oauth2Callback(final String userId, final String providerId,
			final String code, final String referer) {
		final String url = socialConnectUrl.replace("{providerid}", providerId)
				.replace("{userid}", userId) + "&code=" + code;
		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(new URI(url),
					HttpMethod.GET, new HttpEntity<>(buildHeaders(referer)),
					String.class);
			if (responseEntity != null) {
				System.out.println(responseEntity.getBody());
			}
		} catch (final RestClientException e) {
			e.printStackTrace();
		} catch (final URISyntaxException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void oauth1Callback(final String userId, final String providerId,
			final String oauthToken, final String referer) {
		final String url = socialConnectUrl.replace("{providerid}", providerId)
				.replace("{userid}", userId) + "&oauth_token=" + oauthToken;
		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(new URI(url),
					HttpMethod.GET, new HttpEntity<>(buildHeaders(referer)),
					String.class);
		} catch (final RestClientException e) {
			e.printStackTrace();
		} catch (final URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(responseEntity.getBody());
	}

	private HttpHeaders buildHeaders(final String referer) {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Referer", referer);
		return headers;
	}

}
