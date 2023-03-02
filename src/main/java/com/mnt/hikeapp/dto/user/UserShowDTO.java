package com.mnt.hikeapp.dto.user;

import com.mnt.hikeapp.entity.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserShowDTO {

    private String username;
    private Picture profilePicture;

}
