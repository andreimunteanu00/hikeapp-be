package com.mnt.hikeapp.dto.chatroom;

import com.mnt.hikeapp.dto.user.UserShowDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatRoomPrivateDTO {

    private Long id;
    private UserShowDTO receiver;

}
