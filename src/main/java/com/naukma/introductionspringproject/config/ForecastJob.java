package com.naukma.introductionspringproject.config;

import com.naukma.introductionspringproject.service.ForecastService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForecastJob implements Job {
    private final ForecastService forecastService;

    public ForecastJob(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        forecastService.getForecast();
    }
}