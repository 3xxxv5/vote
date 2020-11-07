package com.winterice.vote.controller;


import com.winterice.vote.exception.UnloginException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(value = UnloginException.class)
    public String unloginExceptionHandler(UnloginException e){
        return e.getMessage();
    }


}
