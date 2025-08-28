package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.Notification;
import com.smartfarm.smartfarm.entity.Recommendation;
import com.smartfarm.smartfarm.model.RecommendationType;
import com.smartfarm.smartfarm.model.SeverityLevel;
import com.smartfarm.smartfarm.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getUnsentNotifications();
    }

    @PostMapping("/test")
    public Notification createTestNotification(
            @RequestParam String cropName,
            @RequestParam String location,
            @RequestParam String message
    ) {
        Recommendation recommendation = new Recommendation(
                RecommendationType.WEATHER_ALERT,
                message,
                SeverityLevel.HIGH
        );
        return notificationService.createNotification(recommendation, cropName, location);
    }
}
