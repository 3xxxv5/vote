package com.winterice.vote.controller;


import com.winterice.vote.annotation.Auth;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestController {
    @GetMapping("/visitor")
    public String test01(){
        return "this is a visitor";
    }

    @GetMapping("/user")
    @Auth
    public String test02(){
        return "this is a user";
    }

    @GetMapping("/admin")
    @Auth("admin")
    public String test03(){
        return "this is a admin";

    }
}
