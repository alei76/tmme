package org.tmme.ci.webapp.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tmme.ci.clients.SocialClient;

@Controller
@RequestMapping("/social")
public class SocialController {

	@Autowired
	private SocialClient socialClient;

	@RequestMapping(value = "/likes/{providerid}", method = RequestMethod.GET)
	public List<String> likes(
			@PathVariable(value = "providerid") final String providerId,
			final HttpServletRequest request, final Principal principal) {
		return socialClient.likes(principal.getName(), providerId);
	}

	@RequestMapping(value = "/checkins/{providerid}", method = RequestMethod.GET)
	public List<String> checkins(
			@PathVariable(value = "providerid") final String providerId,
			final Principal principal) {
		return socialClient.checkins(principal.getName(), providerId);
	}

}
