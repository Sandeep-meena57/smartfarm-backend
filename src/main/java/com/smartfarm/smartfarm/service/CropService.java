package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.Crop;
import com.smartfarm.smartfarm.repositories.CropRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {
    @Autowired
    private CropRepo cropRepo;

    public Crop addCrop(Crop crop){
        return cropRepo.save(crop);

    }

    public Crop getCropById(Long id){
        return cropRepo.findById(id).orElseThrow(()->new RuntimeException("Crop not Found "));
    }

    public List<Crop> getAllCrops(){
        return cropRepo.findAll();
    }

    public List<Crop> recommendedCrops(String soilType,String season,String waterRequirement){
        return cropRepo.findBySoilTypeAndSeasonAndWaterRequirement(soilType,season,waterRequirement);
    }

    public Crop updateCrop(Long id,Crop updatedCrop){
        Crop crop = cropRepo.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        crop.setName(updatedCrop.getName());
        crop.setDescription(updatedCrop.getDescription());
        crop.setSeason(updatedCrop.getSeason());
        crop.setSoilType(updatedCrop.getSoilType());
        return cropRepo.save(crop);
    }

    public  void deleteCrop(Long id){
        cropRepo.deleteById(id);
    }

}
