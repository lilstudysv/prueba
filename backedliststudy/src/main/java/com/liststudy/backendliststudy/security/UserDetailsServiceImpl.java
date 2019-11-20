package com.liststudy.backendliststudy.security;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.liststudy.backendliststudy.user.UserJpaRepository;


@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserJpaRepository userJpaRepository;

	@Autowired
	public UserDetailsServiceImpl(@Qualifier("userJpaRepository") UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return buildUser(userJpaRepository.findByUsername(username));
	}
	
	private User buildUser(com.liststudy.backendliststudy.user.User user) {
		return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
	}

}
