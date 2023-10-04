package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepo extends JpaRepository<User, Long> {
}
