package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.WeatherData;
import com.smartfarm.smartfarm.repositories.WeatherDataRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherDataService {

    private final WeatherDataRepo weatherDataRepo;
    private final WeatherService weatherService;

    public WeatherData saveData(WeatherData data){
        return weatherDataRepo.save(data);
    }

    public WeatherData getById(Long id){
        return weatherDataRepo.findById(id).orElseThrow(() -> new RuntimeException("Weather data not found"));
    }

    public List<WeatherData> getByLocation(String location){
        return weatherDataRepo.findByLocationOrderByDateDesc(location);
    }

    public List<WeatherData> getAllData(){
        return weatherDataRepo.findAll();
    }

    @Scheduled(cron = "0 0 6 * * *") // every day at 6 AM
    public void autoFetchWeatherData() {
        List<String> locations = Arrays.asList("Delhi", "Jaipur", "Indore","Bhopal");

        for (String location : locations) {
            WeatherData data = weatherService.fetchWeatherData(location);
            if (data != null) {
                weatherDataRepo.save(data);
                System.out.println("Saved weather for: " + location);
            }
        }
    }
}
