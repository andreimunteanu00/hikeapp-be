package com.mnt.hikeapp.service;

import com.mnt.hikeapp.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public interface AuthService {

    String generateToken(String googleId, String email) throws Exception;
    User getCurrentUser() throws SQLException, IOException;
}
