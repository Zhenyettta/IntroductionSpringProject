package com.naukma.introductionspringproject;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;




//@ComponentScan(basePackages = "com.naukma")
@SpringBootApplication
@Import(com.naukma.springboot.DiscountUtilConfig.class)
public class IntroductionSpringProjectApplication {
	private static final Logger logger = LogManager.getLogger(IntroductionSpringProjectApplication.class);
	public static void main(String[] args) {
		logger.log(Level.WARN, "asd");
		SpringApplication.run(IntroductionSpringProjectApplication.class, args);
	}

}
