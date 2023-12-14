package com.naukma.introductionspringproject.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.naukma.introductionspringproject.service.ForecastService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForecastServiceImpl implements ForecastService {
    @Override
    public void getForecast() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String resourceUrl = "https://api.open-meteo.com/v1/forecast?latitude=50.450001&longitude=30.523333&hourly=temperature_2m&timezone=auto";
            String responseBody = restTemplate.getForObject(resourceUrl, String.class);
            assert responseBody != null;
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonArray timeArray = jsonObject.get("hourly").getAsJsonObject().get("time").getAsJsonArray();
            JsonArray temperatureArray = jsonObject.get("hourly").getAsJsonObject().get("temperature_2m").getAsJsonArray();
            for (int i = 0; i < timeArray.size(); i++) {
                String time = timeArray.get(i).getAsString();
                double temperature = temperatureArray.get(i).getAsDouble();
                System.out.println("Time: " + time + ", Temperature: " + temperature);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
