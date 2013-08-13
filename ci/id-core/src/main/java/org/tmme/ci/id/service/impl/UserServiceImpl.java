package org.tmme.ci.id.service.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.tmme.ci.id.repository.UserRepository;
import org.tmme.ci.id.service.UserService;
import org.tmme.ci.models.User;

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;

	public UserServiceImpl(final UserRepository userRepository,
			final PasswordEncoder passwordEncoder,
			final UserDetailsService userDetailsService) {
		Validate.notNull(userRepository);
		Validate.notNull(passwordEncoder);
		Validate.notNull(userDetailsService);
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void createUser(final User user) {
		final String encodedPassword = passwordEncoder.encodePassword(
				user.getPassword(), user.getEmail());
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}

	@Override
	public void autoLogin(final String email) {
		try {
			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(email);
			final Authentication authentication = new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(), userDetails.getPassword(),
					userDetails.getAuthorities());
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		} catch (final Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
	}

	@Override
	public User getByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

}
