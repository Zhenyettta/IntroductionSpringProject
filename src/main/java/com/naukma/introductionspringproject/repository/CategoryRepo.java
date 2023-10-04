package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
