package com.naukma.introductionspringproject.service.impl;


import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.entity.MealEntity;
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

@Service
public class MealServiceImpl implements MealService {
    private static final Marker MY_MARKER = MarkerManager.getMarker("MyMarker");
    private static final Logger logger = LogManager.getLogger(MealServiceImpl.class);
    CategoryService categoryService;
    MealRepo mealRepo;

    @Autowired
    public MealServiceImpl(CategoryService categoryService, MealRepo mealRepo) {
        this.categoryService = categoryService;
        this.mealRepo = mealRepo;
    }



    @Override
    public MealEntity createMeal() {
        MealEntity meal = new MealEntity();
        meal.setPrice(1997.0);
        mealRepo.save(meal);
        return meal;
    }

    @Override
    public MealEntity readMeal(Long id) {
        return mealRepo.findById(id).orElseThrow(() -> new NotFoundException("Meal not found by id " + id));
    }

    @Override
    public void updateMeal(MealEntity meal) {
        MealEntity mealNew = mealRepo.findById(meal.getId()).orElseThrow(() -> new NotFoundException("Meal not found by id " + meal.getId()));
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