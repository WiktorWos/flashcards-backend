package com.wiktor.wos.flashcards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SetIsNotPublicException extends RuntimeException{
    public SetIsNotPublicException() {
        super("Set is not public");
    }
}
