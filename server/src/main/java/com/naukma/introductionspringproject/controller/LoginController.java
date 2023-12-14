package com.naukma.introductionspringproject.controller;


import com.naukma.introductionspringproject.entity.MealEntity;
import com.naukma.introductionspringproject.service.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class LoginController {
    private final MealService mealService;

    public LoginController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping("/")
    public String home(Model model) {
        List<MealEntity> meals = mealService.getAllMeals();
        model.addAttribute("meals", meals);
        return "home";
    }
}
