package org.tmme.ci.catalog.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tmme.ci.models.Item;

public interface CatalogService {

	void createItemType(String typeName);

	Set<String> getItemTypes();

	void createItem(String typeName, String body);

	void createItems(String typeName, String body);

	List<Item> getItems(String typeName);

	Map<String, List<Item>> getItems();

	Item getItem(String typeName, String itemId);

	List<Item> getItems(List<String> itemIds);

}
