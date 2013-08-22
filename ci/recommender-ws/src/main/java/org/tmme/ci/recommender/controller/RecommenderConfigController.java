package org.tmme.ci.recommender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.tmme.ci.recommender.cb.model.ClusterConfig;
import org.tmme.ci.recommender.cb.service.RecommenderConfigService;

@Controller
@RequestMapping(value = "config")
public class RecommenderConfigController {

	@Autowired
	private RecommenderConfigService recommenderConfigService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public @ResponseBody
	void configure(@RequestBody final ClusterConfig config) {
		recommenderConfigService.addConfig(config);
	}

}
