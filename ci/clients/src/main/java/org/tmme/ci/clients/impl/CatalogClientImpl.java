package org.tmme.ci.clients.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
	public Item getItemById(final String itemId, final String itemType) {
		return restClient.exchange(catalogUrl + "/item/" + itemType + "."
				+ itemId, new HttpEntity<Object>(buildHeaders()),
				HttpMethod.GET, Item.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getItemsByIds(final List<String> itemIds,
			final String itemType) {
		return restClient.exchange(catalogUrl + "/items/" + itemType
				+ queryString(itemIds), new HttpEntity<Object>(buildHeaders()),
				HttpMethod.GET, List.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<Item>> getItems() {
		return restClient.exchange(catalogUrl + "/items",
				new HttpEntity<Object>(buildHeaders()), HttpMethod.GET,
				Map.class);
	}

	private String queryString(final List<String> itemIds) {
		final Iterator<String> it = itemIds.iterator();
		final StringBuilder sb = new StringBuilder("?itemids=");
		while (it.hasNext()) {
			sb.append(it.next());
			if (it.hasNext()) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	private HttpHeaders buildHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
