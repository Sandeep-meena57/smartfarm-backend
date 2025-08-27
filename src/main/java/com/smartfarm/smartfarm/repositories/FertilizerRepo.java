package com.smartfarm.smartfarm.repositories;

import com.smartfarm.smartfarm.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FertilizerRepo extends JpaRepository<Fertilizer,Long> {
    List<Fertilizer>findByRecommendedForCrop(String recommendedForCrop);
}
