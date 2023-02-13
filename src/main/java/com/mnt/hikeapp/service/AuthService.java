package com.mnt.hikeapp.service;

import com.mnt.hikeapp.entity.User;

public interface AuthService {

    String generateToken(String googleId);

    User getCurrentUser();
}
