package org.springframework.social.connect.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.OAuth1ConnectionFactory;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuth1Version;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

//hack to change redirect_uri to complete authentication based on Referer header
public class ConnectSupport {

	private static final Logger LOG = LoggerFactory
			.getLogger(ConnectSupport.class);

	public String buildOAuthUrl(final ConnectionFactory<?> connectionFactory,
			final NativeWebRequest request) {
		return buildOAuthUrl(connectionFactory, request, null);
	}

	public String buildOAuthUrl(final ConnectionFactory<?> connectionFactory,
			final NativeWebRequest request,
			final MultiValueMap<String, String> additionalParameters) {
		if (connectionFactory instanceof OAuth1ConnectionFactory) {
			return buildOAuth1Url(
					(OAuth1ConnectionFactory<?>) connectionFactory, request,
					additionalParameters);
		} else if (connectionFactory instanceof OAuth2ConnectionFactory) {
			return buildOAuth2Url(
					(OAuth2ConnectionFactory<?>) connectionFactory, request,
					additionalParameters);
		} else {
			throw new IllegalArgumentException(
					"ConnectionFactory not supported");
		}
	}

	public Connection<?> completeConnection(
			final OAuth1ConnectionFactory<?> connectionFactory,
			final NativeWebRequest request) {
		final String verifier = request.getParameter("oauth_verifier");
		final AuthorizedRequestToken requestToken = new AuthorizedRequestToken(
				extractCachedRequestToken(request), verifier);
		final OAuthToken accessToken = connectionFactory.getOAuthOperations()
				.exchangeForAccessToken(requestToken, null);
		return connectionFactory.createConnection(accessToken);
	}

	public Connection<?> completeConnection(
			final OAuth2ConnectionFactory<?> connectionFactory,
			final NativeWebRequest request) {
		final String code = request.getParameter("code");
		try {
			final AccessGrant accessGrant = connectionFactory
					.getOAuthOperations().exchangeForAccess(code,
							callbackUrl(request), null);
			return connectionFactory.createConnection(accessGrant);
		} catch (final HttpClientErrorException e) {
			LOG.warn("HttpClientErrorException while completing connection: "
					+ e.getMessage());
			LOG.warn("      Response body: " + e.getResponseBodyAsString());
			throw e;
		}
	}

	// internal helpers

	private String buildOAuth1Url(
			final OAuth1ConnectionFactory<?> connectionFactory,
			final NativeWebRequest request,
			final MultiValueMap<String, String> additionalParameters) {
		final OAuth1Operations oauthOperations = connectionFactory
				.getOAuthOperations();
		final OAuth1Parameters parameters = new OAuth1Parameters(
				additionalParameters);
		if (oauthOperations.getVersion() == OAuth1Version.CORE_10) {
			parameters.setCallbackUrl(callbackUrl(request));
		}
		final OAuthToken requestToken = fetchRequestToken(request,
				oauthOperations);
		request.setAttribute(OAUTH_TOKEN_ATTRIBUTE, requestToken,
				RequestAttributes.SCOPE_SESSION);
		return buildOAuth1Url(oauthOperations, requestToken.getValue(),
				parameters);
	}

	private OAuthToken fetchRequestToken(final NativeWebRequest request,
			final OAuth1Operations oauthOperations) {
		if (oauthOperations.getVersion() == OAuth1Version.CORE_10_REVISION_A) {
			return oauthOperations
					.fetchRequestToken(callbackUrl(request), null);
		}
		return oauthOperations.fetchRequestToken(null, null);
	}

	private String buildOAuth2Url(
			final OAuth2ConnectionFactory<?> connectionFactory,
			final NativeWebRequest request,
			final MultiValueMap<String, String> additionalParameters) {
		final OAuth2Operations oauthOperations = connectionFactory
				.getOAuthOperations();
		final OAuth2Parameters parameters = getOAuth2Parameters(request,
				additionalParameters);
		return oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE,
				parameters);
	}

	private OAuth2Parameters getOAuth2Parameters(
			final NativeWebRequest request,
			final MultiValueMap<String, String> additionalParameters) {
		final OAuth2Parameters parameters = new OAuth2Parameters(
				additionalParameters);
		parameters.setRedirectUri(callbackUrl(request));
		final String scope = request.getParameter("scope");
		if (scope != null) {
			parameters.setScope(scope);
		}
		return parameters;
	}

	private String callbackUrl(final NativeWebRequest request) {
		final HttpServletRequest nativeRequest = request
				.getNativeRequest(HttpServletRequest.class);
		final String referer = nativeRequest.getHeader("Referer");
		if (StringUtils.isNotBlank(referer)) {
			return referer;
		}
		return nativeRequest.getRequestURL().toString();

	}

	private String buildOAuth1Url(final OAuth1Operations oauthOperations,
			final String requestToken, final OAuth1Parameters parameters) {
		return oauthOperations.buildAuthorizeUrl(requestToken, parameters);
	}

	private OAuthToken extractCachedRequestToken(final WebRequest request) {
		final OAuthToken requestToken = (OAuthToken) request.getAttribute(
				OAUTH_TOKEN_ATTRIBUTE, RequestAttributes.SCOPE_SESSION);
		request.removeAttribute(OAUTH_TOKEN_ATTRIBUTE,
				RequestAttributes.SCOPE_SESSION);
		return requestToken;
	}

	private static final String OAUTH_TOKEN_ATTRIBUTE = "oauthToken";

}
