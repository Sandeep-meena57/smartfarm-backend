package com.smartfarm.smartfarm.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String cropName;

    private String location;

    private boolean sent;

    private LocalDateTime createdAt;
}
