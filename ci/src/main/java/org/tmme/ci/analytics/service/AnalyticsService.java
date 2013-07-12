package org.tmme.ci.analytics.service;

import org.tmme.ci.model.Review;

public interface AnalyticsService {

	void review(String username, String typeName, String itemId, Review review);

	void purchase(String username, String typeName, String itemId);

	void visit(String username, String typeName, String itemId);

}
