package org.tmme.ci.clients;

import java.util.List;
import java.util.Map;

import org.tmme.ci.models.Item;

public interface CatalogClient {

	Item getItemById(String itemId, String itemType);

	List<Item> getItemsByIds(List<String> itemIds, String itemType);

	Map<String, List<Item>> getItems();

	Map<String, List<Item>> getItemsForTypes(List<String> criteriaTypes);

}
