package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.Security.JwtService;
import com.smartfarm.smartfarm.entity.User;
import com.smartfarm.smartfarm.repositories.UserRepo;
import com.smartfarm.smartfarm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Operations related to Users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepo userRepo;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all users - Admin only")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.of(Optional.ofNullable(userService.getAllUser()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get own profile by ID")
    public ResponseEntity<User> getById(@PathVariable Long id,
                                        @RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        User loggedInUser = userService.getUserByEmail(email).orElseThrow();
        if (!loggedInUser.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(loggedInUser);
    }

    @GetMapping("/me")
    @Operation(summary = "Get currently logged-in user")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        return userRepo.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update own profile")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user,
                                       @RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        User loggedInUser = userService.getUserByEmail(email).orElseThrow();
        if (!loggedInUser.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user - Admin only")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}