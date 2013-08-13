package org.tmme.ci.id.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmme.ci.id.service.UserService;
import org.tmme.ci.models.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{userid:.+}", method = RequestMethod.GET)
	public @ResponseBody
	User user(@PathVariable(value = "userid") final String email) {
		return userService.getByEmail(email);
	}

}
