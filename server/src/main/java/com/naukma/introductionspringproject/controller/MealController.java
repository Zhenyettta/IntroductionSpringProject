package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.config.HttpStatuses;
import com.naukma.introductionspringproject.dto.MealDTO;
import com.naukma.introductionspringproject.entity.MealEntity;
import com.naukma.introductionspringproject.entity.UserEntity;
import com.naukma.introductionspringproject.model.Category;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.model.User;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.service.MealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/meals")
@Validated
@io.swagger.v3.oas.annotations.tags.Tag(name = "Meal Management", description = "Operations pertaining to meal in Meal Management")
public class MealController {
    private final ModelMapper modelMapper;
    private final MealService mealService;

    private final CategoryService categoryService;

    public MealController(ModelMapper modelMapper, MealService mealService, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.mealService = mealService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a meal by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> getById(@Parameter(description = "Id value for the meal you need to retrieve") @PathVariable Long id) {
        return new ResponseEntity<>(mealService.readMeal(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all meals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<List<Meal>> getAllMeals() {
        List<Meal> meals = mealService.getAllMeals();
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update a meal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> updateMeal(@Parameter(description = "Update meal object") @Valid @RequestBody MealDTO mealDTO) {
        mealService.updateMeal(modelMapper.map(mealDTO, Meal.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a meal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "409", description = HttpStatuses.CONFLICT)
    })
    public String createMeal(@Parameter(description = "Create meal object") @Valid @ModelAttribute MealDTO mealDTO,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-meal";
        }
        mealService.createMeal(modelMapper.map(mealDTO, Meal.class));
        return "redirect:/";
    }


    @GetMapping("/createMealForm")
    public String createMealForm(Model model) {
        model.addAttribute("meal", new Meal());
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "create-meal";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a meal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> deleteMeal(@Parameter(description = "Id value for the meal you want to delete") @PathVariable Long id) {
        mealService.deleteMeal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

