package com.example.ToikanaService.exception;

import org.springframework.http.HttpStatus;

public class NotUniqueFloor extends BaseException{
    public NotUniqueFloor(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
