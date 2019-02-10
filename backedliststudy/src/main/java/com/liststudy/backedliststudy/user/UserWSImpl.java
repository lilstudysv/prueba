package com.liststudy.backedliststudy.user;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserWSImpl {
	
	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserWSImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userJpaRepository.save(user);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userJpaRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/users/{username}")
	public  ResponseEntity<User> getUserByUsername(@PathVariable String username) {
		return new ResponseEntity<User>(userJpaRepository.findByUsername(username), HttpStatus.OK);
	}
	@GetMapping("/user/")
	public  ResponseEntity<User> getMyUser() {
		String user = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return getUserByUsername(user);
	}
	
	
}
