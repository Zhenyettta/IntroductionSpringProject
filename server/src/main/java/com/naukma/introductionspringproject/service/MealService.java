package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.entity.MealEntity;
import com.naukma.introductionspringproject.model.Meal;

import java.util.List;

public interface MealService {
    Meal createMeal(Meal meal);

    Meal readMeal(Long id);

    void updateMeal(Meal meal);

    void deleteMeal(Long id);

    List<MealEntity> getAllMeals();
}
