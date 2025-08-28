package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.WeatherData;
import com.smartfarm.smartfarm.repositories.WeatherDataRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
        return weatherDataRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Weather data not found"));
    }

    public List<WeatherData> getByLocation(String location){
        return weatherDataRepo.findByLocationOrderByDateDesc(location);
    }

    public List<WeatherData> getAllData(){
        return weatherDataRepo.findAll();
    }

    @Scheduled(cron = "0 0 6 * * *") // every day at 6 AM
    public void autoFetchWeatherData() {

        List<LocationPoint> locations = List.of(
                new LocationPoint("Delhi", 28.6139, 77.2090),
                new LocationPoint("Jaipur", 26.9124, 75.7873),
                new LocationPoint("Indore", 22.7196, 75.8577),
                new LocationPoint("Bhopal", 23.2599, 77.4126)
        );

        for (LocationPoint loc : locations) {
            WeatherData data =
                    weatherService.fetchWeatherData(loc.getLat(), loc.getLon());

            if (data != null) {
                weatherDataRepo.save(data);
                System.out.println("Saved weather for: " + loc.getName());
            }
        }
    }
}
