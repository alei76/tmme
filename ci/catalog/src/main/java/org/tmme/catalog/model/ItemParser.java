package org.tmme.catalog.model;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public abstract class ItemParser {

    public enum Type {
        APPLICATION("application", Application.class), MOVIE("movie", Movie.class), BOOK("book", Book.class), SCHEMALESS(
                "schemaless", Schemaless.class) {

            @Override
            Item parse(final String body) {
                final ObjectMapper mapper = new ObjectMapper();
                try {
                    final Map<String, Object> values = mapper.readValue(body, new TypeReference<Map<String, Object>>() {
                    });
                    final Schemaless item = new Schemaless();
                    item.setValues(values);
                    return item;
                } catch (final Exception ex) {
                    throw new IllegalArgumentException("Cannot parse body: " + body);
                }
            }
        };

        private Type(final String name, final Class<? extends Item> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

        private final String name;
        private final Class<? extends Item> clazz;

        Item parse(final String body) {
            final ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(body, clazz);
            } catch (final Exception ex) {
                throw new IllegalArgumentException("Cannot parse body: " + body + " with type name: " + name);
            }
        }
    }

    private static Type getTypeFromName(final String name) {
        for (final Type itemType : Type.values()) {
            if (itemType.name.equals(name)) {
                return itemType;
            }
        }
        return Type.SCHEMALESS;
    }

    public static Item parseItem(final String name, final String body) {
        final Type type = getTypeFromName(name);
        return type.parse(body);
    }

    public static List<Item> parseItems(final String name, final String body) {
        final Type type = getTypeFromName(name);
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(body, mapper.getTypeFactory().constructCollectionType(List.class, type.clazz));
        } catch (final Exception ex) {
            throw new IllegalArgumentException("Cannot parse body: " + body + " with type name: " + name);
        }
    }

}
