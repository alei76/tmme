package org.tmme.ci.social.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmme.ci.social.service.CheckinService;
import org.tmme.ci.social.service.LikeService;

@Controller
public class SocialController {

	@Resource(name = "likeServices")
	private Map<String, LikeService> likeServices;
	@Resource(name = "checkinServices")
	private Map<String, CheckinService> checkinServices;

	@RequestMapping(value = "/likes/{providerid}", method = RequestMethod.GET, params = "user")
	public @ResponseBody
	List<String> likes(
			@RequestParam(value = "user", required = true) final String user,
			@PathVariable(value = "providerid") final String providerId) {
		final LikeService likeService = likeServices.get(providerId);
		return likeService.getLikes();
	}

	@RequestMapping(value = "/checkins/{providerid}", method = RequestMethod.GET, params = "user")
	public @ResponseBody
	List<String> checkins(
			@RequestParam(value = "user", required = true) final String user,
			@PathVariable(value = "providerid") final String providerId) {
		final CheckinService checkinService = checkinServices.get(providerId);
		return checkinService.getCheckins();
	}

}
