package com.naukma.introductionspringproject.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.naukma.introductionspringproject.config.LoginConfig;
import com.naukma.introductionspringproject.entity.UserEntity;
import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.exception.UniqueEmailException;
import com.naukma.introductionspringproject.model.User;
import com.naukma.introductionspringproject.repository.UserRepo;
import com.naukma.introductionspringproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl( UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {

        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        validateEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(modelMapper.map(user, UserEntity.class));
        return user;
    }

    @Override
    public User readUser(Long id) {
        return modelMapper.map(userRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found by id " + id)), User.class);
    }



    @Override
    public void updateUser(User user) {
        UserEntity userNew = userRepo.findById(user.getId()).orElseThrow(() -> new NotFoundException("User not found by id " + user.getId()));
        userNew.setFirstName(user.getFirstName());
        userNew.setLastName(user.getLastName());
        userNew.setPhoneNumber(user.getPhoneNumber());
        userNew.setEmail(user.getEmail());
        userNew.setPassword(user.getPassword());
        userRepo.save(userNew);
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepo.existsById(id))
            userRepo.deleteById(id);
        else throw new NotFoundException("User not found by id " + id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    private void validateEmail(String email) {
        if (userRepo.findByEmail(email).isPresent())
            throw new UniqueEmailException("The email address " + email + " is already in use");
    }
}