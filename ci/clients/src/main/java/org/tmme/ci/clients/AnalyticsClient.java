package org.tmme.ci.clients;

import java.util.List;

public interface AnalyticsClient {

	List<String> getRejectedRecommendations(String userId);

}
