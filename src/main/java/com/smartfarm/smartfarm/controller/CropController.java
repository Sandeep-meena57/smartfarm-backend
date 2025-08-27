package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.Crop;
import com.smartfarm.smartfarm.service.CropService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/crops")
@Tag(name = "Crop", description = "Admin-managed crops and recommendations")
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add a new crop (Admin only)")
    public ResponseEntity<Crop> addCrop(@RequestBody Crop crop ){
      return ResponseEntity.ok(cropService.addCrop(crop));
    }
    @GetMapping
    @Operation(summary = "Get all crops")
    public ResponseEntity<List<Crop>> getAll(){
        return ResponseEntity.ok(cropService.getAllCrops());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get crop by ID")

    public ResponseEntity<Crop> getById(@PathVariable Long id){
        return ResponseEntity.ok(cropService.getCropById(id));

    }
    @GetMapping("/recommend")
    @Operation(summary = "Get recommended crops based on soil, season, and water")
    public ResponseEntity<List<Crop>> recommendedCrops(@RequestParam
                                                       String soilType,
                                                       @RequestParam
                                                       String season,
                                                        @RequestParam
                                                       String waterRequirement){
        return  ResponseEntity.ok(cropService.recommendedCrops(soilType, season,waterRequirement));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update crop (Admin only)")
    public ResponseEntity<Crop> updateCrop(@PathVariable Long id,@RequestBody Crop crop){
        return  ResponseEntity.ok(cropService.updateCrop(id, crop));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete crop (Admin only)")
    public ResponseEntity<?> deleteCrop(@PathVariable Long id){
        cropService.deleteCrop(id);
        return ResponseEntity.ok("crop deleted successfully");
    }
}
