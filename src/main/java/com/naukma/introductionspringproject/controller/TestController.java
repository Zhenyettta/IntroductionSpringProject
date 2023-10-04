package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.config.LoginConfig;
import com.naukma.introductionspringproject.service.UserService;
import com.naukma.introductionspringproject.util.ConditionalLoggerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private LoginConfig loginConfig;
    private ConditionalLoggerWrapper logger;
    private UserService userService;


    @Autowired
    public TestController(LoginConfig loginConfig, UserService userService) {
        this.loginConfig = loginConfig;
        this.userService = userService;
    }
    @Autowired(required = false)
    public void setConditionalLoggerWrapper(ConditionalLoggerWrapper conditionalLoggerWrapper) {
        this.logger = conditionalLoggerWrapper;
    }



    @GetMapping()
    public ResponseEntity<String> addNewAdmin() {
        userService.createUser();
        return new ResponseEntity<>(HttpStatus.OK);

    }





}
