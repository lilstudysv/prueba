package com.liststudy.backendliststudy.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f


    @ExceptionHandler(Exception.class)
    ResponseEntity handleControllerException() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}