package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.Recommendation;
import com.smartfarm.smartfarm.entity.WeatherData;
import com.smartfarm.smartfarm.model.RecommendationType;
import com.smartfarm.smartfarm.model.SeverityLevel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    /**
     * Generates crop recommendations based on weather and crop name.
     */
    public List<Recommendation> generate(WeatherData weather, String cropName) {
        List<Recommendation> list = new ArrayList<>();

        if (weather.getTemperature() > 35) {
            list.add(new Recommendation(
                    RecommendationType.IRRIGATION,
                    "High temperature detected. Increase irrigation for " + cropName,
                    SeverityLevel.HIGH
            ));
        }

        if (weather.getHumidity() > 80) {
            list.add(new Recommendation(
                    RecommendationType.DISEASE,
                    "High humidity detected. Risk of fungal disease for " + cropName,
                    SeverityLevel.HIGH
            ));
        }

        if (weather.getWindSpeed() > 30) {
            list.add(new Recommendation(
                    RecommendationType.WEATHER_ALERT,
                    "Strong winds expected. Avoid spraying today for " + cropName,
                    SeverityLevel.MEDIUM
            ));
        }

        if (weather.getTemperature() < 5) {
            list.add(new Recommendation(
                    RecommendationType.WEATHER_ALERT,
                    "Low temperature detected. Frost risk for " + cropName,
                    SeverityLevel.HIGH
            ));
        }

        return list;
    }
}
