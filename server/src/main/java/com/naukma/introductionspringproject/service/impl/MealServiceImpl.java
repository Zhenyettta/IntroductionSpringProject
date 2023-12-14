package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.entity.MealEntity;
import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.repository.MealRepo;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.service.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CachePut(value = "mealCache", key = "#result.id")
    public Meal createMeal(Meal meal) {
        MealEntity savedEntity = mealRepo.save(modelMapper.map(meal, MealEntity.class));
        return modelMapper.map(savedEntity, Meal.class);
    }

    @Override
    @Cacheable(value = "mealCache", key = "#id")
    public Meal readMeal(Long id) {
        return modelMapper.map(mealRepo.findById(id).orElseThrow(() -> new NotFoundException("Meal not found by id " + id)), Meal.class);
    }

    @Override
    @CachePut(value = "mealCache", key = "#meal.id")
    public void updateMeal(Meal meal) {
        Meal mealNew = modelMapper.map(mealRepo.findById(meal.getId()).orElseThrow(() -> new NotFoundException("Meal not found by id " + meal.getId())), Meal.class);

        mealNew.setName(meal.getName());
        mealNew.setPrice(meal.getPrice());
        mealNew.setCategoryId(meal.getCategoryId());
        mealNew.setAmount(meal.getAmount());


        mealRepo.save(modelMapper.map(mealNew, MealEntity.class));

        clearMealCache(meal.getId());
    }

    @Override
    @CacheEvict(value = "mealCache", key = "#id")
    public void deleteMeal(Long id) {
        mealRepo.deleteById(id);
    }

    @Override
    public List<MealEntity> getAllMeals() {
        return mealRepo.findAll();
    }

    // Очистити кеш для конкретного id
    @CacheEvict(value = "mealCache", key = "#id")
    public void clearMealCache(Long id) {
    }

    // Очистити весь кеш mealCache
    @CacheEvict(value = "mealCache", allEntries = true)
    public void clearAllMealCache() {
    }
}