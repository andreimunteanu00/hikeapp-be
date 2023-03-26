package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.user.UserAfterLoginDTO;

import java.io.IOException;
import java.sql.SQLException;

public interface AuthService {

    String generateToken(String googleId, String email) throws Exception;
    UserAfterLoginDTO getCurrentUserAfterFirstLogin() throws SQLException, IOException;

}
