package com.smartfarm.smartfarm.repositories;

import com.smartfarm.smartfarm.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {

    // Optional: get all unsent notifications
    List<Notification> findBySentFalse();
}
