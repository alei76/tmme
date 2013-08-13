package org.tmme.ci.recommender.filter;

import java.util.List;

public interface Filter<T> {

	List<T> filter(List<T> elements, String userId);
}
