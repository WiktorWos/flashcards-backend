package com.wiktor.wos.flashcards.dto;

import java.io.Serializable;
import java.util.List;

public class AuthenticationResponse implements Serializable {
    private final String jwt;
    private String firstName;
    private String lastName;
    private String email;

    public AuthenticationResponse(String jwt, String firstName, String lastName, String email) {
        this.jwt = jwt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

}
