package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidMessageException extends RuntimeException{
    private String message;

    public InvalidMessageException() {
    }

    public InvalidMessageException(String message) {
        super(message);
        this.message = message;
    }
}

    
