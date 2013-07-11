package org.tmme.ci.recommender.service;

import java.util.List;

import org.tmme.ci.model.Item;

public interface HybridRecommenderService {

	List<Item> recommend(final String itemId, final String userId);

}
