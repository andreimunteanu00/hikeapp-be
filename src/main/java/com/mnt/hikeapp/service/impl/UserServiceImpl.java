package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.UserRepository;
import com.mnt.hikeapp.service.UserService;
import com.mnt.hikeapp.util.Constants;
import com.mnt.hikeapp.util.Messages;
import com.mnt.hikeapp.util.Util;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Path;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createFirstTimeLogInUser(String googleId, String email) throws Exception {
        User user = new User();
        user.setGoogleId(googleId);
        user.setEmail(email);
        user.setFirstLogin(true);
        user.setProfilePicture(Util.fileToBase64(Path.of(Constants.PATH_DEFAULT_AVATAR)));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean checkUserExists(String googleId) {
        return userRepository.findByGoogleId(googleId).isPresent();
    }

    @Override
    @Transactional
    public User findByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId).orElse(null);
    }

    @Override
    @Transactional
    public User update(User user) throws Exception {
        if (!Util.checkSameUser(user.getGoogleId())) {
            throw new UserNotFoundException(Messages.NOT_SAME_USER_UPDATE);
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Boolean checkFieldDuplicate(String columnName, String value) {
        return userRepository.checkFieldDuplicate(columnName, value) != 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
