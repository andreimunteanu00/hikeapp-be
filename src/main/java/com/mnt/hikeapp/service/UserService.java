package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.user.UserSetupDTO;
import com.mnt.hikeapp.dto.user.UserShowDTO;
import com.mnt.hikeapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.sql.SQLException;

public interface UserService {

    User createFirstTimeLogInUser(String googleId, String email) throws Exception;
    boolean checkUserExists(String googleId);
    User findByGoogleId(String googleId) throws SQLException, IOException;
    User update(UserSetupDTO user) throws Exception;
    Boolean checkFieldDuplicate(String field, String value);
    Page<UserShowDTO> findAllUsersForChat(String chatType, String username, Pageable pageable);
}