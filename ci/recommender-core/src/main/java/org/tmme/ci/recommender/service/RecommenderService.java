package org.tmme.ci.recommender.service;

import java.util.List;

import org.tmme.ci.models.Item;

public interface RecommenderService {

	List<Item> recommend(final String id, final int count);

}
