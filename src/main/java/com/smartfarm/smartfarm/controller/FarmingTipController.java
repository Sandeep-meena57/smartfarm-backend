package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.Security.JwtService;
import com.smartfarm.smartfarm.entity.FarmingTip;
import com.smartfarm.smartfarm.service.FarmingTipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tips")
@Tag(name = "Farming Tips", description = "Farming Tips shared by farmers")
public class FarmingTipController {

    @Autowired
    private FarmingTipService farmingTipService;

    @Autowired
    private JwtService jwtService;
    @PreAuthorize("hasRole('FARMER')")
    @PostMapping
    @Operation(summary = "Farmer can give tips according to their experience")
    public ResponseEntity<FarmingTip> addTip(@RequestBody FarmingTip tip,
                                             @RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        return ResponseEntity.ok(farmingTipService.addTip(tip, email));
    }

    @GetMapping
    @Operation(summary = "for alll tips any user can view it")
    public ResponseEntity<List<FarmingTip>> getAllTips() {
        return ResponseEntity.ok(farmingTipService.getAllTips());
    }

    @GetMapping("/region")
    @Operation(summary = "get tips according to region")
    public ResponseEntity<List<FarmingTip>> getByRegion(@RequestParam String region) {
        return ResponseEntity.ok(farmingTipService.getTipsByRegion(region));
    }

    @GetMapping("/crop")
    @Operation(summary = "get tips according to crop")
    public ResponseEntity<List<FarmingTip>> getByCrop(@RequestParam String cropType) {
        return ResponseEntity.ok(farmingTipService.getTipsByCrop(cropType));
    }
}