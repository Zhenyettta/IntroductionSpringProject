package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
}
