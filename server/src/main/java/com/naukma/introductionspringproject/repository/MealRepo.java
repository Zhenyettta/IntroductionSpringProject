package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.entity.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepo extends JpaRepository<MealEntity, Long> {

}
