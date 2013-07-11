package org.tmme.ci.catalog.repository;

import java.util.List;
import java.util.Set;

import org.tmme.ci.model.Item;

public interface CatalogRepository {

	Set<String> getCollections();

	void createCollection(String collectionName);

	List<Item> getItemsByCollectionName(String collectionName);

	void save(Item item, String collectionName);

	void save(List<Item> items, String collectionName);

	boolean itemExists(String itemId, String collectionName);

}
