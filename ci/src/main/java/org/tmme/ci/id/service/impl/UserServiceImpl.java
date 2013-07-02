package org.tmme.ci.id.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tmme.ci.id.service.UserService;
import org.tmme.ci.model.User;
import org.tmme.ci.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void createUser(final User user) {
		final String encodedPassword = passwordEncoder.encodePassword(
				user.getPassword(), user.getUsername());
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}

}
