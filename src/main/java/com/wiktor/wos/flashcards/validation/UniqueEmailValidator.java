package com.wiktor.wos.flashcards.validation;

import com.wiktor.wos.flashcards.entity.User;
import com.wiktor.wos.flashcards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private UserService userService;

    @Autowired
    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        List<User> userWithGivenEmail = userService.findByEmail(email);
        return userWithGivenEmail.isEmpty();
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }
}
