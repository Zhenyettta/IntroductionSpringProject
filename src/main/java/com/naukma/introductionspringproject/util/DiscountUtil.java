package com.naukma.introductionspringproject.util;

import com.naukma.introductionspringproject.config.DiscountConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(DiscountProperties.class)
public class DiscountUtil {
    private final DiscountConfig config;

    public DiscountUtil(DiscountConfig config) {
        this.config = config;
    }

    public String createDiscount(){
        return config.getProperties().getBirthday()+" asd";
    }
}
