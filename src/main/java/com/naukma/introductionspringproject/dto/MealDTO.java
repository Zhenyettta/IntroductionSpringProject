package com.naukma.introductionspringproject.dto;

import com.naukma.introductionspringproject.model.Category;
import com.naukma.introductionspringproject.model.Order;
import com.naukma.introductionspringproject.model.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class MealDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer amount;
    private Long categoryId;
    private List<Long> orderIds;
    private List<Long> tagIds;
}
