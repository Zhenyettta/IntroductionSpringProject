package com.naukma.introductionspringproject.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Long userId;
    private LocalDateTime orderTaken;
    private LocalDateTime orderGiven;
    private List<Long> mealIds;
}
