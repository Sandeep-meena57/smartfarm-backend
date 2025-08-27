package com.smartfarm.smartfarm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmingTip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String cropType; // e.g. "Wheat", "Garlic", etc.

    private String region;   // e.g. "MP", "Rajasthan" — useful for filtering

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Who posted this tip (farmer)
}
