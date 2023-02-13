package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("{googleId}")
    private ResponseEntity<User> findUserByGoogleId(@PathVariable String googleId) {
        return ResponseEntity.ok(userService.findByGoogleId(googleId));
    }

    @PostMapping("save")
    private ResponseEntity<User> updateUser(@RequestBody User user) {
        user = userService.save(user);
        return ResponseEntity.ok(user);
    }
}
