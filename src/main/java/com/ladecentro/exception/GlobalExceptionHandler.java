package com.ladecentro.exception;

import com.ladecentro.models.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> globalExceptionHandle(Exception exp) {

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(exp.getMessage());
        response.setDate_time(new Date().toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GlobalException.class)
    ResponseEntity<?> resourceNotFoundExceptionHandle(GlobalException exp) {

        ErrorResponse response = new ErrorResponse();
        response.setStatus(exp.httpStatus.value());
        response.setMessage(exp.getMessage());
        response.setDate_time(new Date().toString());
        return new ResponseEntity<>(response, exp.httpStatus);
    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<?> authenticationException(AuthenticationException exp) {

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setMessage(exp.getMessage());
        response.setDate_time(new Date().toString());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        super.handleMethodArgumentNotValid(ex, headers, status, request);
        List<String> errors = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setMessage(errors.toString());
        response.setDate_time(new Date().toString());
        return new ResponseEntity<>(response, status);
    }
}
