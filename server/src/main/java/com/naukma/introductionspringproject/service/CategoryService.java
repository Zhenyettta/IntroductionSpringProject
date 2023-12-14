package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);

    Category readCategory(Long id);

    void updateCategory(Category category);

    void deleteCategory(Long id);

    List<Category> getAllCategories();

}
