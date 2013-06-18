package org.tmme.catalog.model;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public abstract class ItemParser {

	public static Item parseItem(final String body) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(body, Item.class);
		} catch (final Exception ex) {
			throw new IllegalArgumentException("Cannot parse body: " + body);
		}
	}

	public static List<Item> parseItems(final String body) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(body, mapper.getTypeFactory()
					.constructCollectionType(List.class, Item.class));
		} catch (final Exception ex) {
			throw new IllegalArgumentException("Cannot parse body: " + body);
		}
	}

}
