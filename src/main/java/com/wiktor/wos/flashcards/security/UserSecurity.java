package com.wiktor.wos.flashcards.security;

import com.wiktor.wos.flashcards.entity.User;
import com.wiktor.wos.flashcards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {
    private UserService userService;

    @Autowired
    public UserSecurity(UserService userService) {
        this.userService = userService;
    }

    public boolean isUsersIdSameAsURL(Authentication authentication, Long userId) {
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        String email = myUserPrincipal.getEmail();
        User user = userService.findByEmail(email).get(0);
        return user.getUserId().equals(userId);
    }
}
