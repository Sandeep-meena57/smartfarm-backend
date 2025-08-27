package com.smartfarm.smartfarm.repositories;

import com.smartfarm.smartfarm.entity.PestAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PestAlertRepo extends JpaRepository<PestAlert,Long> {
    List<PestAlert> findByRegionIgnoreCase(String region);
    List<PestAlert> findByCropAffected(String cropAffected);
}
