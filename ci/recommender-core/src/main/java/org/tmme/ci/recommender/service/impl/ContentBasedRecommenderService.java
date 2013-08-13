package org.tmme.ci.recommender.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmme.ci.models.Item;
import org.tmme.ci.recommender.service.RecommenderService;

public class ContentBasedRecommenderService implements RecommenderService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ContentBasedRecommenderService.class);

	@Override
	public List<Item> recommend(final String itemId, final String type,
			final int count) {
		return null;
	}

}
