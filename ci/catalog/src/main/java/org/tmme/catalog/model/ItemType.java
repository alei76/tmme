package org.tmme.catalog.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "itemtypes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemType {

    @Id
    private String id;
    private String name;

    public ItemType() {
    }

    public ItemType(final String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
