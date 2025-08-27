package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.UserCrop;
import com.smartfarm.smartfarm.service.UserCropService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "User Crops", description = "Manage the crops grown by individual farmers")
@RestController
@RequestMapping("/api/user-crops")
@RequiredArgsConstructor
public class UserCropController {

    private final UserCropService userCropService;

    @Operation(summary = "Add a new crop grown by the logged-in farmer")
    @PostMapping
    @PreAuthorize("hasRole('FARMER')")
    public ResponseEntity<UserCrop> addCrop(
            @RequestParam Long cropId,
            @RequestParam double areaInAcres,
            @RequestParam(required = false) String notes,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sowingDate
    ) {
        return ResponseEntity.ok(userCropService.createUserCrop(cropId, areaInAcres, notes, sowingDate));
    }

    @Operation(summary = "Get all crops grown by the logged-in farmer")
    @GetMapping
    @PreAuthorize("hasRole('FARMER')")
    public ResponseEntity<List<UserCrop>> myCrops() {
        return ResponseEntity.ok(userCropService.getMyCrops());
    }

    @Operation(summary = "Delete a user's crop record by ID")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('FARMER')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userCropService.deleteUserCrop(id);
        return ResponseEntity.ok("Crop deleted");
    }
}
