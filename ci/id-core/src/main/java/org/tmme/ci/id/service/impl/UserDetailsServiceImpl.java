package org.tmme.ci.id.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.tmme.ci.id.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(final UserRepository userRepository) {
		Validate.notNull(userRepository);
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(final String email)
			throws UsernameNotFoundException {
		final org.tmme.ci.id.models.User user = userRepository
				.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("The email " + email
					+ " was not found");
		}
		final List<String> roles = user.getRoles();
		final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(
				roles.size());
		for (final String role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role));
		}
		return new User(email, user.getPassword(), grantedAuthorities);
	}
}
