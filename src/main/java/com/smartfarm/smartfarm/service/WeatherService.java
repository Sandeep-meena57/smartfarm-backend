package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.WeatherData;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public WeatherData fetchWeatherData(double lat, double lon) {
        String url = apiUrl + "?key=" + apiKey + "&q=" + lat + "," + lon + "&aqi=no";
        try {
            String response = restTemplate.getForObject(url, String.class);
            JSONObject json = new JSONObject(response);
            JSONObject location = json.getJSONObject("location");
            JSONObject current = json.getJSONObject("current");
            JSONObject condition = current.getJSONObject("condition");

            WeatherData data = new WeatherData();
            data.setLocation(location.getString("name"));
            data.setTemperature(current.getDouble("temp_c"));
            data.setHumidity(current.getInt("humidity"));
            data.setCondition(condition.getString("text"));
            data.setWindSpeed(current.getDouble("wind_kph"));
            data.setDate(LocalDate.now());
            return data;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch weather data: " + e.getMessage(), e);
        }
    }
}