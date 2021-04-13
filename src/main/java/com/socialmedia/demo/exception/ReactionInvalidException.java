package com.socialmedia.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReactionInvalidException extends RuntimeException {

    public ReactionInvalidException(String message) {
        super(message);
    }
}
