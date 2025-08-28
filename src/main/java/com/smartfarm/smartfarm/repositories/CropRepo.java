package com.smartfarm.smartfarm.repositories;

import com.smartfarm.smartfarm.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CropRepo extends JpaRepository<Crop, Long> {

    @Query("SELECT c FROM Crop c " +
            "WHERE LOWER(c.soilType) = LOWER(:soilType) " +
            "AND LOWER(c.season) = LOWER(:season) " +
            "AND LOWER(c.waterRequirement) = LOWER(:waterRequirement)")
    List<Crop> findByConditionsIgnoreCase(@Param("soilType") String soilType,
                                          @Param("season") String season,
                                          @Param("waterRequirement") String waterRequirement);
}
