package com.naukma.introductionspringproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class MealDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    private String name;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive number")
    private Double price;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be a positive number")
    private Integer amount;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

    @NotNull(message = "Order IDs cannot be null")
    private List<Long> orderIds;

    @NotNull(message = "Tag IDs cannot be null")
    private List<Long> tagIds;


}
