package org.tmme.ci.catalog.repository;

import java.util.List;
import java.util.Set;

import org.tmme.ci.models.Item;

public interface CatalogRepository {

	Set<String> getCollectionNames();

	void createCollection(String collectionName);

	List<Item> findItemsByCollectionName(String collectionName);

	void save(Item item, String collectionName);

	void save(List<Item> items, String collectionName);

	Item findById(String itemId, String collectionName);

	List<Item> findItemsByIds(List<String> ids, String collectionName);

}
