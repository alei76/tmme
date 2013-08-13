package org.tmme.ci.id.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmme.ci.id.service.UserService;
import org.tmme.ci.models.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	User user(@RequestParam(value = "user", required = true) final String email) {
		// did it with requestParam instead of pathVariable, because there's a
		// problem when a point is in the path,
		// spring thinks it's a file extension and shortens the string. Using a
		// regex like {user:.+} still has problems
		// it returns a 406 error code
		return userService.getByEmail(email);
	}
}
