package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.config.HttpStatuses;
import com.naukma.introductionspringproject.dto.MealDTO;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.service.MealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meals")
@Validated
@io.swagger.v3.oas.annotations.tags.Tag(name = "Meal Management", description = "Operations pertaining to meal in Meal Management")
public class MealController {
    private final ModelMapper modelMapper;
    private final MealService mealService;

    public MealController(ModelMapper modelMapper, MealService mealService) {
        this.modelMapper = modelMapper;
        this.mealService = mealService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a meal by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> getById(@Parameter(description = "Id value for the meal you need to retrieve") @PathVariable Long id){
        return new ResponseEntity<>(mealService.readMeal(id), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update a meal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> updateMeal(@Parameter(description = "Update meal object") @Valid @RequestBody MealDTO mealDTO){
        mealService.updateMeal(modelMapper.map(mealDTO, Meal.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a meal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "409", description = HttpStatuses.CONFLICT)
    })
    public ResponseEntity<Object> createMeal(@Parameter(description = "Create meal object") @Valid @RequestBody MealDTO mealDTO){
        mealService.createMeal(modelMapper.map(mealDTO, Meal.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a meal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> deleteMeal(@Parameter(description = "Id value for the meal you want to delete") @PathVariable Long id){
        mealService.deleteMeal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

