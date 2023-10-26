package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.model.User;

public interface UserService {
    User createUser(User user);
    User readUser(Long id);
    void updateUser(User user);
    void deleteUser(Long id);
}
