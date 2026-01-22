package com.smartfarm.smartfarm.scheduler;

import com.smartfarm.smartfarm.entity.Crop;
import com.smartfarm.smartfarm.entity.Recommendation;
import com.smartfarm.smartfarm.entity.WeatherData;
import com.smartfarm.smartfarm.model.SeverityLevel;
import com.smartfarm.smartfarm.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WeatherAlertScheduler {

    private final WeatherDataService weatherDataService;
    private final CropService cropService;
    private final RecommendationService recommendationService;
    private final NotificationService notificationService;

    @Scheduled(cron = "0 0 */3 * * *") // every 3 hours
    public void generateAlerts() {

        String location = "Indore";
        String cropName = "wheat";

        List<WeatherData> weatherList = weatherDataService.getByLocation(location);
        if (weatherList.isEmpty()) return;

        WeatherData latestWeather = weatherList.get(0);

        Crop crop = cropService.getCropByName(cropName);

        List<Recommendation> recommendations = recommendationService.generate(latestWeather, cropName);

        for (Recommendation r : recommendations) {
            if (r.getSeverity() == SeverityLevel.HIGH) {
                notificationService.createNotification(r, cropName, location);
            }
        }
    }
}
