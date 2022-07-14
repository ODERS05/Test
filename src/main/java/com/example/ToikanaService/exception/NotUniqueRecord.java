package com.example.ToikanaService.exception;

import org.springframework.http.HttpStatus;

public class NotUniqueRecord extends BaseException{
    public NotUniqueRecord(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
