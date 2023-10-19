package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.entity.CategoryEntity;

public interface CategoryService {
    CategoryEntity createCategory();
    CategoryEntity readCategory(Long id);
    void updateCategory(CategoryEntity category);
    void deleteCategory(Long id);
}
