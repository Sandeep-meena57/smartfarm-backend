package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.Notification;
import com.smartfarm.smartfarm.repositories.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    public Notification createNotification(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        return notificationRepo.save(notification);
    }

    public Notification getById(Long id) {
        return notificationRepo.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public List<Notification> getAllNotifications() {
        return notificationRepo.findAll();
    }

    public Notification updateNotification(Long id, Notification updated) {
        Notification note = notificationRepo.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));

        note.setTitle(updated.getTitle());
        note.setMessage(updated.getMessage());
        note.setType(updated.getType());
        note.setCreatedAt(updated.getCreatedAt());
        return notificationRepo.save(note);
    }

    public void deleteNotification(Long id) {
        notificationRepo.deleteById(id);
    }
}