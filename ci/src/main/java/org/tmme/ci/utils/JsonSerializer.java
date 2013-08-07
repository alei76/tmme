package org.tmme.ci.utils;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonSerializer {

	private static final Logger LOG = LoggerFactory
			.getLogger(JsonSerializer.class);

	private JsonSerializer() {
		// avoid construction
	}

	public static String serialize(final Map<?, ?> map) {
		String json = null;
		final ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(map);
		} catch (final Exception exc) {
			LOG.error("Cannot serialize object {}. Exception {}", map, exc);
		}
		return json;
	}

}
