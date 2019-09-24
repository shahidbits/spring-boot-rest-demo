package com.example.springbootin28min.restfulwebservices.exception;

import com.example.springbootin28min.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {

        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false), new Date());
        return (new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotException(Exception ex, WebRequest request) throws Exception {

        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false), new Date());
        return (new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND));
    }

}
