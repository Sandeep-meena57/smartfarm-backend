package com.smartfarm.smartfarm.controller;

import com.smartfarm.smartfarm.entity.Query;
import com.smartfarm.smartfarm.entity.User;
import com.smartfarm.smartfarm.service.QueryService;
import com.smartfarm.smartfarm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Queries", description = "Farmers can ask questions; Admins can view and manage all queries.")
@RestController
@RequestMapping("/api/queries")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Submit a new query (Farmer only)")
    @PostMapping
    @PreAuthorize("hasRole('FARMER')")
    public ResponseEntity<Query> submitQuery(@RequestBody Query query) {
        return ResponseEntity.ok(queryService.submitQuery(query));
    }

    @Operation(summary = "Get all queries (Admin only)")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Query>> getAllQueries() {
        return ResponseEntity.ok(queryService.getAllQueries());
    }

    @Operation(summary = "Get query by ID (Admin only)")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Query> getQueryById(@PathVariable Long id) {
        return ResponseEntity.ok(queryService.getQueryById(id));
    }

    @Operation(summary = "Get all queries submitted by a specific user (Admin only)")
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Query>> getQueriesByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(queryService.getQueriesByUser(user));
    }

    @Operation(summary = "Update a query (Admin only)")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Query> updateQuery(@PathVariable Long id, @RequestBody Query query) {
        return ResponseEntity.ok(queryService.updateQuery(id, query));
    }

    @Operation(summary = "Delete a query (Admin only)")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteQuery(@PathVariable Long id) {
        queryService.deleteQuery(id);
        return ResponseEntity.ok("Query deleted");
    }
}
