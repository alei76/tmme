package org.tmme.ci.analytics.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tmme.ci.analytics.service.AnalyticsService;
import org.tmme.ci.model.Review;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

	private static final Logger LOG = LoggerFactory
			.getLogger(AnalyticsServiceImpl.class);

	@Override
	public void review(final String userId, final String typeName,
			final String itemId, final Review review) {

	}

	@Override
	public void purchase(final String userId, final String typeName,
			final String itemId) {
	}

	@Override
	public void visit(final String userId, final String typeName,
			final String itemId) {
	}

}
