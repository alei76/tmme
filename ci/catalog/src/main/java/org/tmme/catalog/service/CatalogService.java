package org.tmme.catalog.service;

import java.util.List;

import org.tmme.catalog.model.Item;
import org.tmme.catalog.model.ItemType;

public interface CatalogService {

    void createItemType(String typeName);

    List<ItemType> getItemTypes();

    void createItem(String typeName, String body);

    void createItems(String typeName, String body);

    List<Item> getItems(String typeName);

}
