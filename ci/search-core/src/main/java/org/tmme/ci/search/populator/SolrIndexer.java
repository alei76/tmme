package org.tmme.ci.search.populator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.tmme.ci.catalog.service.CatalogService;
import org.tmme.ci.models.Item;
import org.tmme.ci.models.ItemParser;
import org.tmme.ci.search.service.SearchService;

public class SolrIndexer {

	private final CatalogService catalogService;
	private final SearchService searchService;
	private final boolean oneShot;
	private boolean notRun = true;

	public SolrIndexer(final CatalogService catalogService,
			final SearchService searchService, final boolean oneShot) {
		Validate.notNull(catalogService);
		Validate.notNull(searchService);
		this.catalogService = catalogService;
		this.searchService = searchService;
		this.oneShot = oneShot;
	}

	public void updateIndex() {
		if (!oneShot || oneShot && notRun) {
			final Map<String, List<Item>> itemsMap = catalogService.getItems();
			if (!itemsMap.isEmpty()) {
				final Collection<List<Item>> items = itemsMap.values();
				if (!items.isEmpty()) {
					final Iterator<List<Item>> it = items.iterator();
					while (it.hasNext()) {
						final String itemsAsJson = ItemParser.parseItems(it
								.next());
						searchService.updateIndex(itemsAsJson);
					}
					notRun = false;
				}
			}
		}

	}
}
