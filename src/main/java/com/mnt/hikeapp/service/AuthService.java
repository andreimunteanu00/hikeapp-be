package com.mnt.hikeapp.service;

import com.mnt.hikeapp.entity.User;

public interface AuthService {

    String generateToken(String googleId, String email) throws Exception;
    User getCurrentUser();
}
