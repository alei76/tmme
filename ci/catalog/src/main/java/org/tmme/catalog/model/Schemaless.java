package org.tmme.catalog.model;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;

public class Schemaless extends Item {

    private Map<String, Object> values;

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

    public void setValues(final Map<String, Object> values) {
        this.values = values;
    }

}
