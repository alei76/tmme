package org.tmme.ci.social.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tmme.ci.social.service.CheckinService;
import org.tmme.ci.social.service.LikeService;

@Controller
@RequestMapping("/social")
public class SocialController {

	@Resource(name = "likeServices")
	private Map<String, LikeService> likeServices;
	@Resource(name = "checkinServices")
	private Map<String, CheckinService> checkinServices;

	@RequestMapping(value = "/likes/user/{userid}/{providerid}", method = RequestMethod.GET)
	public List<String> likes(
			@PathVariable(value = "userid") final String userId,
			@PathVariable(value = "providerid") final String providerId) {
		final LikeService likeService = likeServices.get(providerId);
		return likeService.getLikes(userId);
	}

	@RequestMapping(value = "/checkins/user/{userid}/{providerid}", method = RequestMethod.GET)
	public List<String> checkins(
			@PathVariable(value = "userid") final String userId,
			@PathVariable(value = "providerid") final String providerId) {
		final CheckinService checkinService = checkinServices.get(providerId);
		return checkinService.getCheckins(userId);
	}

}
