package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.model.User;
import com.naukma.introductionspringproject.util.DiscountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    DiscountUtil discountUtil;

    @Autowired
    LoginConfig loginConfig;

    @Override
    public User createUser() {
        discountUtil.createDiscount(createUser());
        return null;
    }
}
