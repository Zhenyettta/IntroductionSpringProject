package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.entity.CategoryEntity;
import com.naukma.introductionspringproject.entity.MealEntity;
import com.naukma.introductionspringproject.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category readCategory(Long id);
    void updateCategory(Category category);
    void deleteCategory(Long id);

}
