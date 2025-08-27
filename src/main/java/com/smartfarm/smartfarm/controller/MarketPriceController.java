package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.MarketPrice;
import com.smartfarm.smartfarm.service.MarketPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Market Prices", description = "APIs for managing crop market prices")
@RestController
@RequestMapping("/api/market-prices")
public class MarketPriceController {

    @Autowired
    private MarketPriceService marketPriceService;

    @Operation(summary = "Add new market price (Admin only)")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MarketPrice> addPrice(@RequestBody MarketPrice price) {
        return ResponseEntity.ok(marketPriceService.addPrice(price));
    }

    @Operation(summary = "Get all market prices")
    @GetMapping
    public ResponseEntity<List<MarketPrice>> getAllPrices() {
        return ResponseEntity.ok(marketPriceService.getAllPrices());
    }

    @Operation(summary = "Get market price by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MarketPrice> getById(@PathVariable Long id) {
        return ResponseEntity.ok(marketPriceService.getById(id));
    }

    @Operation(summary = "Get market prices by crop name")
    @GetMapping("/crop")
    public ResponseEntity<List<MarketPrice>> getByCrop(@RequestParam String cropName) {
        return ResponseEntity.ok(marketPriceService.getPriceByCrop(cropName));
    }

    @Operation(summary = "Update market price by ID (Admin only)")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MarketPrice> updatePrice(@PathVariable Long id, @RequestBody MarketPrice price) {
        return ResponseEntity.ok(marketPriceService.updatedPrice(id, price));
    }

    @Operation(summary = "Delete market price by ID (Admin only)")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePrice(@PathVariable Long id) {
        marketPriceService.deleteMarketPrice(id);
        return ResponseEntity.ok("Market price entry deleted");
    }
}
