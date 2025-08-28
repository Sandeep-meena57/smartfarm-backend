package com.smartfarm.smartfarm.repositories;

import com.smartfarm.smartfarm.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CropRepo extends JpaRepository<Crop, Long> {

    // Search by crop name (for free text search)
    List<Crop> findByNameContainingIgnoreCase(String name);

    // Optional: search by category
    List<Crop> findByCategoryIgnoreCase(String category);
    boolean existsByNameIgnoreCase(String name);
    Optional<Crop> findByNameIgnoreCase(String name);

}
