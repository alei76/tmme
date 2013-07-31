package org.tmme.ci.id.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tmme.ci.id.model.RegistrationForm;
import org.tmme.ci.id.service.UserService;
import org.tmme.ci.model.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm(final Model model) {
		model.addAttribute(new RegistrationForm());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid final RegistrationForm form,
			final BindingResult formBinding) {
		if (formBinding.hasErrors()) {
			return null;
		}
		final User user = createUser(form, formBinding);
		if (user == null) {
			return null;
		}
		userService.autoLogin(user.getEmail());
		return user == null ? null : "redirect:/";
	}

	private User createUser(final RegistrationForm form,
			final BindingResult formBinding) {
		try {
			final User user = new User();
			user.setEmail(form.getEmail());
			user.setPassword(form.getPassword());
			user.setRoles(Collections.singletonList("ROLE_USER"));
			userService.createUser(user);
			return user;
		} catch (final Exception ex) {
			formBinding.rejectValue("username", "user.duplicateUsername",
					"already in use");
		}
		return null;
	}
}
