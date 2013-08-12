package org.tmme.ci.recommender.service;

import java.util.List;

import org.tmme.ci.models.Item;

public interface HybridRecommenderService {

	List<Item> recommend(final String itemId, final String userId);

}
