package org.tmme.ci.common.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestClient {

	private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);
	private final RestTemplate restTemplate;

	public RestClient(final RestTemplate restTemplate) {
		Validate.notNull(restTemplate);
		this.restTemplate = restTemplate;
	}

	public <T> T exchange(final String url, final HttpEntity<?> requestEntity,
			final HttpMethod method, final Class<T> responseType) {
		try {
			final URI uri = new URI(url);
			final ResponseEntity<T> response = restTemplate.exchange(uri,
					method, requestEntity, responseType);
			return response.getBody();
		} catch (final RestClientException exc) {
			LOG.error("RestClientException {}", exc.getMessage());
			if (exc instanceof HttpClientErrorException) {
				LOG.error("Cause {}", ((HttpClientErrorException) exc)
						.getResponseBodyAsString());
			}
		} catch (final URISyntaxException exc) {
			LOG.error("URI Exception {}", exc.getMessage());
		} catch (final Exception exc) {
			LOG.error("Unexpected exception {}", exc.getMessage());
		}
		return null;
	}

	public <T> T exchange(final String url, final HttpEntity<?> requestEntity,
			final HttpMethod method,
			final ParameterizedTypeReference<T> typeReference) {
		try {
			final URI uri = new URI(url);
			final ResponseEntity<T> response = restTemplate.exchange(uri,
					method, requestEntity, typeReference);
			return response.getBody();
		} catch (final RestClientException exc) {
			LOG.error("RestClientException {}", exc.getMessage());
			if (exc instanceof HttpClientErrorException) {
				LOG.error("Cause {}", ((HttpClientErrorException) exc)
						.getResponseBodyAsString());
			}
		} catch (final URISyntaxException exc) {
			LOG.error("URI Exception {}", exc.getMessage());
		} catch (final Exception exc) {
			LOG.error("Unexpected exception {}", exc.getMessage());
		}
		return null;
	}
}
