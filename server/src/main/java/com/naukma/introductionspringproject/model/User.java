package com.naukma.introductionspringproject.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private List<Long> orderIds;
}