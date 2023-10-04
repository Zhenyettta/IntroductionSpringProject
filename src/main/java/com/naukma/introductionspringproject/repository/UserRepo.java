package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
