package com.smartfarm.smartfarm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class cropRecommendationRequest {

    private String soilType;
    private String season;
    private String waterAvailability;
}
