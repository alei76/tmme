package org.tmme.ci.analytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.tmme.ci.analytics.service.AnalyticsService;
import org.tmme.ci.model.Review;

@Controller
@RequestMapping("/analytics")
public class AnalyticsController {

	@Autowired
	private AnalyticsService analyticsService;

	@RequestMapping(value = "/user/{username}/review/{type}.{itemid}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	void review(@PathVariable(value = "username") final String username,
			@PathVariable(value = "type") final String typeName,
			@PathVariable(value = "itemid") final String itemId,
			final @RequestBody Review review) {
		analyticsService.review(username, typeName, itemId, review);
	}

	@RequestMapping(value = "/user/{username}/purchase/{type}.{itemid}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	void purchase(@PathVariable(value = "username") final String username,
			@PathVariable(value = "type") final String typeName,
			@PathVariable(value = "itemid") final String itemId) {
		analyticsService.purchase(username, typeName, itemId);
	}

	@RequestMapping(value = "/user/{username}/visit/{type}.{itemid}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	void visit(@PathVariable(value = "username") final String username,
			@PathVariable(value = "type") final String typeName,
			@PathVariable(value = "itemid") final String itemId) {
		analyticsService.visit(username, typeName, itemId);
	}

}
