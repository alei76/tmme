package org.tmme.ci.recommender.service.impl;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.math.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.tmme.ci.clients.CatalogClient;
import org.tmme.ci.models.Item;
import org.tmme.ci.recommender.filter.Filter;

public class CollaborativeFilteringRecommenderService extends
		AbstractRecommenderService {

	private static final Logger LOG = LoggerFactory
			.getLogger(CollaborativeFilteringRecommenderService.class);

	private final Recommender recommender;
	private final Converter<List<RecommendedItem>, List<String>> recommendedItemsConverter;
	private final Converter<String, Long> idConverter;

	public CollaborativeFilteringRecommenderService(
			final Recommender recommender,
			final CatalogClient catalogClient,
			final Converter<List<RecommendedItem>, List<String>> recommendedItemsConverter,
			final Converter<String, Long> idConverter, final Filter<Item> filter) {
		super(catalogClient, filter);
		Validate.notNull(recommender);
		Validate.notNull(recommendedItemsConverter);
		Validate.notNull(idConverter);
		this.recommender = recommender;
		this.recommendedItemsConverter = recommendedItemsConverter;
		this.idConverter = idConverter;
	}

	@Override
	protected List<String> preprocess(final String id, final String type,
			final int count) throws Exception {
		final List<RecommendedItem> recommendedItems = recommender.recommend(
				idConverter.convert(id), count);
		final List<String> itemIds = recommendedItemsConverter
				.convert(recommendedItems);
		LOG.debug("Recommended Items for {} : {}", id,
				Arrays.toString(itemIds.toArray()));
		return itemIds;
	}

}
