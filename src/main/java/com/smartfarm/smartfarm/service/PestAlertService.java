package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.PestAlert;
import com.smartfarm.smartfarm.repositories.PestAlertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PestAlertService {
    @Autowired
    private PestAlertRepo pestAlertRepo;

    public PestAlert addAlert(PestAlert alert) {
        return pestAlertRepo.save(alert);
    }

    public List<PestAlert> getAlertsByRegion(String region) {
        return pestAlertRepo.findByRegionIgnoreCase(region);
    }

    public List<PestAlert> getAllAlerts() {
        return pestAlertRepo.findAll();
    }

    public List<PestAlert> getAlertsByCrop(String crop) {
        return pestAlertRepo.findByCropAffected(crop);
    }
}
