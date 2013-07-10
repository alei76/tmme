package org.tmme.ci.catalog.repository;

import java.util.List;
import java.util.Set;

import org.tmme.ci.model.Item;

public interface CatalogRepository {

	Set<String> getCollections();

	void createCollection(String name);

	List<Item> getByCollectionName(String name);

	void save(Item item, String name);

	void save(List<Item> items, String name);

}
