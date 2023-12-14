package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.entity.CategoryEntity;
import com.naukma.introductionspringproject.entity.MealEntity;
import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.model.Category;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.repository.CategoryRepo;
import com.naukma.introductionspringproject.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper modelMapper;

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepo categoryRepo) {
        this.modelMapper = modelMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category createCategory(Category category) {
        categoryRepo.save(modelMapper.map(category, CategoryEntity.class));
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll().stream()
                .map(this::mapToCategory)
                .collect(Collectors.toList());
    }

    public Category mapToCategory(CategoryEntity categoryEntity) {
        return modelMapper.map(categoryEntity, Category.class);
    }

    @Override
    public Category readCategory(Long id) {
        return modelMapper.map(categoryRepo.findById(id).orElseThrow(() -> new NotFoundException("Category not found by id " + id)), Category.class);
    }

    @Override
    public void updateCategory(Category category) {
        CategoryEntity categoryNew = categoryRepo.findById(category.getId()).orElseThrow(() -> new NotFoundException("Category not found by id " + category.getId()));
        categoryNew.setName(category.getName());
        categoryRepo.save(categoryNew);
    }

    @Override
    public void deleteCategory(Long id) {
        if (categoryRepo.existsById(id))
            categoryRepo.deleteById(id);
        else throw new NotFoundException("Category not found by id " + id);    }

}
