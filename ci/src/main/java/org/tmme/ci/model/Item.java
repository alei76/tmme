package org.tmme.ci.model;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

	@Id
	private String id;

	private final Map<String, Object> values = new HashMap<String, Object>();

	public Object get(final String name) {
		return values.get(name);
	}

	@JsonAnyGetter
	public Map<String, Object> any() {
		return values;
	}

	@JsonAnySetter
	public void set(final String name, final Object value) {
		values.put(name, value);
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

}
