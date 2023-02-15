package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.UserRepository;
import com.mnt.hikeapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createFirstTimeLogInUser(String googleId, String email) {
        User user = new User();
        user.setGoogleId(googleId);
        user.setEmail(email);
        user.setFirstLogin(true);
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
    public User save(User user) {
        return userRepository.save(user);
    }
}
