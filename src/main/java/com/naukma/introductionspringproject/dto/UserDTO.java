package com.naukma.introductionspringproject.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
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