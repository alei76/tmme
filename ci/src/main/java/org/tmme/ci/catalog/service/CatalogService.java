package org.tmme.ci.catalog.service;

import java.util.List;

import org.tmme.ci.model.Item;
import org.tmme.ci.model.ItemType;

public interface CatalogService {

	void createItemType(String typeName);

	List<ItemType> getItemTypes();

	void createItem(String typeName, String body);

	void createItems(String typeName, String body);

	List<Item> getItems(String typeName);

}
