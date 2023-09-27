package com.naukma.introductionspringproject.util;

import com.naukma.introductionspringproject.model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;


@ConditionalOnClass(DiscountUtil.class)
@EnableConfigurationProperties(DiscountProperties.class)
public class DiscountUtil {
    private final DiscountConfig config;

    public DiscountUtil(DiscountConfig config) {
        this.config = config;
    }

    public void createDiscount(User user){

    }
}
