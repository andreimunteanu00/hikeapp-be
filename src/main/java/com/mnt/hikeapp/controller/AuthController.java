package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("{googleId}")
    public ResponseEntity<String> generateToken(@PathVariable Long googleId) {
        return ResponseEntity.ok(authService.generateToken(googleId));
    }
}
