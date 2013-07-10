package org.tmme.ci.id.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.tmme.ci.id.repository.UserRepository;
import org.tmme.ci.id.service.UserService;
import org.tmme.ci.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void createUser(final User user) {
		final String encodedPassword = passwordEncoder.encodePassword(
				user.getPassword(), user.getUsername());
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}

	@Override
	public void autoLogin(final String username) {
		try {
			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(username);
			final Authentication authentication = new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(), userDetails.getPassword(),
					userDetails.getAuthorities());
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		} catch (final Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
	}
}
