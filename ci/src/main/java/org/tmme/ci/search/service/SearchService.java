package org.tmme.ci.search.service;

import java.util.List;

import org.tmme.ci.model.Item;

public interface SearchService {

	List<Item> search(String searchQuery);

	void updateIndex(String json);

}
