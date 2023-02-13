package com.mnt.hikeapp.service;

import com.mnt.hikeapp.entity.User;

public interface UserService {

    User createFirstTimeLogInUser(String googleId);
    boolean checkUserExists(String googleId);
    User findByGoogleId(String googleId);

    User save(User user);

}