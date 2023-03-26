package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.dto.user.UserAfterLoginDTO;
import com.mnt.hikeapp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("{googleId}/{email}")
    public ResponseEntity<String> generateToken(@PathVariable String googleId, @PathVariable String email) throws Exception {
        return ResponseEntity.ok(authService.generateToken(googleId, email));
    }

    @GetMapping("current-user-after-first-login")
    public ResponseEntity<UserAfterLoginDTO> getCurrentUserAfterFirstLogin() throws SQLException, IOException {
        return ResponseEntity.ok(authService.getCurrentUserAfterFirstLogin());
    }
}
