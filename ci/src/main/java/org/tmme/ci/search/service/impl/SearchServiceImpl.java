package org.tmme.ci.search.service.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tmme.ci.model.Item;
import org.tmme.ci.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	private static final Logger LOG = LoggerFactory
			.getLogger(SearchServiceImpl.class);

	@Autowired
	private SolrServer solrServer;
	@Autowired
	private RestTemplate solrRestTemplate;
	@Resource(name = "solrUpdateUrl")
	private String solrUpdateUrl;

	@Override
	public List<Item> search(final String search) {
		final List<Item> items = Collections.emptyList();
		final SolrQuery query = new SolrQuery(search);
		query.add("wt", "json");
		QueryResponse queryResponse;
		try {
			queryResponse = solrServer.query(query);
			final SolrDocumentList results = queryResponse.getResults();
			if (results != null) {
				final Iterator<SolrDocument> it = results.iterator();
				while (it.hasNext()) {
					final SolrDocument document = it.next();
					final String author = (String) document
							.getFieldValue("author");
					System.out.println(author);
					items.add(new Item());
				}
			}
		} catch (final SolrServerException exc) {
			LOG.error(
					"Exception executing solr search on query {}. Message {}",
					query, exc.getMessage());
		}
		return items;
	}

	@Override
	public void updateIndex(final String json) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		final HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		final ResponseEntity<String> response = solrRestTemplate.postForEntity(
				solrUpdateUrl, entity, String.class);
		final HttpStatus statusCode = response.getStatusCode();
		if (statusCode != HttpStatus.OK) {
			LOG.error("Error updating solr index, status code {}", statusCode);
		}

	}
}
