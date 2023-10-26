package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.config.HttpStatuses;
import com.naukma.introductionspringproject.dto.CategoryDTO;
import com.naukma.introductionspringproject.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Validated
@io.swagger.v3.oas.annotations.tags.Tag(name = "Category Management", description = "Operations pertaining to category in Category Management")
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> getById(@Parameter(description = "Id value for the category you need to retrieve") @PathVariable Long id) {
        return new ResponseEntity<>(categoryService.readCategory(id), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> updateCategory(@Parameter(description = "Update category object") @Valid @RequestBody CategoryDTO category) {
        categoryService.updateCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "409", description = HttpStatuses.CONFLICT)
    })
    public ResponseEntity<Object> createCategory(@Parameter(description = "Create category object") @Valid @RequestBody CategoryDTO category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> deleteCategory(@Parameter(description = "Id value for the category you want to delete") @PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
