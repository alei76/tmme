package org.tmme.ci.recommender.filter.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.tmme.ci.clients.AnalyticsClient;
import org.tmme.ci.models.Item;
import org.tmme.ci.recommender.filter.Filter;

public class RejectRecommendationFilter implements Filter<Item> {

	private final boolean useAnalytics;
	private final AnalyticsClient analyticsClient;

	public RejectRecommendationFilter(final AnalyticsClient analyticsClient,
			final boolean useAnalytics) {
		Validate.notNull(analyticsClient);
		this.analyticsClient = analyticsClient;
		this.useAnalytics = useAnalytics;
	}

	@Override
	public List<Item> filter(final List<Item> elements, final String userId) {
		if (!useAnalytics || CollectionUtils.isEmpty(elements)) {
			return elements;
		}
		final List<String> itemIds = analyticsClient
				.getRejectedRecommendations(userId);
		if (CollectionUtils.isEmpty(itemIds)) {
			return elements;
		}
		final List<Item> filtered = new ArrayList<Item>();
		for (final Item element : elements) {
			if (!itemIds.contains(element.getId())) {
				filtered.add(element);
			}
		}
		return filtered;
	}
}
