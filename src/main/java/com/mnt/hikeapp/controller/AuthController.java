package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("{googleId}/{email}")
    public ResponseEntity<String> generateToken(@PathVariable String googleId, @PathVariable String email) {
        return ResponseEntity.ok(authService.generateToken(googleId, email));
    }

    @GetMapping("currentUser")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }
}
