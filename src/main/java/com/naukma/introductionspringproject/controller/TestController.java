package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.config.LoginConfig;
import com.naukma.introductionspringproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    LoginConfig loginConfig;


    UserService userService;


    @GetMapping()
    public ResponseEntity<String> addNewAdmin() {
        return ResponseEntity.ok(loginConfig.encodePassword("asd"));
    }

    @Autowired
    public void setUserServiceImpl(UserService userService) {
        this.userService = userService;
    }
}
