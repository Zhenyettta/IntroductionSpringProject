package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.config.LoginConfig;
import com.naukma.introductionspringproject.model.User;
import com.naukma.introductionspringproject.service.UserService;
import com.naukma.introductionspringproject.util.DiscountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final DiscountUtil discountUtil;
    private final LoginConfig loginConfig;

    @Autowired
    public UserServiceImpl(DiscountUtil discountUtil, LoginConfig loginConfig) {
        this.discountUtil = discountUtil;
        this.loginConfig = loginConfig;
    }

    @Override
    public User createUser() {
        User user = new User();
        discountUtil.createDiscount(user);
        return user;
    }
}