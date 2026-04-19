package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.Fertilizer;
import com.smartfarm.smartfarm.service.FertilizerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fertilizers")
@RequiredArgsConstructor
@Tag(name = "Fertilizer", description = "Endpoints for managing fertilizers")
public class FertilizerController {

    private final FertilizerService fertilizerService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Add fertilizer - Admin only")
    public ResponseEntity<Fertilizer> addFertilizer(@RequestBody Fertilizer fertilizer) {
        return ResponseEntity.ok(fertilizerService.addFertilizer(fertilizer));
    }

    @GetMapping
    @Operation(summary = "Get all fertilizers")
    public ResponseEntity<List<Fertilizer>> getAllFertilizers() {
        return ResponseEntity.ok(fertilizerService.getAllFertilizer());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get fertilizer by ID")
    public ResponseEntity<Fertilizer> getById(@PathVariable Long id) {
        return ResponseEntity.ok(fertilizerService.getFertilizerById(id));
    }

    @GetMapping("/crop")
    @Operation(summary = "Get fertilizers by crop type")
    public ResponseEntity<List<Fertilizer>> getByCrop(@RequestParam String cropName) {
        return ResponseEntity.ok(fertilizerService.getByCrop(cropName));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update fertilizer - Admin only")
    public ResponseEntity<Fertilizer> updateFertilizer(@PathVariable Long id, @RequestBody Fertilizer fertilizer) {
        return ResponseEntity.ok(fertilizerService.updateFertilizer(id, fertilizer));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete fertilizer - Admin only")
    public ResponseEntity<String> deleteFertilizer(@PathVariable Long id) {
        fertilizerService.deleteFertilizer(id);
        return ResponseEntity.ok("Fertilizer deleted");
    }
}