package com.smartfarm.smartfarm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartfarm.smartfarm.entity.MarketPrice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketPriceRepo extends JpaRepository<MarketPrice,Long> {

    List<MarketPrice> findByCropNameOrderByDateDesc(String cropName);
}
