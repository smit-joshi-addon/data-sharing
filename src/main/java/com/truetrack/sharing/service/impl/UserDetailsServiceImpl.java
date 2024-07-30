package com.truetrack.sharing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.truetrack.sharing.config.auth.AuthUserDetails;
import com.truetrack.sharing.entity.Users;
import com.truetrack.sharing.service.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userService.getUser(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid Username Or Password");
		}

		return new AuthUserDetails(user);
	}
}
