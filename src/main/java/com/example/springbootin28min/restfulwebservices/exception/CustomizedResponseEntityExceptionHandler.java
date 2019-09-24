package com.example.springbootin28min.restfulwebservices.exception;

import com.example.springbootin28min.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String errMsg = ex.getBindingResult().toString();
        StringBuilder errMsgBuilder = new StringBuilder();

        List<ObjectError> errList = ex.getBindingResult().getAllErrors();
        int count = 0;
        for (ObjectError err : ex.getBindingResult().getAllErrors()) {
            errMsgBuilder.append("" + (++count) + ". ");
            errMsgBuilder.append(err.getDefaultMessage());
        }

        if (errList != null && errList.size() > 0) {
            errMsg = errMsgBuilder.toString();
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse("Validation failed", errMsg, new Date());
        return (new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST));
    }

}
