package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.Crop;
import com.smartfarm.smartfarm.repositories.CropRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CropService {

    private final CropRepo cropRepo;

    // CRUD operations
    public Crop addCrop(Crop crop) {
        return cropRepo.save(crop);
    }

    public List<Crop> getAllCrops() {
        return cropRepo.findAll();
    }
    public Crop getCropByName(String name) {
        return cropRepo.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("Crop data not available for: " + name));
    }


    public Crop updateCrop(Long id, Crop crop) {
        Optional<Crop> opt = cropRepo.findById(id);
        if (opt.isEmpty()) throw new RuntimeException("Crop not found with id " + id);
        Crop existing = opt.get();
        existing.setName(crop.getName());
        existing.setCategory(crop.getCategory());
        existing.setDescription(crop.getDescription());
        existing.setSoilType(crop.getSoilType());
        existing.setWaterRequirement(crop.getWaterRequirement());
        existing.setPlantingSeason(crop.getPlantingSeason());
        existing.setHarvestSeason(crop.getHarvestSeason());
        existing.setIdealTempMin(crop.getIdealTempMin());
        existing.setIdealTempMax(crop.getIdealTempMax());
        existing.setIdealHumidityMin(crop.getIdealHumidityMin());
        existing.setIdealHumidityMax(crop.getIdealHumidityMax());
        existing.setGrowthDurationDays(crop.getGrowthDurationDays());
        existing.setPopular(crop.getPopular());
        existing.setImageUrl(crop.getImageUrl());
        return cropRepo.save(existing);
    }

    public void deleteCrop(Long id) {
        if (!cropRepo.existsById(id)) throw new RuntimeException("Crop not found with id " + id);
        cropRepo.deleteById(id);
    }

    // Search by name
    public List<Crop> searchCrops(String query) {
        return cropRepo.findByNameContainingIgnoreCase(query);
    }

    // Recommendation based on weather + planting season
    public List<Crop> recommendCrops(Float currentTemp, Float currentHumidity) {
        Month currentMonth = LocalDate.now().getMonth();

        return cropRepo.findAll().stream()
                .filter(crop -> {
                    // Check temperature
                    boolean tempMatch = currentTemp >= crop.getIdealTempMin() && currentTemp <= crop.getIdealTempMax();

                    // Check humidity
                    boolean humidityMatch = currentHumidity >= crop.getIdealHumidityMin() && currentHumidity <= crop.getIdealHumidityMax();

                    // Check if current month is in planting season
                    boolean seasonMatch = crop.getPlantingSeason() != null &&
                            crop.getPlantingSeason().toLowerCase()
                                    .contains(currentMonth.name().substring(0, 3).toLowerCase());

                    return tempMatch && humidityMatch && seasonMatch;
                })
                .sorted((c1, c2) -> Boolean.compare(c2.getPopular(), c1.getPopular())) // popular crops first
                .collect(Collectors.toList());
    }
}
