package org.tmme.ci.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "socialconnections")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialConnection {

	@Id
	private String id;
	@Indexed(unique = true)
	private String providerId;
	@Indexed(unique = true)
	private String providerUserId;
	@Indexed(unique = true)
	private String username;
	private String displayName;
	private String profileUrl;
	private String imageUrl;
	private String accessToken;
	private String secret;
	private String refreshToken;
	private long expireTime;

	/**
	 * @return the providerId
	 */
	public String getProviderId() {
		return providerId;
	}

	/**
	 * @param providerId
	 *            the providerId to set
	 */
	public void setProviderId(final String providerId) {
		this.providerId = providerId;
	}

	/**
	 * @return the providerUserId
	 */
	public String getProviderUserId() {
		return providerUserId;
	}

	/**
	 * @param providerUserId
	 *            the providerUserId to set
	 */
	public void setProviderUserId(final String providerUserId) {
		this.providerUserId = providerUserId;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the profileUrl
	 */
	public String getProfileUrl() {
		return profileUrl;
	}

	/**
	 * @param profileUrl
	 *            the profileUrl to set
	 */
	public void setProfileUrl(final String profileUrl) {
		this.profileUrl = profileUrl;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken
	 *            the accessToken to set
	 */
	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret
	 *            the secret to set
	 */
	public void setSecret(final String secret) {
		this.secret = secret;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken
	 *            the refreshToken to set
	 */
	public void setRefreshToken(final String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the expireTime
	 */
	public long getExpireTime() {
		return expireTime;
	}

	/**
	 * @param expireTime
	 *            the expireTime to set
	 */
	public void setExpireTime(final long expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

}
