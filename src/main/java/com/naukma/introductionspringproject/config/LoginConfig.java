package com.naukma.introductionspringproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {

    @Bean
    LoginConfig getLoginConfig(){
        return new LoginConfig();
    }

    public String encodePasword(String password) {
        return password + " encoded";
    }

}
