package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.Security.JwtService;
import com.smartfarm.smartfarm.entity.User;
import com.smartfarm.smartfarm.repositories.UserRepo;
import com.smartfarm.smartfarm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "Operations related to Users")
public class UserController {

    @Autowired
    private UserService userService;
   @Autowired
   private JwtService jwtService;

   @Autowired
   private UserRepo userRepo;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all users(ADMIN Only)")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.of(Optional.ofNullable(userService.getAllUser()));
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getById(@PathVariable Long id) {
//        return userService.getUserById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
//        return ResponseEntity.ok(userService.updateUser(id, user));
//    }

    // 🟢 Allow logged-in user to get only their own profile
    @GetMapping("/{id}")
    @Operation(summary = " Allow logged-in user to get only their own profile")
    public ResponseEntity<User> getById(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        User loggedInUser = userService.getUserByEmail(email).orElseThrow();

        if (!loggedInUser.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // ❌ Block if trying to access other user
        }

        return ResponseEntity.ok(loggedInUser); // ✅ Return their own data
    }
    // ✅ Get currently logged-in user (used to fetch region)
    @GetMapping("/me")
    @Operation(summary = "Get currently logged-in user (used to fetch region)")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        return userRepo.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 🟢 Allow user to update only their own info
    @PutMapping("/{id}")
    @Operation(summary = "Allow user to update only their own info")
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
    @Operation(summary = "Admin can delete user")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}

