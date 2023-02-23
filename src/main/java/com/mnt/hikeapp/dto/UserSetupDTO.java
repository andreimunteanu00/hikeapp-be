package com.mnt.hikeapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSetupDTO {

    private boolean isFirstLogin;
    private String googleId;
    private String username;
    private String email;
    private String profilePicture;

}
