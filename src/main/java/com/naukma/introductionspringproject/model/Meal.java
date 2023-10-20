package com.naukma.introductionspringproject.model;

import lombok.Data;

import java.util.List;

@Data
public class Meal {
    private Long id;
    private String name;
    private Double price;
    private Integer amount;
    private Long categoryId;
    private List<Long> orderIds;
    private List<Long> tagIds;
}
