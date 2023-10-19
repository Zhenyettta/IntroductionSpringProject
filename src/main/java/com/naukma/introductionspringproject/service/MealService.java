package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.entity.MealEntity;

public interface MealService {
    MealEntity createMeal();

    MealEntity readMeal(Long id);

    void updateMeal(MealEntity meal);

    void deleteMeal(Long id);

}
