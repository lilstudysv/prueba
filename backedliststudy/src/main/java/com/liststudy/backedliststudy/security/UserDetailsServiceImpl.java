package com.liststudy.backedliststudy.security;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.liststudy.backedliststudy.user.UserJpaRepository;


@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return buildUser(userJpaRepository.findByUsername(username));
	}
	
	public User buildUser(com.liststudy.backedliststudy.user.User user) {
		return new User(user.getUsername(),user.getPassword(),new ArrayList<GrantedAuthority>());
	}

}
