package org.tmme.ci.social.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.tmme.ci.social.service.ConnectorService;

@Controller
@RequestMapping("/conn")
public class ConnectionController {

	private static final Logger LOG = LoggerFactory
			.getLogger(ConnectionController.class);

	@Autowired
	private ConnectorService connectorService;

	private static final Pattern PATTERN = Pattern
			.compile("(redirect_uri=)((http).+)");

	@RequestMapping(value = "/status", method = RequestMethod.GET, params = "user")
	public @ResponseBody
	Map<String, Boolean> status(final NativeWebRequest request) {
		return connectorService.connectionStatus(request);
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.POST, params = "user")
	public @ResponseBody
	String connect(@PathVariable(value = "providerid") final String providerId,
			final NativeWebRequest request) {
		final String url = connectorService.connect(providerId, request);
		return changeUrl(request, url);
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.DELETE, params = "user")
	public @ResponseBody
	void removeConnections(
			@PathVariable(value = "providerid") final String providerId,
			final HttpServletRequest request, final HttpServletResponse response) {
		connectorService.removeConnections(providerId, new ServletWebRequest(
				request, response));
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.GET, params = "code")
	public @ResponseBody
	void oauth2Callback(
			@RequestParam(value = "code", required = true) final String code,
			@RequestParam(value = "user", required = true) final String userId,
			@PathVariable(value = "providerid") final String providerId,
			final NativeWebRequest request) {
		connectorService.oauth2Callback(providerId, request);
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.GET, params = "oauth_token")
	public @ResponseBody
	void oauth1Callback(
			@RequestParam(value = "oauth_token", required = true) final String oauthToken,
			@RequestParam(value = "user", required = true) final String userId,
			@PathVariable(value = "providerid") final String providerId,
			final NativeWebRequest request) {
		connectorService.oauth1Callback(providerId, request);
	}

	private String changeUrl(final NativeWebRequest request, final String url) {
		final HttpServletRequest nativeRequest = request
				.getNativeRequest(HttpServletRequest.class);
		final String referer = nativeRequest.getHeader("Referer");
		if (StringUtils.isNotBlank(referer)) {
			try {
				final String encodedReferer = URLEncoder.encode(referer,
						"UTF-8");
				final Matcher m = PATTERN.matcher(url);
				final StringBuffer result = new StringBuffer();
				while (m.find()) {
					m.appendReplacement(result, m.group(1) + encodedReferer);
				}
				m.appendTail(result);
				return result.toString();
			} catch (final UnsupportedEncodingException e) {
				LOG.error("Encoding not supported, {}", e.getMessage());
			}
		}
		return url;
	}

}
