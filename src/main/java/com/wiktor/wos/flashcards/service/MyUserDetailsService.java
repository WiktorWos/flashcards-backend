package com.wiktor.wos.flashcards.service;

import com.wiktor.wos.flashcards.entity.User;
import com.wiktor.wos.flashcards.repository.UserRepository;
import com.wiktor.wos.flashcards.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        List<User> usersFoundByEmail = userRepository.findByEmail(email);
        if(usersFoundByEmail.isEmpty()) throw new UsernameNotFoundException(email);
        User user = usersFoundByEmail.get(0);
        return new MyUserPrincipal(user);
    }
}
