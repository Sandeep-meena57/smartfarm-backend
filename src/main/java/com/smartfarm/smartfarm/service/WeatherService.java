package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.WeatherData;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    public WeatherData fetchWeatherData(String city) {
        String url = apiUrl + "?key=" + apiKey + "&q=" + city + "&aqi=no";
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(url, String.class);
            JSONObject json = new JSONObject(response);
            JSONObject current = json.getJSONObject("current");
            JSONObject condition = current.getJSONObject("condition");

            return new WeatherData(
                    null,
                    city,
                    current.getDouble("temp_c"),
                    current.getDouble("humidity"),
                    condition.getString("text"),
                    current.getDouble("wind_kph"),
                    LocalDate.now()
            );
        } catch (Exception e) {
            System.err.println("Failed to fetch weather for " + city + ": " + e.getMessage());
            return null;
        }
    }
}
