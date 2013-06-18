package org.tmme.catalog.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    private String id;

    private ItemType type;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @JsonIgnore
    public ItemType getType() {
        return type;
    }

    public void setType(final ItemType type) {
        this.type = type;
    }

}
