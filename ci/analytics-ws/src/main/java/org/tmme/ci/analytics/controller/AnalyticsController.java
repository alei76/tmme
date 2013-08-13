package org.tmme.ci.analytics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.tmme.ci.analytics.models.Review;
import org.tmme.ci.analytics.service.AnalyticsService;

@Controller
public class AnalyticsController {

	@Autowired
	private AnalyticsService analyticsService;

	@RequestMapping(value = "/user/{userid}/review/{type}.{itemid}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	void review(@PathVariable(value = "userid") final String userId,
			@PathVariable(value = "type") final String typeName,
			@PathVariable(value = "itemid") final String itemId,
			final @RequestBody Review review) {
		analyticsService.review(userId, typeName, itemId, review);
	}

	@RequestMapping(value = "/user/{userid}/purchase/{type}.{itemid}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	void purchase(@PathVariable(value = "userid") final String userId,
			@PathVariable(value = "type") final String typeName,
			@PathVariable(value = "itemid") final String itemId) {
		analyticsService.purchase(userId, typeName, itemId);
	}

	@RequestMapping(value = "/user/{userid}/visit/{type}.{itemid}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	void visit(@PathVariable(value = "userid") final String userId,
			@PathVariable(value = "type") final String typeName,
			@PathVariable(value = "itemid") final String itemId) {
		analyticsService.visit(userId, typeName, itemId);
	}

	@RequestMapping(value = "/user/{userid}/accept/{type}.{itemid}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	void acceptRecommendation(
			@PathVariable(value = "userid") final String userId,
			@PathVariable(value = "type") final String typeName,
			@PathVariable(value = "itemid") final String itemId) {
		analyticsService.acceptRecommendation(userId, typeName, itemId);
	}

	@RequestMapping(value = "/user/{userid}/reject/{type}.{itemid}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	void rejectRecommendation(
			@PathVariable(value = "userid") final String userId,
			@PathVariable(value = "type") final String typeName,
			@PathVariable(value = "itemid") final String itemId) {
		analyticsService.rejectRecommendation(userId, typeName, itemId);
	}

	@RequestMapping(value = "/rejects", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<String> rejects(
			@RequestParam(value = "user", required = true) final String userId) {
		return analyticsService.getRejects(userId);
	}

}
