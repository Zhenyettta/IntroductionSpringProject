package com.naukma.introductionspringproject.service.impl;


import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.repository.MealRepo;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService {
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
        mealNew.setName(meal.getName());
        mealNew.setPrice(meal.getPrice());
        mealNew.setTags(meal.getTags());
        mealNew.setAmount(meal.getAmount());
        meal.setCategory(meal.getCategory());
        meal.setOrders(meal.getOrders());
        mealRepo.save(mealNew);
    }

    @Override
    public void deleteMeal(Long id) {
        mealRepo.deleteById(id);
    }
}