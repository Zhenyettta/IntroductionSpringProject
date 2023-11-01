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

    public void getForecast() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String resourceUrl = "https://api.open-meteo.com/v1/forecast?latitude=50.450001&longitude=30.523333&hourly=temperature_2m&timezone=auto";
            String responseBody = restTemplate.getForObject(resourceUrl, String.class);
            JsonParser parser = new JsonParser();
            assert responseBody != null;
            JsonObject jsonObject = parser.parse(responseBody).getAsJsonObject();
            JsonArray timeArray = jsonObject.get("hourly").getAsJsonObject().get("time").getAsJsonArray();
            JsonArray temperatureArray = jsonObject.get("hourly").getAsJsonObject().get("temperature_2m").getAsJsonArray();
            for (int i = 0; i < timeArray.size(); i++) {
                String time = timeArray.get(i).getAsString();
                double temperature = temperatureArray.get(i).getAsDouble();
                System.out.println("Time: " + time + ", Temperature: " + temperature);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void validateEmail(String email) {
        if (userRepo.findByEmail(email).isPresent())
            throw new UniqueEmailException("The email address " + email + " is already in use");
    }
}