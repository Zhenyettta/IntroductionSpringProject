package com.naukma.introductionspringproject.dto;

import com.naukma.introductionspringproject.model.Meal;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class TagDTO {
    private Long id;
    private String name;
    private List<Long> mealIds;
}
