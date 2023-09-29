package com.naukma.springboot;

import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConditionalOnClass(DiscountUtil.class)
@EnableConfigurationProperties(DiscountUtilProperties.class)
public class DiscountUtilConfig {
    private final DiscountUtilProperties properties;

    public DiscountUtilConfig(DiscountUtilProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public DiscountUtil getDiscountUtil() {
        return new DiscountUtil(this);
    }
}


