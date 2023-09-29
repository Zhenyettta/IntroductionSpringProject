package com.naukma.springboot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "discount")
@Getter
@Setter
public class DiscountUtilProperties {
    private double birthday;
    private double newYear;
}

