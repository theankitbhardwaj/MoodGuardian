package com.bhardwaj.MoodGuardian.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/test")
@Tag(name= "Test")
public class TestController {
    @Operation(
            description = "Public Content",
            summary = "This endpoint will return even if user is not authenticated."
    )
    @GetMapping("/all")
    public String AllAccess(){
        return "Public Content";
    }

    @Operation(
            description = "User Content",
            summary = "This endpoint will return only if user is authenticated."
    )
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess(){
        return "User content";
    }

    @Operation(
            description = "Admin Content",
            summary = "This endpoint will return only if user is admin."
    )
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin access";
    }

    @Operation(
            description = "Mod Content",
            summary = "This endpoint will return only if user is moderator."
    )
    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String modAccess() {
        return "Mod access";
    }
}
