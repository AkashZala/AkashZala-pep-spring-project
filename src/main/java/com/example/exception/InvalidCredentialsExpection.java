package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InvalidCredentialsExpection extends RuntimeException{
    private String message;

    public InvalidCredentialsExpection() {
    }

    public InvalidCredentialsExpection(String message) {
        super(message);
        this.message = message;
    }
    
}
