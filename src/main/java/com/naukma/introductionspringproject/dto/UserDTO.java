package com.naukma.introductionspringproject.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {

    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Long> orderIds;

}