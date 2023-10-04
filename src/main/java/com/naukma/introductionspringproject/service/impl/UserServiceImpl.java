package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.config.LoginConfig;
import com.naukma.introductionspringproject.model.User;
import com.naukma.introductionspringproject.repository.UserRepo;
import com.naukma.introductionspringproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
    private final LoginConfig loginConfig;
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(LoginConfig loginConfig, UserRepo userRepo) {
        this.loginConfig = loginConfig;
        this.userRepo = userRepo;
    }

    @Override
    public User createUser() {
        User user = new User();
        user.setEmail("email@gmail.com");
        user.setPassword("123");
        user.setFirstName("boba");
        user.setLastName("aboba");
        userRepo.save(user);
        return user;
    }
}