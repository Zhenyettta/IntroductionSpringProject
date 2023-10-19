package com.naukma.introductionspringproject.model;

import lombok.Data;

import java.util.List;

@Data
public class Tag {
    private Long id;
    private String name;
    private List<Long> mealIds;
}
