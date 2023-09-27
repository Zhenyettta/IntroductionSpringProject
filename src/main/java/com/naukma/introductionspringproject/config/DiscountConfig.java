package com.naukma.introductionspringproject.config;

import com.naukma.introductionspringproject.util.DiscountProperties;
import com.naukma.introductionspringproject.util.DiscountUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConditionalOnClass(DiscountUtil.class)
@EnableConfigurationProperties(DiscountProperties.class)
public class DiscountConfig {
    private final DiscountProperties properties;

    public DiscountConfig(DiscountProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public DiscountUtil discountManager() {
        return new DiscountUtil(this);
    }
}
