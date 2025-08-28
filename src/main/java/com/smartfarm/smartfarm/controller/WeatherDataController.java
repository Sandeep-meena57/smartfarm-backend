package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.WeatherData;
import com.smartfarm.smartfarm.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@Tag(name = "Weather API")
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherDataController {

    private final WeatherService weatherService;

    @Operation(summary = "Get live weather data by latitude and longitude")
    @GetMapping("/live")
    public ResponseEntity<WeatherData> getLiveWeather(
            @RequestParam double lat,
            @RequestParam double lon) {

        return ResponseEntity.ok(
                weatherService.fetchWeatherData(lat, lon)
        );
    }
}

