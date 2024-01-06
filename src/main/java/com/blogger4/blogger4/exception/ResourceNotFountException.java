package com.blogger4.blogger4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFountException extends RuntimeException  {

    private  String message;

    public ResourceNotFountException(String message) {
         super(message);//super keywod called parent class constructor
    }
}
