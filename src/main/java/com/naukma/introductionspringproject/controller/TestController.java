package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.config.LoginConfig;
import com.naukma.introductionspringproject.entity.MealEntity;
import com.naukma.introductionspringproject.service.MealService;
import com.naukma.introductionspringproject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    private LoginConfig loginConfig;

    private UserService userService;
    private MealService mealService;


    @Autowired
    public TestController(LoginConfig loginConfig, UserService userService, MealService mealService) {
        this.loginConfig = loginConfig;
        this.userService = userService;
        this.mealService = mealService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return new ResponseEntity<>(userService.readUser(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> updateUser(@RequestBody MealEntity meal){
        mealService.updateMeal(meal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
