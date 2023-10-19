package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.dto.UserDTO;
import com.naukma.introductionspringproject.entity.UserEntity;

public interface UserService {
    UserEntity createUser(UserDTO user);
    UserEntity readUser(Long id);
    void updateUser(UserDTO user);
    void deleteUser(Long id);
}
