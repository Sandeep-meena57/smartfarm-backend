package com.smartfarm.smartfarm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "crops")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;               // Crop name
    private String category;           // Cereal, Vegetable, Fruit, etc.

    private Float idealTempMin;        // Min temperature in °C
    private Float idealTempMax;        // Max temperature in °C

    private Float idealHumidityMin;    // Min humidity in %
    private Float idealHumidityMax;    // Max humidity in %

    private String soilType;           // Loamy, Clay, Sandy, etc.
    private String waterRequirement;   // Low, Medium, High

    private String plantingSeason;     // e.g., Oct-Jan
    private String harvestSeason;      // e.g., Mar-May

    private Integer growthDurationDays;

    private Boolean popular;           // true if commonly recommended

    @Column(length = 1000)
    private String description;        // Optional notes

    private String imageUrl;           // Optional for frontend
}
