package org.tmme.ci.recommender.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.tmme.ci.model.Item;
import org.tmme.ci.recommender.service.RecommenderService;

@Controller
@RequestMapping("/recommender")
public class RecommenderController {

	@Resource(name = "collaborativeFilteringRecommenderService")
	private RecommenderService cfRecommenderService;

	@Resource(name = "contentBasedRecommenderService")
	private RecommenderService cbRecommenderService;

	@RequestMapping(value = "/{type}.{itemid}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<Item> itemBased(@PathVariable(value = "type") final String typeName,
			@PathVariable(value = "itemid") final String itemId) {
		return null;
	}

	@RequestMapping(value = "/user/{userid}/{type}.{itemid}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<Item> itemAndUserBased(
			@PathVariable(value = "userid") final String userId,
			@PathVariable(value = "type") final String typeName,
			@PathVariable(value = "itemid") final String itemId) {
		return null;
	}

	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<Item> userBased(@PathVariable(value = "username") final String username) {
		return cfRecommenderService.recommend(username, 5);
	}

}
