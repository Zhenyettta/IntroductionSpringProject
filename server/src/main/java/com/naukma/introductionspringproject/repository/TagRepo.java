package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<TagEntity, Long> {
}
