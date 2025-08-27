package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.Fertilizer;
import com.smartfarm.smartfarm.repositories.FertilizerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FertilizerService {
    @Autowired
    private FertilizerRepo fertilizerRepo;

    public Fertilizer addFertilizer(Fertilizer fertilizer){

        return fertilizerRepo.save(fertilizer);
    }

    public Fertilizer getFertilizerById(Long id){
        return fertilizerRepo.findById(id).orElseThrow(()->new RuntimeException("Fertilizer Not Found "));

    }
    public List<Fertilizer> getAllFertilizer(){
        return fertilizerRepo.findAll();
    }

    public List<Fertilizer> getByCrop(String cropName){
        return fertilizerRepo.findByRecommendedForCrop(cropName);

    }

    public Fertilizer updateFertilizer(Long  id, Fertilizer updatedFertilizer){
        Fertilizer fert = fertilizerRepo.findById(id).orElseThrow(()->new RuntimeException("Fertilizer not found"));

        fert.setName(updatedFertilizer.getName());
        fert.setRecommendedForCrop(updatedFertilizer.getRecommendedForCrop());
        fert.setUsageInstruction(updatedFertilizer.getUsageInstruction());
        return fertilizerRepo.save(fert);
    }

    public void deleteFertilizer(Long id){
        fertilizerRepo.deleteById(id);
    }
}
