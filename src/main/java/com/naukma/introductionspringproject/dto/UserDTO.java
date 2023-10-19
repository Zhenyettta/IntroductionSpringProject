package com.naukma.introductionspringproject.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long Id;
    private String firstName;
    private String lastName;
    @Email(message = "Must be a valid e-mail address")
    private String email;
    private String phoneNumber;
    private String password;
    private List<Long> orderIds;
}