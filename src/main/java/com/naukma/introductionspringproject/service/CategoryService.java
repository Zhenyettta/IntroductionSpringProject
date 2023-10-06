package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.model.Category;
import com.naukma.introductionspringproject.model.Meal;

public interface CategoryService {
    Category createCategory();
    Category readCategory(Long id);
    void updateCategory(Category category);
    void deleteCategory(Long id);
}
