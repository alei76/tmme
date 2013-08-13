package org.tmme.ci.recommender.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.tmme.ci.clients.CatalogClient;
import org.tmme.ci.models.Item;
import org.tmme.ci.recommender.filter.Filter;
import org.tmme.ci.recommender.service.RecommenderService;

public class CollaborativeFilteringRecommenderService implements
		RecommenderService {

	private static final Logger LOG = LoggerFactory
			.getLogger(CollaborativeFilteringRecommenderService.class);

	private final Recommender recommender;
	private final CatalogClient catalogClient;
	private final Converter<List<RecommendedItem>, List<String>> recommendedItemsConverter;
	private final Converter<String, Long> idConverter;
	private final Filter<Item> filter;

	public CollaborativeFilteringRecommenderService(
			final Recommender recommender,
			final CatalogClient catalogClient,
			final Converter<List<RecommendedItem>, List<String>> recommendedItemsConverter,
			final Converter<String, Long> idConverter, final Filter<Item> filter) {
		Validate.notNull(recommender);
		Validate.notNull(catalogClient);
		Validate.notNull(recommendedItemsConverter);
		Validate.notNull(idConverter);
		Validate.notNull(filter);
		this.recommender = recommender;
		this.catalogClient = catalogClient;
		this.recommendedItemsConverter = recommendedItemsConverter;
		this.idConverter = idConverter;
		this.filter = filter;
	}

	@Override
	public List<Item> recommend(final String id, final int count) {
		try {
			final List<RecommendedItem> recommendedItems = recommender
					.recommend(idConverter.convert(id), count);
			final List<String> itemIds = recommendedItemsConverter
					.convert(recommendedItems);
			final List<Item> items = catalogClient.getItemsByIds(itemIds);
			return filter.filter(items, id);
		} catch (final TasteException ex) {
			LOG.error("Exception while trying to get recommendations {}",
					ex.getMessage());
		}
		return Collections.<Item> emptyList();
	}

}
