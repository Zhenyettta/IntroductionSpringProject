package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.service.MealService;
import com.naukma.introductionspringproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    MealService mealService;

    @Autowired
    public void setMealServiceImpl(MealService mealService) {
        this.mealService = mealService;
    }
}
