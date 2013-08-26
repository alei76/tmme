package org.tmme.ci.recommender.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.mahout.math.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmme.ci.clients.CatalogClient;
import org.tmme.ci.models.Item;
import org.tmme.ci.recommender.cb.model.ClusteredItem;
import org.tmme.ci.recommender.cb.repository.ClusteredItemRepository;
import org.tmme.ci.recommender.filter.Filter;

public class ContentBasedRecommenderService extends AbstractRecommenderService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ContentBasedRecommenderService.class);

	private final ClusteredItemRepository repository;

	public ContentBasedRecommenderService(final CatalogClient catalogClient,
			final Filter<Item> filter, final ClusteredItemRepository repository) {
		super(catalogClient, filter);
		Validate.notNull(repository);
		this.repository = repository;
	}

	@Override
	protected List<String> preprocess(final String id, final String type,
			final int count) throws Exception {
		final ClusteredItem clusteredItem = repository.findByItemId(id);
		final List<String> itemIds = new ArrayList<String>(count);
		if (clusteredItem != null) {
			final List<ClusteredItem> clusteredItems = repository
					.findByClusterId(clusteredItem.getClusterId());
			clusteredItems.remove(clusteredItem);
			for (int i = 0; i < clusteredItems.size(); i++) {
				if (i >= count) {
					break;
				}
				itemIds.add(clusteredItems.get(i).getItemId());
			}
		}
		LOG.debug("Clustered Items for {} : {}", id,
				Arrays.toString(itemIds.toArray()));
		return itemIds;
	}

}
