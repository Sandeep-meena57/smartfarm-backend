package com.smartfarm.smartfarm.controller;



import com.smartfarm.smartfarm.entity.Fertilizer;
import com.smartfarm.smartfarm.service.FertilizerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fertilizers")
@Tag(name = "Fertilizer", description = "Endpoints for managing fertilizers")
public class FertilizerController {

    @Autowired
    private FertilizerService fertilizerService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "For adding fertilizer(ADMIN Only)")
    public ResponseEntity<Fertilizer> addFertilizer(@RequestBody Fertilizer fertilizer) {
        return ResponseEntity.ok(fertilizerService.addFertilizer(fertilizer));
    }

    @GetMapping
    @Operation(summary = "For getting all fertilizer")
    public ResponseEntity<List<Fertilizer>> getAllFertilizers() {
        return ResponseEntity.ok(fertilizerService.getAllFertilizer());
    }

    @GetMapping("/{id}")
    @Operation(summary = "For getting all fertilizer  by id")
    public ResponseEntity<Fertilizer> getById(@PathVariable Long id) {
        return ResponseEntity.ok(fertilizerService.getFertilizerById(id));
    }

    @GetMapping("/crop")
    @Operation(summary = "For getting all fertilizer by crop type")
    public ResponseEntity<List<Fertilizer>> getByCrop(@RequestParam String cropName) {
        return ResponseEntity.ok(fertilizerService.getByCrop(cropName));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "updating fertilizer (ADMIN only)")
    public ResponseEntity<Fertilizer> updateFertilizer(@PathVariable Long id, @RequestBody Fertilizer fertilizer) {
        return ResponseEntity.ok(fertilizerService.updateFertilizer(id, fertilizer));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deleting fertilizer( ADMIN only)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFertilizer(@PathVariable Long id) {
        fertilizerService.deleteFertilizer(id);
        return ResponseEntity.ok("Fertilizer deleted");
    }
}