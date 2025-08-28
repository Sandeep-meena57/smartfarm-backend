package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.Notification;
import com.smartfarm.smartfarm.entity.Recommendation;
import com.smartfarm.smartfarm.model.SeverityLevel;
import com.smartfarm.smartfarm.repositories.NotificationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepo notificationRepo;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Create a notification for HIGH severity recommendations.
     * Also push it via WebSocket.
     */
    public Notification createNotification(Recommendation recommendation, String cropName, String location) {
        if (recommendation.getSeverity() != SeverityLevel.HIGH) return null;

        Notification notification = new Notification();
        notification.setMessage(recommendation.getMessage());
        notification.setCropName(cropName);
        notification.setLocation(location);
        notification.setSent(false);
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepo.save(notification);

        // Push via WebSocket
        try {
            messagingTemplate.convertAndSend("/topic/notifications", notification);
            System.out.println("✅ Notification sent via WebSocket: " + notification.getMessage());
        } catch (Exception e) {
            System.err.println("❌ WebSocket push failed: " + e.getMessage());
        }

        return notification;
    }

    public void markNotificationsAsSent(List<Notification> notifications) {
        notifications.forEach(n -> n.setSent(true));
        notificationRepo.saveAll(notifications);
    }

    public List<Notification> getUnsentNotifications() {
        return notificationRepo.findBySentFalse();
    }
}
