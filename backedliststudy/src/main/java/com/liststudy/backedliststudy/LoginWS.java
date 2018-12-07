package com.liststudy.backedliststudy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginWS {
	
	@GetMapping("/user")
	public 	ResponseEntity<Login> getLogin(){
		Login login = new Login();
		login.setUser("pablo");
		login.setPassword("el villares");
		return new ResponseEntity<Login>(login, HttpStatus.OK);
	}
	
}
