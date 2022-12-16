package com.mnt.hikeapp.service;

import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createFirstTimeLogInUser(String googleId) {
        User user = new User();
        user.setGoogleId(googleId);
        return userRepository.save(user);
    }

    public boolean checkUserExists(String googleId) {
        return userRepository.findByGoogleId(googleId).isPresent();
    }
}
