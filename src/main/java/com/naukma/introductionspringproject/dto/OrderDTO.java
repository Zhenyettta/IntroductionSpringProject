package com.naukma.introductionspringproject.dto;

import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private LocalDateTime orderTaken;
    private LocalDateTime orderGiven;
    private List<Long> mealIds;
}
