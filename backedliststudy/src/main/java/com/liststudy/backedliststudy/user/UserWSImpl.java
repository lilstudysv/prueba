package com.liststudy.backedliststudy.user;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserWSImpl {
	

	@GetMapping("/users")
	public ResponseEntity<User> getAllUsuarios() {
		User User = new User();
		User.setUsername("pablo");
		User.setPassword("el sadsasdas");
		return new ResponseEntity<User>(User, HttpStatus.OK);
	}

	@GetMapping("/users/{username}")
	public  ResponseEntity<User> getUsuario(@PathVariable String username) {
		User User = new User();
		User.setUsername("pablo");
		User.setPassword(username);
		return new ResponseEntity<User>(User, HttpStatus.OK);
	}
	


	
}
