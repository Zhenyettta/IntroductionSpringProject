package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {
    private MealService mealService;

    @Autowired
    public void setMealServiceImpl(MealService mealService) {
        this.mealService = mealService;
    }
}
