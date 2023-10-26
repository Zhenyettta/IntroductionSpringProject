package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.dto.CategoryDTO;
import com.naukma.introductionspringproject.service.CategoryService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Validated
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@Parameter(description = "id of book to be searched") @PathVariable Long id) {
        return new ResponseEntity<>(categoryService.readCategory(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateCategory(@Valid @RequestBody CategoryDTO category) {
        categoryService.updateCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryDTO category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
