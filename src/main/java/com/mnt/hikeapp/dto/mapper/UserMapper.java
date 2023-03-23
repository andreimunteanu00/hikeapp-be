package com.mnt.hikeapp.dto.mapper;

import com.mnt.hikeapp.dto.user.UserSecurityDTO;
import com.mnt.hikeapp.dto.user.UserShowDTO;
import com.mnt.hikeapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserShowDTO toUserShowDTO(User user) {
        return new UserShowDTO(
                user.getUsername(),
                user.getGoogleId(),
                user.getProfilePicture()
        );
    }

    public Page<UserShowDTO> toUserShowListPageDTO(Page<User> userList) {
        List<UserShowDTO> aux = userList.stream().map(this::toUserShowDTO).collect(Collectors.toList());
        return new PageImpl<>(aux);
    }

    public UserSecurityDTO toUserSecurityDTO(User user) {
        return new UserSecurityDTO(
                user.getGoogleId()
        );
    }
}
