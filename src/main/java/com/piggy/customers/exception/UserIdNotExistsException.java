package com.piggy.customers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserIdNotExistsException extends RuntimeException{
    public UserIdNotExistsException(String message){
        super(message);
    }
}
