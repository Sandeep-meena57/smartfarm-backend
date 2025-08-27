package com.smartfarm.smartfarm.repositories;

import com.smartfarm.smartfarm.entity.FarmingTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmingTipRepo extends JpaRepository<FarmingTip,Long> {
    List<FarmingTip> findByRegion(String region);
    List<FarmingTip> findByCropType(String cropType);
}
