package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
}
