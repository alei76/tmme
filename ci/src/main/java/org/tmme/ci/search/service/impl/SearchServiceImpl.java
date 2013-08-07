package org.tmme.ci.search.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tmme.ci.model.Item;
import org.tmme.ci.model.ItemParser;
import org.tmme.ci.search.service.SearchService;
import org.tmme.ci.utils.JsonSerializer;

public class SearchServiceImpl implements SearchService {

	private static final Logger LOG = LoggerFactory
			.getLogger(SearchServiceImpl.class);

	@Autowired
	private SolrServer solrServer;
	@Autowired
	private RestTemplate solrRestTemplate;

	private final Set<String> ignorableFields;
	private final String solrUpdateUrl;

	public SearchServiceImpl(final String solrUpdateUrl,
			final Set<String> ignorableFields) {
		Validate.notBlank(solrUpdateUrl);
		Validate.notEmpty(ignorableFields);
		this.solrUpdateUrl = solrUpdateUrl;
		this.ignorableFields = ignorableFields;
	}

	@Override
	public List<Item> search(final String searchQuery) {
		final SolrQuery query = new SolrQuery(searchQuery);
		query.add("wt", "json");
		QueryResponse queryResponse;
		try {
			queryResponse = solrServer.query(query);
			if (queryResponse != null) {
				final SolrDocumentList results = queryResponse.getResults();
				if (results != null) {
					return parseSearchResults(results);
				}
			}
		} catch (final SolrServerException exc) {
			LOG.error(
					"Exception executing solr search on query {}. Message {}",
					query, exc.getMessage());
		}
		return Collections.<Item> emptyList();
	}

	@Override
	public void updateIndex(final String json) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		final HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		try {
			final ResponseEntity<String> response = solrRestTemplate
					.postForEntity(solrUpdateUrl, entity, String.class);
			final HttpStatus statusCode = response.getStatusCode();
			if (statusCode != HttpStatus.OK) {
				LOG.error(
						"Error updating solr index, status code {}. Response body {}",
						statusCode, response.getBody());
			}
		} catch (final Exception ex) {
			LOG.error("Exception updating solr index {}", ex.getMessage());
		}
	}

	private List<Item> parseSearchResults(final SolrDocumentList results) {
		final Iterator<SolrDocument> it = results.iterator();
		final List<Item> items = new ArrayList<Item>();
		while (it.hasNext()) {
			final SolrDocument document = it.next();
			final Map<String, Object> clonedValueMap = new HashMap<String, Object>();
			final Collection<String> fieldNames = document.getFieldNames();
			for (final String fieldName : fieldNames) {
				if (!ignorableFields.contains(fieldName)) {
					clonedValueMap.put(fieldName,
							document.getFieldValue(fieldName));
				}
			}
			final String json = JsonSerializer.serialize(clonedValueMap);
			if (json != null) {
				items.add(ItemParser.parseItem(json));
			}
		}
		return items;
	}
}
