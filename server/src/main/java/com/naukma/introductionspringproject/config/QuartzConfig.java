package com.naukma.introductionspringproject.config;

import com.naukma.introductionspringproject.config.ForecastJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail forecastJobDetail() {
        return JobBuilder.newJob(ForecastJob.class)
                .withIdentity("forecastJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger forecastJobCronTrigger(JobDetail forecastJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(forecastJobDetail)
                .withIdentity("forecastCronTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 * * * ?")) // Кожні 30хв
                .build();
    }

    @Bean
    public Trigger forecastJobFixedRateTrigger(JobDetail forecastJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(forecastJobDetail)
                .withIdentity("forecastFixedRateTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(1) // Кожну годину
                        .repeatForever())
                .build();
    }
}
