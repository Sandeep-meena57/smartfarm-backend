package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.PestAlert;
import com.smartfarm.smartfarm.service.PestAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pest Alerts", description = "Manage pest outbreak alerts and notify farmers region-wise")
@RestController
@RequestMapping("/api/alerts")
public class PestAlertController {

    @Autowired
    private PestAlertService pestAlertService;

    @Operation(summary = "Add a new pest alert (Admin only)")
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PestAlert> addAlert(@RequestBody PestAlert alert) {
        return ResponseEntity.ok(pestAlertService.addAlert(alert));
    }

    @Operation(summary = "Get pest alerts by region")
    @GetMapping("/region")
    public ResponseEntity<List<PestAlert>> getAlertsByRegion(@RequestParam String region) {
        return ResponseEntity.ok(pestAlertService.getAlertsByRegion(region));
    }

    @Operation(summary = "Get all pest alerts")
    @GetMapping
    public ResponseEntity<List<PestAlert>> getAllAlerts() {
        return ResponseEntity.ok(pestAlertService.getAllAlerts());
    }
}
