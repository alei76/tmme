package org.tmme.ci.model;

import org.apache.commons.lang3.Validate;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "purchases")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Purchase {

	private String userId;
	private String itemId;

	// default constructor
	public Purchase() {
	}

	public Purchase(final String userId, final String itemId) {
		Validate.notBlank(userId);
		Validate.notBlank(itemId);
		this.userId = userId;
		this.itemId = itemId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(final String itemId) {
		this.itemId = itemId;
	}

}
