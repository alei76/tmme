package org.tmme.ci.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;
import org.tmme.ci.clients.SocialClient;

@Controller
@RequestMapping("/social/connect")
public class SocialController {

	private static final String SAME_PATH = "";

	@Autowired
	private SocialClient socialClient;

	@RequestMapping(method = RequestMethod.GET)
	public String social(final NativeWebRequest request, final Model model) {
		// connectorService.connectionStatus(request, model);
		return "social";
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.POST)
	public RedirectView connect(
			@PathVariable(value = "providerid") final String providerId,
			final HttpServletRequest request) {
		final String url = socialClient.connect("nacho@gmail.com", providerId,
				request.getRequestURL().toString());
		return new RedirectView(url);
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.DELETE)
	public RedirectView removeConnections(
			@PathVariable(value = "providerid") final String providerId,
			final HttpServletRequest request, final HttpServletResponse response) {
		socialClient.removeConnections("nacho@gmail.com", providerId);
		return new RedirectView(SAME_PATH, false);
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.GET, params = "code")
	public RedirectView oauth2Callback(
			@PathVariable(value = "providerid") final String providerId,
			@RequestParam(value = "code") final String code,
			final HttpServletRequest request) {
		socialClient.oauth2Callback("nacho@gmail.com", providerId, code,
				request.getRequestURL().toString());
		return new RedirectView(SAME_PATH, false);
	}

	@RequestMapping(value = "/{providerid}", method = RequestMethod.GET, params = "oauth_token")
	public RedirectView oauth1Callback(
			@PathVariable(value = "providerid") final String providerId,
			final HttpServletRequest request,
			@RequestParam(value = "oauth_token") final String oauthToken) {
		socialClient.oauth1Callback("nacho@gmail.com", providerId, oauthToken,
				request.getRequestURL().toString());
		return new RedirectView(SAME_PATH, false);
	}

}
