package com.liststudy.backedliststudy.security;


import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.liststudy.backedliststudy.user.User user = new com.liststudy.backedliststudy.user.User();
		user.setUsername("admin");
		user.setPassword("$2a$10$vDAMBx35BWMIjOblBwtHd.ZRL.DOz9qV91ElJwxc7pLW4B0tLYl4m");
		return buildUser(user);
	}
	
	public User buildUser(com.liststudy.backedliststudy.user.User user) {
		return new User(user.getUsername(),user.getPassword(),new ArrayList<GrantedAuthority>());
	}

}
