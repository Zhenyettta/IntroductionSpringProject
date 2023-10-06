package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.model.Category;
import com.naukma.introductionspringproject.model.Order;
import com.naukma.introductionspringproject.repository.CategoryRepo;
import com.naukma.introductionspringproject.repository.OrderRepo;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryServiceImpl implements CategoryService {
    MealService mealService;
    CategoryRepo categoryRepo;

    @Autowired
    public void setCategoryServiceImpl(MealService mealService, CategoryRepo categoryRepo) {
        this.mealService = mealService;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category createCategory() {
        Category category = new Category();
        category.setName("Category1");
        categoryRepo.save(category);
        return category;
    }

    @Override
    public Category readCategory(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new NotFoundException("Category not found by id " + id));
    }

    @Override
    public void updateCategory(Category category) {
        Category categoryNew = categoryRepo.findById(category.getId()).orElseThrow(() -> new NotFoundException("Category not found by id " + category.getId()));
        categoryNew.setMeals(category.getMeals());
        categoryNew.setName(category.getName());
        categoryRepo.save(categoryNew);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

}
