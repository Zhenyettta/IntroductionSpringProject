package com.naukma.introductionspringproject.dto;

import com.naukma.introductionspringproject.model.Meal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class CategoryDTO {
    private Long Id;
    private String name;
    private List<Long> mealIds;
}
