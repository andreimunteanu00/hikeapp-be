package com.mnt.hikeapp.dto.mapper;

import com.mnt.hikeapp.dto.UserShowDTO;
import com.mnt.hikeapp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserShowDTO toUserShowDTO(User user) {
        return new UserShowDTO(
                user.getUsername(),
                user.getProfilePicture()
        );
    }
}
