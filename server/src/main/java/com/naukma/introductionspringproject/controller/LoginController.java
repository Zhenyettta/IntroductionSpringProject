package com.naukma.introductionspringproject.controller;


import com.naukma.introductionspringproject.dto.CategoryDTO;
import com.naukma.introductionspringproject.dto.MealDTO;
import com.naukma.introductionspringproject.entity.CategoryEntity;
import com.naukma.introductionspringproject.entity.MealEntity;
import com.naukma.introductionspringproject.model.Category;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.service.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowCredentials = "true", allowedHeaders = "*")
public class LoginController {
    private final MealService mealService;
    private final CategoryService categoryService;

    public LoginController(MealService mealService, CategoryService categoryService) {
        this.mealService = mealService;
        this.categoryService = categoryService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping("/")
    public String home() {
        return "home";
    }
}
