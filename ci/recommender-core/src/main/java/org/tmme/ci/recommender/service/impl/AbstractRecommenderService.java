package org.tmme.ci.recommender.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmme.ci.clients.CatalogClient;
import org.tmme.ci.models.Item;
import org.tmme.ci.recommender.filter.Filter;
import org.tmme.ci.recommender.service.RecommenderService;

public abstract class AbstractRecommenderService implements RecommenderService {

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractRecommenderService.class);

	private final CatalogClient catalogClient;
	private final Filter<Item> filter;

	public AbstractRecommenderService(final CatalogClient catalogClient,
			final Filter<Item> filter) {
		Validate.notNull(catalogClient);
		Validate.notNull(filter);
		this.catalogClient = catalogClient;
		this.filter = filter;
	}

	@Override
	public List<Item> recommend(final String id, final String type,
			final int count) {
		try {
			final List<String> itemIds = preprocess(id, type, count);
			if (!CollectionUtils.isEmpty(itemIds)) {
				final List<Item> items = catalogClient.getItemsByIds(itemIds,
						type);
				return postProcess(items, id);
			}
		} catch (final Exception ex) {
			LOG.error("Exception while trying to get recommendations {}",
					ex.getMessage());
		}
		return Collections.<Item> emptyList();
	}

	private List<Item> postProcess(final List<Item> items, final String id) {
		return filter.filter(items, id);
	}

	protected abstract List<String> preprocess(String id, String type, int count)
			throws Exception;

}