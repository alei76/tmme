package org.tmme.ci.id.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("authorizationRequest")
public class AccessConfirmationController {

	@Autowired
	private ClientDetailsService clientDetailsService;

	@RequestMapping("/oauth/confirm_access")
	public ModelAndView getAccessConfirmation(final Map<String, Object> model)
			throws Exception {
		final AuthorizationRequest clientAuth = (AuthorizationRequest) model
				.remove("authorizationRequest");
		final ClientDetails client = clientDetailsService
				.loadClientByClientId(clientAuth.getClientId());
		model.put("auth_request", clientAuth);
		model.put("client", client);
		return new ModelAndView("access_confirmation", model);
	}

	@RequestMapping("/oauth/error")
	public String handleError(final Map<String, Object> model) throws Exception {
		model.put("message", "Problem with the OAuth2 protocol");
		return "oauth_error";
	}

}
