package com.smartfarm.smartfarm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    private double temperature;

    private double humidity;

    private String forecast;

    private Double windSpeed;

    private LocalDate date;

    public WeatherData(Object o, String delhi, double v, double v1, double v2, LocalDate localDate) {
    }
}
