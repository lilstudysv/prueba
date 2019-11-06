package com.liststudy.backendliststudy.security;

import com.liststudy.backendliststudy.user.User;
import com.liststudy.backendliststudy.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("userLoggedToken")
public class UserLoggedToken {

    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserLoggedToken(@Qualifier("userJpaRepository") UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public User getUserLogged(){
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userJpaRepository.findByUsername(login);
    }
}
