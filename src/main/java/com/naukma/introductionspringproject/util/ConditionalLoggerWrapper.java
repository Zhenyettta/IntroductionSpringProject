package com.naukma.introductionspringproject.util;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConditionalLoggerWrapper {

    private Logger logger;

    @Autowired(required = false)
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void warn(String message) {
        if (logger != null) {
            logger.warn(message);
        }
    }

    public void info(String message) {
        if (logger != null) {
            logger.info(message);
        }
    }

    public void error(String message) {
        if (logger != null) {
            logger.error(message);
        }
    }


}