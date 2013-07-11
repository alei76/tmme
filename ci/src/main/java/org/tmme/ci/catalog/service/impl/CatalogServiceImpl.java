package org.tmme.ci.catalog.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmme.ci.catalog.repository.CatalogRepository;
import org.tmme.ci.catalog.service.CatalogService;
import org.tmme.ci.model.Item;
import org.tmme.ci.model.ItemParser;

@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private CatalogRepository catalogRepository;

	@Override
	public void createItemType(final String type) {
		catalogRepository.createCollection(type);
	}

	@Override
	public void createItem(final String typeName, final String body) {
		final Item item = ItemParser.parseItem(body);
		catalogRepository.save(item, typeName);
	}

	@Override
	public void createItems(final String typeName, final String body) {
		final List<Item> items = ItemParser.parseItems(body);
		catalogRepository.save(items, typeName);
	}

	@Override
	public Set<String> getItemTypes() {
		return catalogRepository.getCollections();
	}

	@Override
	public List<Item> getItems(final String typeName) {
		return catalogRepository.getItemsByCollectionName(typeName);
	}

}
