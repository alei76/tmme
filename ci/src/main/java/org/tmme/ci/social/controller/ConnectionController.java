package org.tmme.ci.social.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.view.RedirectView;
import org.tmme.ci.social.service.ConnectorService;

@Controller
@RequestMapping("/social/connect")
public class ConnectionController {

	private static final String SAME_PATH = "";

	@Autowired
	private ConnectorService connectorService;

	@RequestMapping(method = RequestMethod.GET)
	public String social(final NativeWebRequest request, final Model model) {
		connectorService.connectionStatus(request, model);
		return "social";
	}

	@RequestMapping(value = "/{providerId}", method = RequestMethod.POST)
	public RedirectView connect(@PathVariable final String providerId,
			final NativeWebRequest request) {
		return new RedirectView(connectorService.connect(providerId, request));
	}

	@RequestMapping(value = "/{providerId}", method = RequestMethod.DELETE)
	public RedirectView removeConnections(
			@PathVariable final String providerId,
			final HttpServletRequest request, final HttpServletResponse response) {
		connectorService.removeConnections(providerId, new ServletWebRequest(
				request, response));
		return new RedirectView(SAME_PATH, false);
	}

	@RequestMapping(value = "/{providerId}", method = RequestMethod.GET, params = "code")
	public RedirectView oauth2Callback(@PathVariable final String providerId,
			final NativeWebRequest request) {
		connectorService.oauth2Callback(providerId, request);
		return new RedirectView(SAME_PATH, false);
	}

	@RequestMapping(value = "/{providerId}", method = RequestMethod.GET, params = "oauth_token")
	public RedirectView oauth1Callback(@PathVariable final String providerId,
			final NativeWebRequest request) {
		connectorService.oauth1Callback(providerId, request);
		return new RedirectView(SAME_PATH, false);
	}

}
