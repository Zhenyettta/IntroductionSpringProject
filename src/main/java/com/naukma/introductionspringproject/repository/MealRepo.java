package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepo extends JpaRepository<Meal, Long> {
}
