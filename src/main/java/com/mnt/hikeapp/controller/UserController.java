package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.dto.user.UserSetupDTO;
import com.mnt.hikeapp.dto.user.UserShowDTO;
import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("{googleId}")
    private ResponseEntity<User> findUserByGoogleId(@PathVariable String googleId) throws SQLException, IOException {
        return ResponseEntity.ok(userService.findByGoogleId(googleId));
    }

    @GetMapping("checkFieldDuplicate/{columnName}/{username}")
    private ResponseEntity<Boolean> checkFieldDuplicate(@PathVariable String columnName, @PathVariable String username) {
        return ResponseEntity.ok(userService.checkFieldDuplicate(columnName, username));
    }

    @GetMapping("/all")
    private ResponseEntity<Page<UserShowDTO>> getAllUsersForNewChat(@RequestParam(name = "username", defaultValue = "") String username,
                                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(userService.findAllUsersForChat(username, PageRequest.of(page, size)));
    }

    @PutMapping("update")
    private ResponseEntity<UserSetupDTO> update(@RequestBody UserSetupDTO userSetupDTO) throws Exception {
        UserSetupDTO user = userService.update(userSetupDTO);
        return ResponseEntity.ok(user);
    }
}
