package com.naukma.introductionspringproject.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @AfterThrowing(
            pointcut = "execution(* com.naukma.introductionspringproject..*.*(..))" +
                    "&& !execution(* com.naukma.introductionspringproject.util..*.*(..))",
            throwing = "ex")
    public void logException(Exception ex) {
        logger.info("Exception occured: " + ex);
    }
}