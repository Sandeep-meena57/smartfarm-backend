package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.Crop;
import com.smartfarm.smartfarm.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/crops")
public class CropController {
    @Autowired
    private CropService cropService;

    // public recommend endpoint (POST body: { soilType, season, waterRequirement })
    @PostMapping("/recommend")
    public ResponseEntity<List<Crop>> recommendCrops(@RequestBody Map<String, String> request) {
        String soilType = request.get("soilType");
        String season = request.get("season");
        String waterRequirement = request.get("waterRequirement");
        List<Crop> crops = cropService.recommendCrops(soilType, season, waterRequirement);
        return ResponseEntity.ok(crops);
    }

    @GetMapping
    public ResponseEntity<List<Crop>> getAll() {
        return ResponseEntity.ok(cropService.getAllCrops());
    }

    // Admin endpoints — if you have Spring Security + roles, you can add @PreAuthorize("hasRole('ADMIN')") above these.
    @PostMapping
    public ResponseEntity<Crop> addCrop(@RequestBody Crop crop) {
        return ResponseEntity.ok(cropService.addCrop(crop));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Crop> updateCrop(@PathVariable Long id, @RequestBody Crop crop) {
        return ResponseEntity.ok(cropService.updateCrop(id, crop));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return ResponseEntity.noContent().build();
    }
}
