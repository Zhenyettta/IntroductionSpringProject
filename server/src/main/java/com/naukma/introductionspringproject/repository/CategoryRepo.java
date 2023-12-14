package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
}
