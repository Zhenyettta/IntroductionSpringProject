package com.naukma.introductionspringproject.model;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    private Long Id;
    private String name;
    private List<Long> mealIds;
}
