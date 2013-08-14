package org.tmme.ci.webapp.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.tmme.ci.clients.SocialClient;

@Controller
@RequestMapping("/social/connect")
public class SocialConnectController {

	private static final String SAME_PATH = "";

	@Autowired
	private SocialClient socialClient;

	@RequestMapping(method = RequestMethod.GET)
	public String social(final Model model, final Principal principal) {
		final Map<String, Boolean> status = socialClient
				.connectionStatus(principal.getName());
		model.addAllAttributes(status);
		return "social";
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.POST)
	public RedirectView connect(
			@PathVariable(value = "providerid") final String providerId,
			final HttpServletRequest request, final Principal principal) {
		final String url = socialClient.connect(principal.getName(),
				providerId, request.getRequestURL().toString());
		return new RedirectView(url);
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.DELETE)
	public RedirectView removeConnections(
			@PathVariable(value = "providerid") final String providerId,
			final Principal principal) {
		socialClient.removeConnections(principal.getName(), providerId);
		return new RedirectView(SAME_PATH, false);
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.GET, params = "code")
	public RedirectView oauth2Callback(
			@PathVariable(value = "providerid") final String providerId,
			@RequestParam(value = "code") final String code,
			final HttpServletRequest request, final Principal principal) {
		socialClient.oauth2Callback(principal.getName(), providerId, code,
				request.getRequestURL().toString());
		return new RedirectView(SAME_PATH, false);
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.GET, params = "oauth_token")
	public RedirectView oauth1Callback(
			@PathVariable(value = "providerid") final String providerId,
			final HttpServletRequest request,
			@RequestParam(value = "oauth_token") final String oauthToken,
			final Principal principal) {
		socialClient.oauth1Callback(principal.getName(), providerId,
				oauthToken, request.getRequestURL().toString());
		return new RedirectView(SAME_PATH, false);
	}

}
