package com.smartfarm.smartfarm.service;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationPoint {
    private String name;
    private double lat;
    private double lon;
}
