package com.naukma.introductionspringproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {

    @Bean
    public LoginConfig getLoginConfig(){
        return new LoginConfig();
    }

    public String encodePassword(String password) {
        return password + " encoded";
    }

}
