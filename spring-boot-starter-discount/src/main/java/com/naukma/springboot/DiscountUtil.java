package com.naukma.springboot;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountUtil {
    private final DiscountUtilConfig config;
    public DiscountUtil(DiscountUtilConfig config) {
        this.config = config;
    }

    public String createDiscount() {
        return config.getProperties().getBirthday() + " asd";
    }
}
