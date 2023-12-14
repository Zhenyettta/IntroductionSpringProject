package com.naukma.introductionspringproject.controller;


import com.naukma.introductionspringproject.entity.MealEntity;
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
