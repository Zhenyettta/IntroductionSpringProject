package com.naukma.introductionspringproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private Long id;
    private String name;
    private List<Long> mealIds;
}
