package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.UserRepository;
import com.mnt.hikeapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User createFirstTimeLogInUser(String googleId) {
        User user = new User();
        user.setGoogleId(googleId);
        user.setFirstLogin(true);
        return userRepository.save(user);
    }

    public boolean checkUserExists(String googleId) {
        return userRepository.findByGoogleId(googleId).isPresent();
    }

    public User findByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
