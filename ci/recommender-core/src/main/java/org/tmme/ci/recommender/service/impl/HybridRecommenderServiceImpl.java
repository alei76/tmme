package org.tmme.ci.recommender.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tmme.ci.models.Item;
import org.tmme.ci.recommender.service.HybridRecommenderService;

@Service
public class HybridRecommenderServiceImpl implements HybridRecommenderService {

	private static final Logger LOG = LoggerFactory
			.getLogger(HybridRecommenderServiceImpl.class);

	@Override
	public List<Item> recommend(final String itemId, final String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
