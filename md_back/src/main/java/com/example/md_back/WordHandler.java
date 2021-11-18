package com.example.md_back;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice("com.example.md_back.controller")
@RestController
public class WordHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentHandler(IllegalArgumentException e){
        System.out.println(e.getMessage());  //
        return e.getMessage();
    }
}
