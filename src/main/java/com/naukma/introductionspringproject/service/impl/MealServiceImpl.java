package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.entity.MealEntity;
import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.repository.MealRepo;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.service.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    private final ModelMapper modelMapper;


    private final CategoryService categoryService;
    private final MealRepo mealRepo;

    @Autowired
    public MealServiceImpl(ModelMapper modelMapper, CategoryService categoryService, MealRepo mealRepo) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.mealRepo = mealRepo;
    }

    @Override
    public Meal createMeal(Meal meal) {
        mealRepo.save(modelMapper.map(meal, MealEntity.class));
        return meal;
    }

    @Override
    public Meal readMeal(Long id) {
        return modelMapper.map(mealRepo.findById(id).orElseThrow(() -> new NotFoundException("Meal not found by id " + id)), Meal.class);
    }

    @Override
    public void updateMeal(Meal meal) {
        Meal mealNew = modelMapper.map(mealRepo.findById(meal.getId()).orElseThrow(() -> new NotFoundException("Meal not found by id " + meal.getId())), Meal.class);

        mealNew.setName(meal.getName());
        mealNew.setPrice(meal.getPrice());
        mealNew.setCategoryId(meal.getCategoryId());
        mealNew.setAmount(meal.getAmount());


        mealRepo.save(modelMapper.map(mealNew, MealEntity.class));

    }

    @Override
    public void deleteMeal(Long id) {
        mealRepo.deleteById(id);
    }

    @Override
    public List<MealEntity> getAllMeals() {
        return mealRepo.findAll();
    }
}