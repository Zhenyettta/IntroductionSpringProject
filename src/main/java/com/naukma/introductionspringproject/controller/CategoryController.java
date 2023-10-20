package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.dto.CategoryDTO;
import com.naukma.introductionspringproject.dto.UserDTO;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/category")
@Validated
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.readCategory(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> updateCategory(@Valid @RequestBody CategoryDTO category){
        categoryService.updateCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody CategoryDTO category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
