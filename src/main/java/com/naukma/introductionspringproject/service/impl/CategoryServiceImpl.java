package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.entity.CategoryEntity;
import com.naukma.introductionspringproject.repository.CategoryRepo;
import com.naukma.introductionspringproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepo categoryRepo;

    @Autowired
    public void setCategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryEntity createCategory() {
        CategoryEntity category = new CategoryEntity();
        category.setName("Category1");
        categoryRepo.save(category);
        return category;
    }

    @Override
    public CategoryEntity readCategory(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new NotFoundException("Category not found by id " + id));
    }

    @Override
    public void updateCategory(CategoryEntity category) {
        CategoryEntity categoryNew = categoryRepo.findById(category.getId()).orElseThrow(() -> new NotFoundException("Category not found by id " + category.getId()));
        categoryNew.setMeals(category.getMeals());
        categoryNew.setName(category.getName());
        categoryRepo.save(categoryNew);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

}
