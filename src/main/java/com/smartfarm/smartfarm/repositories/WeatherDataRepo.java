package com.smartfarm.smartfarm.repositories;

import com.smartfarm.smartfarm.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WeatherDataRepo extends JpaRepository<WeatherData,Long> {
    List<WeatherData> findByLocationOrderByDateDesc(String location);
}
