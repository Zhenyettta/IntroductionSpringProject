package com.naukma.introductionspringproject.service.impl;


import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.model.Category;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.repository.MealRepo;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.service.MealService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.Arrays;
import java.util.Collections;

@Service
public class MealServiceImpl implements MealService {
    private static final Marker MY_MARKER = MarkerManager.getMarker("MyMarker");
    private static final Logger logger = LogManager.getLogger(MealServiceImpl.class);
    CategoryService categoryService;
    MealRepo mealRepo;

    @Autowired
    public MealServiceImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setCategoryServiceImpl(CategoryService categoryService, MealRepo mealRepo) {
        this.categoryService = categoryService;
        this.mealRepo = mealRepo;
    }


    @Override
    public Meal createMeal() {
        Meal meal = new Meal();
        meal.setPrice(1997.0);
        mealRepo.save(meal);
        return meal;
    }

    @Override
    public Meal readMeal(Long id) {
        return mealRepo.findById(id).orElseThrow(() -> new NotFoundException("Meal not found by id " + id));
    }

    @Override
    public void updateMeal(Meal meal) {
        Meal mealNew = mealRepo.findById(meal.getId()).orElseThrow(() -> new NotFoundException("Meal not found by id " + meal.getId()));
        ThreadContext.put("MealBefore", mealNew.toString());
        mealNew.setName(meal.getName());
        mealNew.setPrice(meal.getPrice());
        mealNew.setTags(meal.getTags());
        mealNew.setAmount(meal.getAmount());
        ThreadContext.put("MealAfter", mealNew.toString());

        logger.info(MY_MARKER, "Test log");
        mealRepo.save(mealNew);
        ThreadContext.removeAll(Arrays.asList("MealBefore", "MealAfter"));
    }

    @Override
    public void deleteMeal(Long id) {
        mealRepo.deleteById(id);
    }
}