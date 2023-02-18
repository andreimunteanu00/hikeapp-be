package com.mnt.hikeapp.service;

import com.mnt.hikeapp.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public interface UserService {

    User createFirstTimeLogInUser(String googleId, String email) throws Exception;
    boolean checkUserExists(String googleId);
    User findByGoogleId(String googleId) throws SQLException, IOException;
    User save(User user);
    Boolean checkFieldDuplicate(String field, String value);
}