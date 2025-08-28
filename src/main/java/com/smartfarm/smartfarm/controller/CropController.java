package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.Crop;
import com.smartfarm.smartfarm.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/crops")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    // Search endpoint
    @GetMapping("/search")
    public ResponseEntity<List<Crop>> searchCrops(@RequestParam String query) {
        return ResponseEntity.ok(cropService.searchCrops(query));
    }

    // Recommendation endpoint (GET: temp & humidity)
    @GetMapping("/recommend")
    public ResponseEntity<List<Crop>> recommendCrops(@RequestParam Float temp, @RequestParam Float humidity) {
        return ResponseEntity.ok(cropService.recommendCrops(temp, humidity));
    }

    // CRUD endpoints
    @GetMapping
    public ResponseEntity<List<Crop>> getAllCrops() {
        return ResponseEntity.ok(cropService.getAllCrops());
    }

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
    @GetMapping("/by-name")
    public ResponseEntity<?> searchCrop(@RequestParam String name) {
        try {
            Crop crop = cropService.getCropByName(name);
            return ResponseEntity.ok(crop);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(404)
                    .body(Map.of("message", e.getMessage()));
        }
    }

}
