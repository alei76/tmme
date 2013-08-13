package org.tmme.ci.clients.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.tmme.ci.clients.UserClient;
import org.tmme.ci.common.utils.RestClient;
import org.tmme.ci.models.User;

public class UserClientImpl implements UserClient {

	private final String idUrl;
	private final RestClient restClient;

	public UserClientImpl(final RestClient restClient, final String idUrl) {
		Validate.notNull(restClient);
		Validate.notBlank(idUrl);
		this.restClient = restClient;
		this.idUrl = idUrl;
	}

	@Override
	public User findByEmail(final String email) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return restClient.exchange(idUrl + "?user=" + email,
				new HttpEntity<Object>(headers), HttpMethod.GET, User.class);
	}
}
