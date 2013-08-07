package org.tmme.ci.search.populator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.tmme.ci.catalog.service.CatalogService;
import org.tmme.ci.model.Item;
import org.tmme.ci.model.ItemParser;
import org.tmme.ci.search.service.SearchService;

public class SolrIndexer {

	@Autowired
	private CatalogService catalogService;
	@Autowired
	private SearchService searchService;

	public void updateIndex() {
		final Map<String, List<Item>> itemsMap = catalogService.getItems();
		if (!itemsMap.isEmpty()) {
			final Collection<List<Item>> items = itemsMap.values();
			if (!items.isEmpty()) {
				final Iterator<List<Item>> it = items.iterator();
				while (it.hasNext()) {
					final String itemsAsJson = ItemParser.parseItems(it.next());
					searchService.updateIndex(itemsAsJson);
				}

			}
		}

	}
}
