package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.Crop;
import com.smartfarm.smartfarm.repositories.CropRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropService {

    @Autowired
    private CropRepo cropRepo;

    public Crop addCrop(Crop crop) {
        return cropRepo.save(crop);
    }

    public List<Crop> getAllCrops() {
        return cropRepo.findAll();
    }

    public Crop updateCrop(Long id, Crop crop) {
        Optional<Crop> opt = cropRepo.findById(id);
        if (opt.isEmpty()) throw new RuntimeException("Crop not found with id " + id);
        Crop existing = opt.get();
        existing.setName(crop.getName());
        existing.setDescription(crop.getDescription());
        existing.setSeason(crop.getSeason());
        existing.setSoilType(crop.getSoilType());
        existing.setWaterRequirement(crop.getWaterRequirement());
        return cropRepo.save(existing);
    }

    public void deleteCrop(Long id) {
        if (!cropRepo.existsById(id)) throw new RuntimeException("Crop not found with id " + id);
        cropRepo.deleteById(id);
    }

    public List<Crop> recommendCrops(String soilType, String season, String waterRequirement) {
        if (soilType == null || season == null || waterRequirement == null) {
            return List.of();
        }
        return cropRepo.findByConditionsIgnoreCase(soilType, season, waterRequirement);
    }
}
