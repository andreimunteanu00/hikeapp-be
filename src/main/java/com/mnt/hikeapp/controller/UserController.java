package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.service.UserService;
import com.mnt.hikeapp.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("{googleId}")
    private ResponseEntity<User> findUserByGoogleId(@PathVariable String googleId) {
        return ResponseEntity.ok(userService.findByGoogleId(googleId));
    }
}
