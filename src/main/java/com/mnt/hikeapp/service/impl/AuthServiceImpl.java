package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.service.AuthService;
import com.mnt.hikeapp.service.UserService;
import com.mnt.hikeapp.util.JwtTokenUtil;
import com.mnt.hikeapp.util.Messages;
import com.mnt.hikeapp.util.Util;
import com.mnt.hikeapp.util.exception.UserNotCreated;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    public String generateToken(String googleId, String email) throws Exception {
        if (!userService.checkUserExists(googleId)) {
            User user = userService.createFirstTimeLogInUser(googleId, email);
            if (user == null) throw new UserNotCreated(Messages.FAIL_CREATE_USER);
        }
        return jwtTokenUtil.generateToken(googleId);
    }

    @Override
    public User getCurrentUser() {
        return Util.getCurrentUser();
    }
}
