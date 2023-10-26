package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.entity.MealEntity;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.repository.MealRepo;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.service.MealService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.Arrays;

@Service
public class MealServiceImpl implements MealService {
    private final ModelMapper modelMapper;

    private static final Marker MY_MARKER = MarkerManager.getMarker("MyMarker");
    private static final Logger logger = LogManager.getLogger(MealServiceImpl.class);
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
        ThreadContext.put("MealBefore", mealNew.toString());
        mealNew.setName(meal.getName());
        mealNew.setPrice(meal.getPrice());
        mealNew.setCategoryId(meal.getCategoryId());
        mealNew.setAmount(meal.getAmount());
        ThreadContext.put("MealAfter", mealNew.toString());

        logger.info(MY_MARKER, "Test log");
        mealRepo.save(modelMapper.map(mealNew, MealEntity.class));
        ThreadContext.removeAll(Arrays.asList("MealBefore", "MealAfter"));
    }

    @Override
    public void deleteMeal(Long id) {
        mealRepo.deleteById(id);
    }
}