package org.tmme.ci.recommender.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.tmme.ci.catalog.repository.CatalogRepository;
import org.tmme.ci.model.Item;
import org.tmme.ci.recommender.service.RecommenderService;

public class CollaborativeFilteringRecommenderService implements
		RecommenderService {

	private static final Logger LOG = LoggerFactory
			.getLogger(CollaborativeFilteringRecommenderService.class);

	@Autowired
	private Recommender recommender;

	@Autowired
	private CatalogRepository catalogRepository;

	@Resource(name = "recommendedItemsConverter")
	private Converter<List<RecommendedItem>, List<String>> recommendedItemsConverter;

	@Resource(name = "idConverter")
	private Converter<String, Long> idConverter;

	@Override
	public List<Item> recommend(final String id, final int count) {
		try {
			final List<RecommendedItem> recommendedItems = recommender
					.recommend(idConverter.convert(id), count);
			final List<String> itemIds = recommendedItemsConverter
					.convert(recommendedItems);
			return catalogRepository.findItemsByIds(itemIds);
		} catch (final TasteException ex) {
			LOG.error("Exception while trying to get recommendations {}",
					ex.getMessage());
		}
		return Collections.<Item> emptyList();
	}

}
