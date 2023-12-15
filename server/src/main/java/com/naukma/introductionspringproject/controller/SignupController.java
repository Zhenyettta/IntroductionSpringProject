package com.naukma.introductionspringproject.controller;


import com.naukma.introductionspringproject.config.HttpStatuses;
import com.naukma.introductionspringproject.entity.UserEntity;
import com.naukma.introductionspringproject.model.User;
import com.naukma.introductionspringproject.service.UserService;
import com.naukma.introductionspringproject.util.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public SignupController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/signup")
    public String getSignUpPage(Model model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("allRoles", Role.values());
        return "signup";
    }

    @GetMapping("/createUserForm")
    @Operation(summary = "Create user form")
    public String createUserForm(Model model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("allRoles", Role.values());
        return "signup";
    }

    @PostMapping()
    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "409", description = HttpStatuses.CONFLICT)
    })
    public String createUser(@ModelAttribute @Valid UserEntity user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        userService.createUser(modelMapper.map(user, User.class));
        return "redirect:/login";
    }
}
