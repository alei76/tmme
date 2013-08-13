package org.tmme.ci.clients;

import org.tmme.ci.models.Item;

public interface CatalogClient {

	Item findById(String itemId, String itemType);

}
