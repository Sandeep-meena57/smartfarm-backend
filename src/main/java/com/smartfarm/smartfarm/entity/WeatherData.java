package com.smartfarm.smartfarm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    private double temperature;

    private int humidity;

    @Column(name = "weather_condition")
    private String condition;


    private double windSpeed;

    private LocalDate date;
}
