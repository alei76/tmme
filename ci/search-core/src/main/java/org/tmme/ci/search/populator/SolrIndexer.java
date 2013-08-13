package org.tmme.ci.search.populator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.tmme.ci.clients.CatalogClient;
import org.tmme.ci.models.Item;
import org.tmme.ci.models.ItemParser;
import org.tmme.ci.search.service.SearchService;

public class SolrIndexer {

	private final CatalogClient catalogClient;
	private final SearchService searchService;
	private final boolean oneShot;
	private boolean notRun = true;

	public SolrIndexer(final CatalogClient catalogClient,
			final SearchService searchService, final boolean oneShot) {
		Validate.notNull(catalogClient);
		Validate.notNull(searchService);
		this.catalogClient = catalogClient;
		this.searchService = searchService;
		this.oneShot = oneShot;
	}

	public void updateIndex() {
		if (!oneShot || oneShot && notRun) {
			final Map<String, List<Item>> itemsMap = catalogClient.getItems();
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
