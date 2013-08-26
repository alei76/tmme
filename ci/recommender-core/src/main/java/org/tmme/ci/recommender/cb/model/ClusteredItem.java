package org.tmme.ci.recommender.cb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clustereditem")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClusteredItem {

	@Indexed
	private String clusterId;
	@Indexed
	private String attribute;
	@Indexed
	private String itemId;

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(final String clusterId) {
		this.clusterId = clusterId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(final String itemId) {
		this.itemId = itemId;
	}

	public void setAttribute(final String attribute) {
		this.attribute = attribute;
	}

	public String getAttribute() {
		return this.attribute;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(this.itemId)
				.append(this.clusterId).append(this.attribute).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ClusteredItem)) {
			return false;
		}
		final ClusteredItem that = (ClusteredItem) obj;
		return new EqualsBuilder().append(this.clusterId, that.clusterId)
				.append(this.itemId, that.itemId)
				.append(this.attribute, that.attribute).isEquals();
	}

	@Override
	public String toString() {
		return "[clusterId: " + clusterId + ", itemId:" + itemId
				+ ", attribute:" + attribute + "]";
	}

}
