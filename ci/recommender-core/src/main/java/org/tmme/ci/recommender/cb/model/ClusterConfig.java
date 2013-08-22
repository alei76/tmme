package org.tmme.ci.recommender.cb.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clusterconfig")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClusterConfig {

	@Indexed(unique = true)
	private String type;
	private List<String> attributes;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(final List<String> attributes) {
		this.attributes = attributes;
	}

}
