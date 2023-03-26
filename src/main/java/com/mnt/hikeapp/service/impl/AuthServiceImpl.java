package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.dto.mapper.UserMapper;
import com.mnt.hikeapp.dto.user.UserAfterLoginDTO;
import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.service.AuthService;
import com.mnt.hikeapp.service.UserService;
import com.mnt.hikeapp.util.JwtTokenUtil;
import com.mnt.hikeapp.util.Messages;
import com.mnt.hikeapp.util.Util;
import com.mnt.hikeapp.util.exception.UserNotCreated;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public String generateToken(String googleId, String email) throws Exception {
        if (!userService.checkUserExists(googleId)) {
            User user = userService.createFirstTimeLogInUser(googleId, email);
            if (user == null) throw new UserNotCreated(Messages.FAIL_CREATE_USER);
        }
        return jwtTokenUtil.generateToken(googleId);
    }

    @Override
    public UserAfterLoginDTO getCurrentUserAfterFirstLogin() throws SQLException, IOException {
        return userMapper.toUserAfterLoginDTO(userService.findByGoogleId(Util.getCurrentUserGoogleId()));
    }
}
