package com.mnt.hikeapp.dto.user;

import com.mnt.hikeapp.entity.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAfterLoginDTO {

    private String username;
    private String googleId;
    private String email;
    private Picture profilePicture;
    private Boolean firstLogin;
    private Boolean active;

}
