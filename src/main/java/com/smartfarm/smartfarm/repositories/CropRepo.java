package com.smartfarm.smartfarm.repositories;

import com.smartfarm.smartfarm.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CropRepo extends JpaRepository<Crop,Long> {

    List<Crop> findBySoilTypeAndSeasonAndWaterRequirement(String soilType,String season,String waterRequirement);
}
