package com.naukma.introductionspringproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long Id;
    private String name;
    private List<Long> mealIds;
}
