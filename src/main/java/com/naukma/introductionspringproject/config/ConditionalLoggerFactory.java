package com.naukma.introductionspringproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalLoggerFactory {
    @Bean
    @ConditionalOnProperty(name = "logging", havingValue = "true", matchIfMissing = false)
    public Logger logger() {
        return LoggerFactory.getLogger("MyConditionalLogger");
    }
}