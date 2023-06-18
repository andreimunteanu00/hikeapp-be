package com.mnt.hikeapp.dto.user;

import com.mnt.hikeapp.entity.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSetupDTO {

    private boolean isFirstLogin;
    private String googleId;
    private String username;
    private String email;
    private Picture profilePicture;

}
