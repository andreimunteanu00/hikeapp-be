package com.mnt.hikeapp.service;

import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    public String generateToken(String googleId) {
        if (!userService.checkUserExists(googleId)) {
            User user = userService.createFirstTimeLogInUser(googleId);
            if (user == null) throw new Error("fail create new user!");
        }
        return jwtTokenUtil.generateToken(googleId);
    }
}
