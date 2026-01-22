package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.Recommendation;
import com.smartfarm.smartfarm.entity.WeatherData;
import com.smartfarm.smartfarm.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
@Tag(name = "Crop Recommendations API")
@CrossOrigin(origins = "http://localhost:5173")
public class RecommendationController {

    private final WeatherDataService weatherDataService;
    private final WeatherService weatherService;
    private final RecommendationService recommendationService;
    private final NotificationService notificationService;

    private static final Map<String, LocationPoint> locationMap = new HashMap<>();
    static {
        locationMap.put("Delhi", new LocationPoint("Delhi", 28.6139, 77.2090));
        locationMap.put("Jaipur", new LocationPoint("Jaipur", 26.9124, 75.7873));
        locationMap.put("Indore", new LocationPoint("Indore", 22.7196, 75.8577));
        locationMap.put("Bhopal", new LocationPoint("Bhopal", 23.2599, 77.4126));
    }

    @Operation(summary = "Get crop recommendations by city name")
    @GetMapping("/by-city")
    public ResponseEntity<?> getRecommendations(@RequestParam String city) {
        // Fetch stored weather data
        var weatherList = weatherDataService.getByLocation(city);
        var weatherData = weatherList.isEmpty() ? null : weatherList.get(0);

        // Fetch live if not stored
        if (weatherData == null && locationMap.containsKey(city)) {
            LocationPoint loc = locationMap.get(city);
            try {
                weatherData = weatherService.fetchWeatherData(loc.getLat(), loc.getLon());
                weatherDataService.saveData(weatherData);
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Failed to fetch weather: " + e.getMessage());
            }
        }

        if (weatherData == null) return ResponseEntity.badRequest().body("Weather not available for city: " + city);

        // Generate recommendations
        List<Recommendation> recommendations = recommendationService.generate(weatherData, "GenericCrop");

        // Send notifications via WebSocket
        recommendations.forEach(rec -> notificationService.createNotification(rec, "GenericCrop", city));

        Map<String, Object> response = new HashMap<>();
        response.put("city", city);
        response.put("weather", weatherData);
        response.put("recommendations", recommendations);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-location")
    @Operation(summary = "Get crop recommendation by latitude and longitude")
    public ResponseEntity<?> getRecommendationByLocation(@RequestParam double lat, @RequestParam double lon) {
        try {
            WeatherData weather = weatherService.fetchWeatherData(lat, lon);

            List<Recommendation> recommendations = recommendationService.generate(weather, "GenericCrop");

            recommendations.forEach(rec -> notificationService.createNotification(rec, "GenericCrop", weather.getLocation()));

            return ResponseEntity.ok(Map.of(
                    "location", weather.getLocation(),
                    "weather", weather,
                    "recommendations", recommendations
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
