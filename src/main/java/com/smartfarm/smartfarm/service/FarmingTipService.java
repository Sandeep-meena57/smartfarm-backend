package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.FarmingTip;
import com.smartfarm.smartfarm.entity.User;
import com.smartfarm.smartfarm.repositories.FarmingTipRepo;
import com.smartfarm.smartfarm.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmingTipService {
    @Autowired
    private  FarmingTipRepo farmingTipRepo;
    @Autowired
    private UserRepo userRepo;

    public FarmingTip addTip(FarmingTip tip, String farmerEmail) {
        User user = userRepo.findByEmail(farmerEmail).orElseThrow();
        tip.setUser(user);
        return farmingTipRepo.save(tip);
    }


    public List<FarmingTip> getAllTips() {
        return farmingTipRepo.findAll();
    }


    public List<FarmingTip> getTipsByRegion(String region) {
        return farmingTipRepo.findByRegion(region);
    }


    public List<FarmingTip> getTipsByCrop(String cropType) {
        return farmingTipRepo.findByCropType(cropType);
    }
}
