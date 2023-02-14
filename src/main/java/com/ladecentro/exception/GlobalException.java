package com.ladecentro.exception;

import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException {

    public final HttpStatus httpStatus;

    public GlobalException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
