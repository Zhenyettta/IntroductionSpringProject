package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.dto.MealDTO;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.service.MealService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meals")
@Validated
public class MealController {
    private final ModelMapper modelMapper;
    private final MealService mealService;

    public MealController(ModelMapper modelMapper, MealService mealService) {
        this.modelMapper = modelMapper;
        this.mealService = mealService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return new ResponseEntity<>(mealService.readMeal(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> updateMeal(@Valid @RequestBody MealDTO mealDTO){
        mealService.updateMeal(modelMapper.map(mealDTO, Meal.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Object> createMeal(@Valid @RequestBody MealDTO mealDTO){
        mealService.createMeal(modelMapper.map(mealDTO, Meal.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMeal(@PathVariable Long id){
        mealService.deleteMeal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

