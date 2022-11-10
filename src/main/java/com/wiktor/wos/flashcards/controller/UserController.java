package com.wiktor.wos.flashcards.controller;

import com.wiktor.wos.flashcards.dto.AuthenticationRequest;
import com.wiktor.wos.flashcards.dto.AuthenticationResponse;
import com.wiktor.wos.flashcards.entity.User;
import com.wiktor.wos.flashcards.exception.UserNotFoundException;
import com.wiktor.wos.flashcards.security.MyUserPrincipal;
import com.wiktor.wos.flashcards.service.UserService;
import com.wiktor.wos.flashcards.validation.OnCreate;
import com.wiktor.wos.flashcards.validation.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate/signIn")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws RuntimeException {
        userService.authenticate(authenticationRequest);
        MyUserPrincipal userDetails = (MyUserPrincipal) userService.getUserDetailsFromAuthRequest(authenticationRequest);
        String jwt = userService.generateJwt(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getFirstName(), userDetails.getLastName(),
                                                             userDetails.getEmail()));
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getOneUser(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(() -> new UserNotFoundException("Entity not found"));
    }

    @Validated(OnCreate.class)
    @PostMapping("/authenticate/signUp")
    public User addUser(@Valid @RequestBody User user) {
        userService.save(user);
        return user;
    }

    @Validated(OnUpdate.class)
    @PutMapping("/users/{id}")
    public User updateUser(@Valid @RequestBody User updatedUser, @PathVariable Long id) {
        userService.updateUser(updatedUser, id);
        return updatedUser;
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        optionalUser.ifPresent(user -> userService.delete(user));
        return optionalUser.orElseThrow(() -> new UserNotFoundException("Entity not found"));
    }
}