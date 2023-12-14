package com.naukma.introductionspringproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long Id;

    @NotBlank
    @Size(min = 2, max = 255)
    private String name;

    @NotNull
    private List<Long> mealIds;

}
