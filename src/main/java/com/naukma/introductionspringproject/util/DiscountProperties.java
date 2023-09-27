package com.naukma.introductionspringproject.util;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "discount")
@Getter
@Setter
public class DiscountProperties {
    private double birthday;
    private double newYear;
}
