package com.piggy.customers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class NotAuthorizedException extends RuntimeException{

    public NotAuthorizedException(String message){
        super(message);
    }
}
