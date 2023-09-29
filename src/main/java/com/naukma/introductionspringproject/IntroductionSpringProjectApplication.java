package com.naukma.introductionspringproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;



//@ComponentScan(basePackages = "com.naukma")
@SpringBootApplication
@Import(com.naukma.springboot.DiscountUtilConfig.class)
public class IntroductionSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroductionSpringProjectApplication.class, args);
	}

}
