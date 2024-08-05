package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AccountAlreadyExistsException extends RuntimeException {
    private String message;

    public AccountAlreadyExistsException() {
    }

    public AccountAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
    
}
