package com.naukma.introductionspringproject.service.impl;

import com.google.gson.*;
import com.naukma.introductionspringproject.config.LoginConfig;
import com.naukma.introductionspringproject.dto.UserDTO;
import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.entity.UserEntity;
import com.naukma.introductionspringproject.repository.UserRepo;
import com.naukma.introductionspringproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserServiceImpl implements UserService {
    private final LoginConfig loginConfig;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private String weatherApiKey = "a167dbd68aa35935eedbe1c995d3b4a7";

    @Autowired
    public UserServiceImpl(LoginConfig loginConfig, UserRepo userRepo, ModelMapper modelMapper) {
        this.loginConfig = loginConfig;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

//    @Override
//    public User createUser(UserDTO user) {
//        userRepo.save(user);
//        return user;
//    }

    @Override
    public UserEntity createUser(UserDTO user) {
        UserEntity user1 = modelMapper.map(user, UserEntity.class);
        RestTemplate restTemplate = new RestTemplate();
        try {
            String resourceUrl = "https://api.open-meteo.com/v1/forecast?latitude=50.450001&longitude=30.523333&hourly=temperature_2m&timezone=auto";
            String responseBody = restTemplate.getForObject(resourceUrl, String.class);
            JsonParser parser = new JsonParser();
            // parse the response
            JsonObject jsonObject = parser.parse(responseBody).getAsJsonObject();

            // get the JSON Arrays for 'time' and 'temperature_2m'
            JsonArray timeArray = jsonObject.get("hourly").getAsJsonObject().get("time").getAsJsonArray();
            JsonArray temperatureArray = jsonObject.get("hourly").getAsJsonObject().get("temperature_2m").getAsJsonArray();

            // assuming 'time' array and 'temperature' array are the same size
            for (int i = 0; i < timeArray.size(); i++) {
                String time = timeArray.get(i).getAsString();
                double temperature = temperatureArray.get(i).getAsDouble();

                System.out.println("Time: " + time + ", Temperature: " + temperature);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRepo.save(user1);
    }

    @Override
    public UserEntity readUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found by id " + id));
    }

    @Override
    public void updateUser(UserDTO user) {
        UserEntity userNew = userRepo.findById(user.getId()).orElseThrow(() -> new NotFoundException("User not found by id " + user.getId()));
        userNew.setFirstName(user.getFirstName());
        userRepo.save(userNew);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}