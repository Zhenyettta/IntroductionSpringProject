package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.entity.UserEntity;
import com.naukma.introductionspringproject.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User readUser(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    List<UserEntity> getAllUsers();
}
