package org.tmme.ci.catalog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.tmme.ci.catalog.repository.CatalogRepository;
import org.tmme.ci.catalog.service.CatalogService;
import org.tmme.ci.models.Item;
import org.tmme.ci.models.ItemParser;

public class CatalogServiceImpl implements CatalogService {

	private final CatalogRepository catalogRepository;

	public CatalogServiceImpl(final CatalogRepository catalogRepository) {
		Validate.notNull(catalogRepository);
		this.catalogRepository = catalogRepository;
	}

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
		return catalogRepository.getCollectionNames();
	}

	@Override
	public List<Item> getItems(final String typeName) {
		return catalogRepository.findItemsByCollectionName(typeName);
	}

	@Override
	public Map<String, List<Item>> getItems() {
		final Set<String> itemTypes = getItemTypes();
		final Map<String, List<Item>> items = new HashMap<String, List<Item>>();
		if (CollectionUtils.isNotEmpty(itemTypes)) {
			CollectionUtils.forAllDo(itemTypes, new Closure() {
				@Override
				public void execute(final Object typeName) {
					items.put((String) typeName, getItems((String) typeName));
				}
			});
		}
		return items;
	}

	@Override
	public Item getItem(final String typeName, final String itemId) {
		return catalogRepository.findById(itemId, typeName);
	}

}
