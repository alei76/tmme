package org.tmme.ci.id.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tmme.ci.id.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		final org.tmme.ci.model.User user = userRepository
				.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("The username " + username
					+ " was not found");
		}
		final List<String> roles = user.getRoles();
		final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(
				roles.size());
		for (final String role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role));
		}
		return new User(username, user.getPassword(), grantedAuthorities);
	}
}
