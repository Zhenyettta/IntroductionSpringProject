package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
   Optional<UserEntity> findByEmail(String email);
}
