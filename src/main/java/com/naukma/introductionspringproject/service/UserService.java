package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.dto.UserDTO;
import com.naukma.introductionspringproject.model.User;

public interface UserService {
    User createUser(UserDTO user);
    User readUser(Long id);
    void updateUser(UserDTO user);
    void deleteUser(Long id);
}
