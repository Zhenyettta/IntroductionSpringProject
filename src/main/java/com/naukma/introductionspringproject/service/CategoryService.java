package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.dto.CategoryDTO;
import com.naukma.introductionspringproject.entity.CategoryEntity;

public interface CategoryService {
    CategoryEntity createCategory(CategoryDTO category);
    CategoryEntity readCategory(Long id);
    void updateCategory(CategoryDTO category);
    void deleteCategory(Long id);
}
