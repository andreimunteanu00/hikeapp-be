package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.UserRepository;
import com.mnt.hikeapp.service.UserService;
import com.mnt.hikeapp.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createFirstTimeLogInUser(String googleId, String email) throws Exception {
        User user = new User();
        user.setGoogleId(googleId);
        user.setEmail(email);
        user.setFirstLogin(true);
        user.setProfilePicture(Util.fileToBase64(Path.of("src/main/resources/images/default_avatar.png")));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean checkUserExists(String googleId) {
        return userRepository.findByGoogleId(googleId).isPresent();
    }

    @Override
    @Transactional
    public User findByGoogleId(String googleId) throws SQLException, IOException {
        return userRepository.findByGoogleId(googleId).orElse(null);
    }

    @Override
    @Transactional
    public User save(User user) {
        if (!Util.getCurrentUser().getGoogleId().equals(user.getGoogleId())) {
            throw new Error("you can't save another user data!");
        }
        return userRepository.save(user);
    }

    @Override
    public Boolean checkFieldDuplicate(String columnName, String value) {
        return userRepository.checkFieldDuplicate(columnName, value) != 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
