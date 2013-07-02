package org.tmme.ci.catalog.service.impl;

import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmme.ci.catalog.service.CatalogService;
import org.tmme.ci.model.Item;
import org.tmme.ci.model.ItemParser;
import org.tmme.ci.model.ItemType;
import org.tmme.ci.repositories.ItemRepository;
import org.tmme.ci.repositories.ItemTypeRepository;

@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemTypeRepository itemTypeRepository;

	@Override
	public void createItemType(final String type) {
		itemTypeRepository.save(new ItemType(type));
	}

	@Override
	public void createItem(final String typeName, final String body) {
		final ItemType itemType = itemTypeRepository.findByName(typeName);
		Validate.notNull(itemType, "Item type does not exist for name "
				+ typeName);
		final Item item = ItemParser.parseItem(body);
		item.setType(itemType);
		itemRepository.save(item);
	}

	@Override
	public void createItems(final String typeName, final String body) {
		final ItemType itemType = itemTypeRepository.findByName(typeName);
		Validate.notNull(itemType, "Item type does not exist for name "
				+ typeName);
		final List<Item> items = ItemParser.parseItems(body);
		CollectionUtils.forAllDo(items, new Closure() {
			@Override
			public void execute(final Object input) {
				((Item) input).setType(itemType);
			}
		});
		itemRepository.save(items);
	}

	@Override
	public List<ItemType> getItemTypes() {
		return itemTypeRepository.findAll();
	}

	@Override
	public List<Item> getItems(final String typeName) {
		return itemRepository.findByTypeName(typeName);
	}

}
