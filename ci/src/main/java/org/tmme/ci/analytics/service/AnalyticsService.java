package org.tmme.ci.analytics.service;

import org.tmme.ci.model.Review;

public interface AnalyticsService {

	void review(String userId, String typeName, String itemId, Review review);

	void purchase(String userId, String typeName, String itemId);

	void visit(String userId, String typeName, String itemId);

}
