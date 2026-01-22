package com.smartfarm.smartfarm.entity;

import com.smartfarm.smartfarm.model.RecommendationType;
import com.smartfarm.smartfarm.model.SeverityLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recommendation {
    private RecommendationType type;
    private String message;
    private SeverityLevel severity;
}
