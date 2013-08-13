package org.tmme.ci.clients.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.tmme.ci.clients.CatalogClient;
import org.tmme.ci.common.utils.RestClient;
import org.tmme.ci.models.Item;

public class CatalogClientImpl implements CatalogClient {

	private final String catalogUrl;
	private final RestClient restClient;

	public CatalogClientImpl(final RestClient restClient,
			final String catalogUrl) {
		Validate.notNull(restClient);
		Validate.notBlank(catalogUrl);
		this.restClient = restClient;
		this.catalogUrl = catalogUrl;
	}

	@Override
	public Item findById(final String itemId, final String itemType) {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		return restClient.exchange(catalogUrl + itemType + "." + itemId,
				new HttpEntity<Object>(headers), HttpMethod.GET, Item.class);
	}

}
