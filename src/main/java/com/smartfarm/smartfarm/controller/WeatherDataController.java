package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.WeatherData;
import com.smartfarm.smartfarm.service.WeatherDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@Tag(name = "Weather Data", description = "Manage and retrieve weather data records")
public class WeatherDataController {

    private final WeatherDataService weatherDataService;

    @Operation(summary = "Save weather data (Admin only)")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<WeatherData> saveData(@RequestBody WeatherData data) {
        return ResponseEntity.ok(weatherDataService.saveData(data));
    }

    @Operation(summary = "Get all weather records")
    @GetMapping
    public ResponseEntity<List<WeatherData>> getAllWeather() {
        return ResponseEntity.ok(weatherDataService.getAllData());
    }

    @Operation(summary = "Get weather by ID")
    @GetMapping("/{id}")
    public ResponseEntity<WeatherData> getById(@PathVariable Long id) {
        return ResponseEntity.ok(weatherDataService.getById(id));
    }

    @Operation(summary = "Get weather by location (latest first)")
    @GetMapping("/location")
    public ResponseEntity<List<WeatherData>> getByLocation(@RequestParam String location) {
        return ResponseEntity.ok(weatherDataService.getByLocation(location));
    }
}
