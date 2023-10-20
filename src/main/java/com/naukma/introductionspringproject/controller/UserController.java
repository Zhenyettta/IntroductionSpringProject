package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.dto.UserDTO;
import com.naukma.introductionspringproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return new ResponseEntity<>(userService.readUser(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO user){
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO user){
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
